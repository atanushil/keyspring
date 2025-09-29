package com.oauth2.keycloak_connection.service.impl;

import com.oauth2.keycloak_connection.dto.CompileRequest;
import com.oauth2.keycloak_connection.dto.CompileResponse;
import com.oauth2.keycloak_connection.service.CompilerService;
import org.springframework.stereotype.Service;

@Service("typescriptCompilerService")
public class TypeScriptCompilerService implements CompilerService {

    @Override
    public CompileResponse compile(CompileRequest compileRequest) {
        String output = "TypeScript code executed successfully: " + compileRequest.getCode();
        String error = "";
        return new CompileResponse(output, error);
    }
}
