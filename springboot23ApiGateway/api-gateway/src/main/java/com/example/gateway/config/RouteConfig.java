package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            // Routes for Product Service
            .route("product-service-route", r -> r
                .path("/api/products/**")
                .uri("http://localhost:8081"))
            
            // Routes for Order Service
            .route("order-service-route", r -> r
                .path("/api/orders/**")
                .uri("http://localhost:8082"))
            
            // Default fallback route
            .route("fallback-route", r -> r
                .path("/**")
                .uri("https://httpbin.org/status/404"))
            .build();
    }
}