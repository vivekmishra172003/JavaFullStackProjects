package com.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route for service-one with additional path rewriting
                .route("service-one-route", r -> r
                        .path("/products/**")
                        .filters(f -> f
                                .rewritePath("/products/(?<segment>.*)", "/api/products/${segment}")
                                .addRequestHeader("X-Gateway-Source", "api-gateway"))
                        .uri("lb://service-one"))
                
                // Route for service-two with additional path rewriting
                .route("service-two-route", r -> r
                        .path("/orders/**")
                        .filters(f -> f
                                .rewritePath("/orders/(?<segment>.*)", "/api/orders/${segment}")
                                .addRequestHeader("X-Gateway-Source", "api-gateway"))
                        .uri("lb://service-two"))
                
                // Route for service health checks
                .route("service-health-route", r -> r
                        .path("/service-info/**")
                        .filters(f -> f
                                .rewritePath("/service-info/(?<service>.*)", "/${service}/service-info"))
                        .uri("lb://"))
                
                .build();
    }
}