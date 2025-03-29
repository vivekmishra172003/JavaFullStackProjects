package com.example.userservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.model.User;



@RestController
@RequestMapping("/users")
public class UserController {

    // Simulate a user database
    private List<User> users = Arrays.asList(
        new User(1L, "John Doe", "john@example.com"),
        new User(2L, "Jane Smith", "jane@example.com"),
        new User(3L, "Bob Johnson", "bob@example.com")
    );

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    @GetMapping("/service-info")
    public String getServiceInfo() {
        return "User Service is running on port 8081";
    }
}