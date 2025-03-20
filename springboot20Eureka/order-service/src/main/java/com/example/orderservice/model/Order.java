package com.example.orderservice.model;

import java.util.List;

import com.example.orderservice.dto.ProductDto;

public class Order {
    private Long id;
    private List<ProductDto> products;
    private double totalAmount;
    
    // Constructors, getters, and setters
    public Order() {}
    
    public Order(Long id, List<ProductDto> products, double totalAmount) {
        this.id = id;
        this.products = products;
        this.totalAmount = totalAmount;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<ProductDto> getProducts() {
        return products;
    }
    
    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}