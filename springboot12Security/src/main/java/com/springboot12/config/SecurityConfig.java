package com.springboot12.config;



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
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/", "/error").permitAll()  // Public endpoints
                    .anyRequest().authenticated()                // All other endpoints require authentication
            )
            .oauth2Login(oauth2Login ->
                oauth2Login
                    .loginPage("/login")                         // Custom login page URL (optional)
                    .defaultSuccessUrl("/", true)       // Redirect after successful login
            )
            .logout(logout ->
                logout
                    .logoutSuccessUrl("/")                       // Redirect after logout
                    .permitAll()
            );
            
        return http.build();
    }
}
