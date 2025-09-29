package com.oauth2.keycloak_connection.service.impl;

import com.oauth2.keycloak_connection.dto.CompileRequest;
import com.oauth2.keycloak_connection.dto.CompileResponse;
import com.oauth2.keycloak_connection.service.CompilerService;
import org.springframework.stereotype.Service;

@Service("javaCompilerService")
public class JavaCompilerService implements CompilerService {

    @Override
    public CompileResponse compile(CompileRequest compileRequest) {
        // TODO: Implement actual Java compilation logic
        String output = "Java code compiled successfully: " + compileRequest.getCode();
        String error = "";
        return new CompileResponse(output, error);
    }
}
