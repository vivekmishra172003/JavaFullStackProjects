package com.itransform.dealsservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CouponDto {
    private Long id;
    private String code;
    private String description;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal minPurchaseAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public BigDecimal getDiscountValue() {
		return discountValue;
	}
	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}
	public BigDecimal getMinPurchaseAmount() {
		return minPurchaseAmount;
	}
	public void setMinPurchaseAmount(BigDecimal minPurchaseAmount) {
		this.minPurchaseAmount = minPurchaseAmount;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public CouponDto(Long id, String code, String description, String discountType, BigDecimal discountValue,
			BigDecimal minPurchaseAmount, LocalDate startDate, LocalDate endDate, boolean active) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.discountType = discountType;
		this.discountValue = discountValue;
		this.minPurchaseAmount = minPurchaseAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.active = active;
	}
	public CouponDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}