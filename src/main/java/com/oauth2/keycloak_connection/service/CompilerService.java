package com.oauth2.keycloak_connection.service;

import com.oauth2.keycloak_connection.dto.CompileRequest;
import com.oauth2.keycloak_connection.dto.CompileResponse;

public interface CompilerService {
    CompileResponse compile(CompileRequest compileRequest);
}
