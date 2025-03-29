package com.itransform.couponservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.itransform.couponservice.dto.DealDto;

@FeignClient(name = "deals-service")
public interface DealsServiceClient {
    
    @GetMapping("/api/deals/{id}")
    DealDto getDealById(@PathVariable("id") Long id);
    
    @GetMapping("/api/deals/{id}")
    DealDto getDealByIdWithAuth(@PathVariable("id") Long id, 
                               @RequestHeader("Authorization") String authToken);
}