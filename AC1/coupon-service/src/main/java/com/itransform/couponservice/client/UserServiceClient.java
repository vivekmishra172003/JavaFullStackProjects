package com.itransform.couponservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    
    @GetMapping("/api/users/validate-token")
    boolean validateToken(@RequestHeader("Authorization") String token);
}