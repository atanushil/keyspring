package com.oauth2.keycloak_connection.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("/admin/home")
    public String adminHome() {
        return "Welcome to my admin page";
    }

    @GetMapping("/trainee/home")
    public String userHome() {
        return "Welcome to my user page";
    }

}
