package com.example.orderservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderservice.client.ProductServiceClient;
import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.model.Order;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ProductServiceClient productServiceClient;
    
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        // For demonstration, we'll create a sample order
        // In a real app, you'd fetch this from a database
        
        // Get product with ID 1 from product-service
        ProductDto product = productServiceClient.getProductById(1L);
        
        // Create and return a sample order
        return new Order(id, Arrays.asList(product), product.getPrice());
    }
    
    @GetMapping("/with-products")
    public List<ProductDto> getOrderWithProducts() {
        // This demonstrates how to fetch all products from product-service
        return productServiceClient.getAllProducts();
    }
}