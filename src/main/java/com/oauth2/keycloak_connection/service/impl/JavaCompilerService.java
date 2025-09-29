package com.oauth2.keycloak_connection.service.impl;

import com.oauth2.keycloak_connection.dto.CompileRequest;
import com.oauth2.keycloak_connection.dto.CompileResponse;
import com.oauth2.keycloak_connection.service.CompilerService;
import org.springframework.stereotype.Service;

import javax.tools.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.UUID;

@Service("javaCompilerService")
public class JavaCompilerService implements CompilerService {

    @Override
    public CompileResponse compile(CompileRequest compileRequest) {
        String code = compileRequest.getCode();
        String originalClassName = extractClassName(code);

        if (originalClassName == null) {
            return new CompileResponse("", "Failed to extract class name from Java code.");
        }

        // Generate a unique class name per user submission
        String uniqueClassName = originalClassName + "_" + UUID.randomUUID().toString().replace("-", "");
        code = code.replaceFirst("public class " + originalClassName, "public class " + uniqueClassName);

        // Use temporary directory for compilation and execution
        try {
            Path tempDir = Files.createTempDirectory("java_compile_");
            Path javaFilePath = tempDir.resolve(uniqueClassName + ".java");
            Files.writeString(javaFilePath, code);

            // Compile the Java file
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(javaFilePath.toFile());
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, Arrays.asList("-d", tempDir.toString()), null, compilationUnits);
            boolean success = task.call();

            StringBuilder compileErrors = new StringBuilder();
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                compileErrors.append("Line ")
                        .append(diagnostic.getLineNumber())
                        .append(": ")
                        .append(diagnostic.getMessage(null))
                        .append("\n");
            }

            if (!success) {
                fileManager.close();
                deleteDirectory(tempDir.toFile());
                return new CompileResponse("", compileErrors.toString());
            }

            // Run the compiled class using ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder("java", "-cp", tempDir.toString(), uniqueClassName);
            Process process = pb.start();

            // Provide input if any
            if (compileRequest.getInput() != null && !compileRequest.getInput().isEmpty()) {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                    writer.write(compileRequest.getInput());
                    writer.flush();
                }
            }

            // Capture output and errors
            String output = new BufferedReader(new InputStreamReader(process.getInputStream()))
                    .lines().reduce("", (a, b) -> a + b + "\n");
            String error = new BufferedReader(new InputStreamReader(process.getErrorStream()))
                    .lines().reduce("", (a, b) -> a + b + "\n");

            process.waitFor();
            fileManager.close();
            deleteDirectory(tempDir.toFile());

            return new CompileResponse(output.trim(), error.trim());

        } catch (IOException | InterruptedException e) {
            return new CompileResponse("", "Execution failed: " + e.getMessage());
        }
    }

    // Extract class name from user code
    private String extractClassName(String code) {
        String[] lines = code.split("\\n");
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("public class ")) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 3) return parts[2];
            }
        }
        return null;
    }

    // Recursively delete temporary directory
    private void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                deleteDirectory(file);
            }
        }
        dir.delete();
    }
}
