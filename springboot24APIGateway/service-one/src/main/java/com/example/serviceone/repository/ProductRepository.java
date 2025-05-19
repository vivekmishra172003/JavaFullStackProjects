package com.example.serviceone.repository;

import com.example.serviceone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Data JPA provides basic CRUD operations automatically
    // You can add custom query methods if needed
}