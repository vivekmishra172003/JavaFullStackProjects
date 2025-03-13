package com.springboot09.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot09.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Data JPA provides basic CRUD operations
    // You can add custom queries here if needed
	Optional<Product> findByName(String name);
}
