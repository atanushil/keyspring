package com.oauth2.keycloak_connection.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompileRequest {
    private String input;
    private String code;
    private String language;
}
