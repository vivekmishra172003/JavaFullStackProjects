package com.springboot16.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	
	@GetMapping("/")
	public Map<String, String> home(){
		Map<String,String> response = new HashMap<>();
		response.put("message", "Welcom to simple Oauth2 with google");
		response.put("login_link", "/oauth2/authorization/google");
		return response;
	}
	
	@GetMapping("/user-info")
	public Map<String,Object> userInfo(@AuthenticationPrincipal OAuth2User principal){
		  Map<String, Object> userInfo = new HashMap<>();
	        userInfo.put("name", principal.getAttribute("name"));
	        userInfo.put("email", principal.getAttribute("email"));
	        userInfo.put("picture", principal.getAttribute("picture"));
	        
	        return userInfo;
	}
	
}
