package com.oauth2.keycloak_connection.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompileResponse {
    private String compilerOutput;
    private String compilerError;

    @Override
    public String toString() {
        return "CompileResponse{" +
                "compilerOutput='" + compilerOutput + '\'' +
                ", compilerError='" + compilerError + '\'' +
                '}';
    }
}
