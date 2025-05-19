// src/main/java/com/example/userauthservice/controller/AuthController.java
package com.example.userauthservice.controller;

import com.example.userauthservice.dto.JwtResponse;
import com.example.userauthservice.dto.LoginRequest;
import com.example.userauthservice.dto.SignupRequest;
import com.example.userauthservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        String response = authService.registerUser(signupRequest);
        return ResponseEntity.ok(response);
    }
}