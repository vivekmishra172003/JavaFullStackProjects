package com.itransform.couponservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponse {
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private BigDecimal couponDiscount;
    private BigDecimal finalPrice;
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	public BigDecimal getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public BigDecimal getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(BigDecimal couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public BigDecimal getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(BigDecimal finalPrice) {
		this.finalPrice = finalPrice;
	}
	public DiscountResponse(BigDecimal originalPrice, BigDecimal discountedPrice, BigDecimal couponDiscount,
			BigDecimal finalPrice) {
		super();
		this.originalPrice = originalPrice;
		this.discountedPrice = discountedPrice;
		this.couponDiscount = couponDiscount;
		this.finalPrice = finalPrice;
	}
	public DiscountResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}