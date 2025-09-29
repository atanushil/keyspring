package com.oauth2.keycloak_connection.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api/admin/home")
    @PreAuthorize("hasRole('backend_admin')")
    public String adminHome() {
        return "Welcome to my admin page";
    }

    @GetMapping("/api/trainee/home")
    @PreAuthorize("hasRole('backend_trainee')")
    public String userHome() {
        return "Welcome to my user page";
    }

}
