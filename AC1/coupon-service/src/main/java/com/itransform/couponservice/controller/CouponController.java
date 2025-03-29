package com.itransform.couponservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itransform.couponservice.dto.CouponDto;
import com.itransform.couponservice.dto.DiscountRequest;
import com.itransform.couponservice.dto.DiscountResponse;
import com.itransform.couponservice.service.CouponService;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;
    
    @GetMapping
    public ResponseEntity<List<CouponDto>> getAllActiveCoupons() {
        return ResponseEntity.ok(couponService.getAllActiveCoupons());
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<CouponDto> getCouponByCode(@PathVariable String code) {
        return ResponseEntity.ok(couponService.getCouponByCode(code));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CouponDto>> getUserCoupons(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String token) {
        // This endpoint is protected - authorization is enforced by SecurityConfig
        return ResponseEntity.ok(couponService.getUserCoupons(userId));
    }
    
    @PostMapping("/calculate-price")
    public ResponseEntity<DiscountResponse> calculateFinalPrice(
            @RequestBody DiscountRequest request,
            @RequestHeader("Authorization") String token) {
        // This endpoint is protected - authorization is enforced by SecurityConfig
        return ResponseEntity.ok(couponService.calculateFinalPrice(request));
    }
}