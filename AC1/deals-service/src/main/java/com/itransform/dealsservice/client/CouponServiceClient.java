package com.itransform.dealsservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.itransform.dealsservice.dto.CouponDto;
import com.itransform.dealsservice.dto.DiscountRequest;
import com.itransform.dealsservice.dto.DiscountResponse;

@FeignClient(name = "coupon-service")
public interface CouponServiceClient {
    
    @GetMapping("/api/coupons/{code}")
    CouponDto getCouponByCode(@PathVariable("code") String code);
    
    @PostMapping("/api/coupons/calculate-price")
    DiscountResponse calculateFinalPrice(@RequestBody DiscountRequest request, 
                                        @RequestHeader("Authorization") String authToken);
}