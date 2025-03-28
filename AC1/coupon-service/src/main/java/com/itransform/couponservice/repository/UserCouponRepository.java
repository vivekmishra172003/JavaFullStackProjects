package com.itransform.couponservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itransform.couponservice.model.UserCoupon;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
    
    List<UserCoupon> findByUserId(Long userId);
    
    Optional<UserCoupon> findByUserIdAndCouponId(Long userId, Long couponId);
    
    List<UserCoupon> findByUserIdAndIsUsed(Long userId, boolean isUsed);
}