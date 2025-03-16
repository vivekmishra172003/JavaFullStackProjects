package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    // Create a new user
    public UserDTO createUser(UserDTO userDTO) {
        // Convert DTO to Entity
        User user = modelMapper.map(userDTO, User.class);
        
        // Save entity to database
        User savedUser = userRepository.save(user);
        
        // Convert Entity back to DTO
        return modelMapper.map(savedUser, UserDTO.class);
    }
    
    // Get all users
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        
        // Convert list of entities to list of DTOs
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
    
    // Get user by ID
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Convert Entity to DTO
        return modelMapper.map(user, UserDTO.class);
    }
    
    // Update user
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Update existing user with DTO values
        // Notice we're NOT using modelMapper here directly
        // Instead, we're manually mapping to preserve the ID
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setAddress(userDTO.getAddress());
        
        // Save updated entity
        User updatedUser = userRepository.save(existingUser);
        
        // Convert updated entity to DTO
        return modelMapper.map(updatedUser, UserDTO.class);
    }
    
    // Delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
