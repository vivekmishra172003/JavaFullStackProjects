package com.itransform.couponservice.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_coupons")
@Data

public class UserCoupon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "coupon_id")
    private Long couponId;
    
    @Column(name = "is_used")
    private boolean isUsed;
    
    @Column(name = "used_date")
    private LocalDateTime usedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public LocalDateTime getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(LocalDateTime usedDate) {
		this.usedDate = usedDate;
	}

	public UserCoupon(Long id, Long userId, Long couponId, boolean isUsed, LocalDateTime usedDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.couponId = couponId;
		this.isUsed = isUsed;
		this.usedDate = usedDate;
	}

	public UserCoupon() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}