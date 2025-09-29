package com.oauth2.keycloak_connection.controller;

import com.oauth2.keycloak_connection.dto.CompileRequest;
import com.oauth2.keycloak_connection.dto.CompileResponse;
import com.oauth2.keycloak_connection.service.CompilerService;
import com.oauth2.keycloak_connection.service.CompilerServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class CompilerController {
    @Autowired
    private CompilerServiceFactory compilerServiceFactory;

    @PostMapping("/run")
    public CompileResponse compile(@RequestBody CompileRequest request) {
        CompilerService service = compilerServiceFactory.getCompilerService(request.getLanguage());
        return service.compile(request);
    }

}
