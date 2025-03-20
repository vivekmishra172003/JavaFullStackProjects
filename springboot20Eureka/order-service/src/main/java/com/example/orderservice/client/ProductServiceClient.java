package com.example.orderservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.orderservice.dto.ProductDto;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
    
    @GetMapping("/products")
    List<ProductDto> getAllProducts();
    
    @GetMapping("/products/{id}")
    ProductDto getProductById(@PathVariable Long id);
}