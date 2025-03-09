package com.springboot10.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot10.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>  {
	
	Optional<Product> findByName(String name);

}
