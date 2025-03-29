package com.example.productservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productservice.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

    // Simulate a product database
    private List<Product> products = Arrays.asList(
        new Product(1L, "Laptop", 999.99),
        new Product(2L, "Smartphone", 499.99),
        new Product(3L, "Headphones", 99.99)
    );

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
    
    @GetMapping("/service-info")
    public String getServiceInfo() {
        return "Product Service is running on port 8082";
    }
}