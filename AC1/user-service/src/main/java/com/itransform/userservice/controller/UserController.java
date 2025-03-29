package com.itransform.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itransform.userservice.dto.AuthRequest;
import com.itransform.userservice.dto.AuthResponse;
import com.itransform.userservice.dto.UserDto;
import com.itransform.userservice.model.User;
import com.itransform.userservice.security.JwtUtil;
import com.itransform.userservice.security.CustomUserDetailsService;
import com.itransform.userservice.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
    
    // Keeping the old login endpoint for backward compatibility, but now using JWT authentication
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
    
    // New endpoint for token validation
    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            String username = jwtUtil.extractUsername(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            return ResponseEntity.ok(jwtUtil.validateToken(jwt, userDetails));
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}