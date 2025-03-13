package com.springboot12.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the OAuth2 Demo App!";
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        // Return user information from the OAuth provider
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
    
    @GetMapping("/secured")
    public String secured() {
        return "This is a secured endpoint that requires authentication";
    }
}
