package com.example.apigateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Route requests to user-service
            .route("user-service-route", r -> r
                .path("/users/**")
                .uri("http://localhost:8081"))
            
            // Route requests to product-service
            .route("product-service-route", r -> r
                .path("/products/**")
                .uri("http://localhost:8082"))
            
            .build();
    }
}
