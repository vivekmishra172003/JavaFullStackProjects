// src/main/java/com/example/userauthservice/service/AuthService.java
package com.example.userauthservice.service;

import com.example.userauthservice.dto.JwtResponse;
import com.example.userauthservice.dto.LoginRequest;
import com.example.userauthservice.dto.SignupRequest;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    String registerUser(SignupRequest signupRequest);
}