package com.itransform.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class AuthResponse {
    private String token;
    private UserDto user;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public AuthResponse(String token, UserDto user) {
		super();
		this.token = token;
		this.user = user;
	}
	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}