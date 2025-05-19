// src/main/java/com/example/userauthservice/dto/LoginRequest.java
package com.example.userauthservice.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}