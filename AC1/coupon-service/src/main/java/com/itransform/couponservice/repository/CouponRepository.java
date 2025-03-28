package com.itransform.couponservice.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itransform.couponservice.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    
    Optional<Coupon> findByCode(String code);
    
    @Query("SELECT c FROM Coupon c WHERE c.isActive = true AND c.startDate <= ?1 AND c.endDate >= ?1")
    List<Coupon> findAllActiveCoupons(LocalDate currentDate);
}