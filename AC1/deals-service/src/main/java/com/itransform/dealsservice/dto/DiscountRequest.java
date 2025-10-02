package com.itransform.dealsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class DiscountRequest {
    private Long dealId;
    private String couponCode;
    private Long userId;
	public Long getDealId() {
		return dealId;
	}
	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public DiscountRequest(Long dealId, String couponCode, Long userId) {
		super();
		this.dealId = dealId;
		this.couponCode = couponCode;
		this.userId = userId;
	}
	public DiscountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}