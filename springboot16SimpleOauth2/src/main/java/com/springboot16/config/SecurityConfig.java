package com.springboot16.config;

//Import necessary Spring Security classes
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login").permitAll() // Public endpoints
                .anyRequest().authenticated() // All other requests require authentication
            )
            .oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/user-info", true) // Redirect after successful login
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/") // Redirect after logout
            );

        return http.build();
    }
}


