package com.oauth2.keycloak_connection.service;

import com.oauth2.keycloak_connection.dto.CompileRequest;
import com.oauth2.keycloak_connection.dto.CompileResponse;
import org.springframework.stereotype.Service;

@Service
public class CompilerService {

    public CompileResponse getCompilerOutput(CompileRequest compileRequest) {
        // TODO: Implement actual compilation logic here
        String code = compileRequest.getCode();
        String input = compileRequest.getInput();

        // For now, just echo the code and input as output
        String compilerOutput = "Code: " + code + "\nInput: " + input;
        String compilerError = ""; // Empty if no error

        return new CompileResponse(compilerOutput, compilerError);
    }
}
