package com.itransform.couponservice.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itransform.couponservice.client.DealsServiceClient;
import com.itransform.couponservice.dto.CouponDto;
import com.itransform.couponservice.dto.DealDto;
import com.itransform.couponservice.dto.DiscountRequest;
import com.itransform.couponservice.dto.DiscountResponse;
import com.itransform.couponservice.model.Coupon;
import com.itransform.couponservice.model.UserCoupon;
import com.itransform.couponservice.repository.CouponRepository;
import com.itransform.couponservice.repository.UserCouponRepository;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;
    
    @Autowired
    private UserCouponRepository userCouponRepository;
    
    @Autowired
    private DealsServiceClient dealsServiceClient;
    
    public List<CouponDto> getAllActiveCoupons() {
        return couponRepository.findAllActiveCoupons(LocalDate.now()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public List<CouponDto> getUserCoupons(Long userId) {
        // This is now a protected endpoint - only accessible with valid JWT
        List<UserCoupon> userCoupons = userCouponRepository.findByUserIdAndIsUsed(userId, false);
        
        return userCoupons.stream()
                .map(uc -> couponRepository.findById(uc.getCouponId()).orElse(null))
                .filter(coupon -> coupon != null && coupon.isActive())
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public CouponDto getCouponByCode(String code) {
        // Public endpoint - just show coupon details
        Coupon coupon = couponRepository.findByCode(code)
                        .orElseThrow(() -> new RuntimeException("Coupon not found with code: " + code));
        
        if (!coupon.isActive() || coupon.getStartDate().isAfter(LocalDate.now()) || coupon.getEndDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Coupon is not valid");
        }
        
        return convertToDto(coupon);
    }
    
    public DiscountResponse calculateFinalPrice(DiscountRequest request) {
        // This is now a protected endpoint - only accessible with valid JWT
        
        // Get deal details
        DealDto deal = dealsServiceClient.getDealById(request.getDealId());
        
        // Initialize response
        DiscountResponse response = new DiscountResponse();
        response.setOriginalPrice(deal.getOriginalPrice());
        response.setDiscountedPrice(deal.getDiscountedPrice());
        response.setCouponDiscount(BigDecimal.ZERO);
        response.setFinalPrice(deal.getDiscountedPrice());
        
        // If no coupon code, just return the discounted price
        if (request.getCouponCode() == null || request.getCouponCode().isEmpty()) {
            return response;
        }
        
        try {
            // Get and validate coupon
            Coupon coupon = couponRepository.findByCode(request.getCouponCode())
                                .orElseThrow(() -> new RuntimeException("Invalid coupon code"));
            
            // Check if coupon is active
            if (!coupon.isActive() || coupon.getStartDate().isAfter(LocalDate.now()) || coupon.getEndDate().isBefore(LocalDate.now())) {
                throw new RuntimeException("Coupon is expired or not active");
            }
            
            // Check minimum purchase amount
            if (coupon.getMinPurchaseAmount().compareTo(deal.getDiscountedPrice()) > 0) {
                throw new RuntimeException("Purchase amount does not meet minimum requirement for this coupon");
            }
            
            // Calculate coupon discount
            BigDecimal couponDiscount;
            if (coupon.getDiscountType() == Coupon.DiscountType.PERCENTAGE) {
                couponDiscount = deal.getDiscountedPrice().multiply(coupon.getDiscountValue())
                                    .divide(new BigDecimal("100"));
            } else {
                couponDiscount = coupon.getDiscountValue();
                // Ensure discount doesn't exceed the price
                if (couponDiscount.compareTo(deal.getDiscountedPrice()) > 0) {
                    couponDiscount = deal.getDiscountedPrice();
                }
            }
            
            // Calculate final price
            BigDecimal finalPrice = deal.getDiscountedPrice().subtract(couponDiscount);
            
            // Update response
            response.setCouponDiscount(couponDiscount);
            response.setFinalPrice(finalPrice);
            
            // If user ID is provided, mark the coupon as used for this user
            if (request.getUserId() != null) {
                UserCoupon userCoupon = userCouponRepository.findByUserIdAndCouponId(request.getUserId(), coupon.getId())
                                            .orElse(new UserCoupon());
                
                if (userCoupon.getId() == null) {
                    userCoupon.setUserId(request.getUserId());
                    userCoupon.setCouponId(coupon.getId());
                }
                
                userCoupon.setUsed(true);
                userCoupon.setUsedDate(LocalDateTime.now());
                userCouponRepository.save(userCoupon);
            }
            
        } catch (Exception e) {
            // If any error occurs with the coupon, just return the regular discounted price
            response.setFinalPrice(deal.getDiscountedPrice());
        }
        
        return response;
    }
    
    private CouponDto convertToDto(Coupon coupon) {
        CouponDto dto = new CouponDto();
        dto.setId(coupon.getId());
        dto.setCode(coupon.getCode());
        dto.setDescription(coupon.getDescription());
        dto.setDiscountType(coupon.getDiscountType().toString());
        dto.setDiscountValue(coupon.getDiscountValue());
        dto.setMinPurchaseAmount(coupon.getMinPurchaseAmount());
        dto.setStartDate(coupon.getStartDate());
        dto.setEndDate(coupon.getEndDate());
        dto.setActive(coupon.isActive());
        return dto;
    }
}