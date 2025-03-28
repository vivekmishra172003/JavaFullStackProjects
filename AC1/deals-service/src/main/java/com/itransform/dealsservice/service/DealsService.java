package com.itransform.dealsservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itransform.dealsservice.client.CouponServiceClient;
import com.itransform.dealsservice.dto.DealDto;
import com.itransform.dealsservice.dto.DiscountRequest;
import com.itransform.dealsservice.dto.DiscountResponse;
import com.itransform.dealsservice.model.Deal;
import com.itransform.dealsservice.repository.DealsRepository;

@Service
public class DealsService {

    @Autowired
    private DealsRepository dealsRepository;
    
    @Autowired
    private CouponServiceClient couponServiceClient;
    
    public List<DealDto> getAllDeals() {
        return dealsRepository.findByIsActiveTrue().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public DealDto getDealById(Long id) {
        Deal deal = dealsRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Deal not found with id: " + id));
        return convertToDto(deal);
    }
    
    public List<DealDto> getDealsByCategory(String category) {
        return dealsRepository.findByCategoryAndIsActiveTrue(category).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    public DiscountResponse calculatePriceWithCoupon(Long dealId, String couponCode, Long userId) {
        DiscountRequest request = new DiscountRequest();
        request.setDealId(dealId);
        request.setCouponCode(couponCode);
        request.setUserId(userId);
        
        return couponServiceClient.calculateFinalPrice(request);
    }
    
    private DealDto convertToDto(Deal deal) {
        DealDto dto = new DealDto();
        dto.setId(deal.getId());
        dto.setTitle(deal.getTitle());
        dto.setDescription(deal.getDescription());
        dto.setMerchantName(deal.getMerchantName());
        dto.setCategory(deal.getCategory());
        dto.setOriginalPrice(deal.getOriginalPrice());
        dto.setDiscountedPrice(deal.getDiscountedPrice());
        dto.setDiscountPercentage(deal.getDiscountPercentage());
        dto.setStartDate(deal.getStartDate());
        dto.setEndDate(deal.getEndDate());
        dto.setActive(deal.isActive());
        return dto;
    }
}