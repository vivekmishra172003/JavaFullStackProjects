package com.itransform.dealsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itransform.dealsservice.dto.DealDto;
import com.itransform.dealsservice.dto.DiscountResponse;
import com.itransform.dealsservice.service.DealsService;

@RestController
@RequestMapping("/api/deals")
public class DealsController {

    @Autowired
    private DealsService dealsService;
    
    @GetMapping
    public ResponseEntity<List<DealDto>> getAllDeals() {
        return ResponseEntity.ok(dealsService.getAllDeals());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DealDto> getDealById(@PathVariable Long id) {
        return ResponseEntity.ok(dealsService.getDealById(id));
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<DealDto>> getDealsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(dealsService.getDealsByCategory(category));
    }
    
    @PostMapping("/{id}/apply-coupon")
    public ResponseEntity<DiscountResponse> applyDealCoupon(
            @PathVariable Long id, 
            @RequestParam String couponCode,
            @RequestParam Long userId,
            @RequestHeader("Authorization") String token) {
        
        // The request will only reach here if authenticated due to Security config
        // Extract token for passing to the coupon service
        String authToken = token.startsWith("Bearer ") ? token : "Bearer " + token;
        
        return ResponseEntity.ok(dealsService.calculatePriceWithCoupon(id, couponCode, userId, authToken));
    }
}