package com.oauth2.keycloak_connection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CompilerServiceFactory {

    @Autowired
    private ApplicationContext context;

    public CompilerService getCompilerService(String language) {
        if (language == null || language.isEmpty()) {
            return context.getBean("unsupportedCompilerService", CompilerService.class);
        }

        return switch (language.toLowerCase()) {
            case "java" -> context.getBean("javaCompilerService", CompilerService.class);
            case "python" -> context.getBean("pythonCompilerService", CompilerService.class);
            case "javascript", "js" -> context.getBean("javascriptCompilerService", CompilerService.class);
            case "typescript", "ts" -> context.getBean("typescriptCompilerService", CompilerService.class);
            default -> context.getBean("unsupportedCompilerService", CompilerService.class);
        };
    }
}
