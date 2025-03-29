package com.example.apigateway.filter;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Log the request path
        logger.info("Request path: {}", exchange.getRequest().getPath());
        
        // Continue the filter chain
        return chain.filter(exchange)
            .then(Mono.fromRunnable(() -> {
                // Log the response status
                logger.info("Response status: {}", exchange.getResponse().getStatusCode());
            }));
    }
}
