package com.oauth2.keycloak_connection.service.impl;

import com.oauth2.keycloak_connection.dto.CompileRequest;
import com.oauth2.keycloak_connection.dto.CompileResponse;
import com.oauth2.keycloak_connection.service.CompilerService;
import org.springframework.stereotype.Service;

@Service("unsupportedCompilerService")
public class UnsupportedLanguageCompilerService implements CompilerService {

    @Override
    public CompileResponse compile(CompileRequest compileRequest) {
        String output = "";
        String error = "The language '" + compileRequest.getLanguage() + "' is not supported.";
        return new CompileResponse(output, error);
    }
}
