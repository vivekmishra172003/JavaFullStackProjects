package com.itransform.userservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itransform.userservice.dto.AuthRequest;
import com.itransform.userservice.dto.AuthResponse;
import com.itransform.userservice.dto.UserDto;
import com.itransform.userservice.model.User;
import com.itransform.userservice.repository.UserRepository;
import com.itransform.userservice.security.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    public UserDto createUser(User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToDto(user);
    }
    
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return convertToDto(user);
    }
    
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public AuthResponse authenticate(AuthRequest request) {
        // This will throw an exception if authentication fails
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        
        // Generate JWT token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);
        
        // Get user details
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return new AuthResponse(token, convertToDto(user));
    }
    
    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }
}