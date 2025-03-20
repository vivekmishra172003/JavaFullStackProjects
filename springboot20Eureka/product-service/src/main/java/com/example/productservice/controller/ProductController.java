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

    // Simulating a product database
    private List<Product> productList = Arrays.asList(
        new Product(1L, "Laptop", 1200.0),
        new Product(2L, "Phone", 800.0),
        new Product(3L, "Tablet", 400.0)
    );
    
    @GetMapping
    public List<Product> getAllProducts() {
        return productList;
    }
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}