package com.springboot09.service;

import java.util.List;
import java.util.Optional;

import com.springboot09.model.Product;

public interface ProductService {
	List<Product> getAllProducts();
	Optional<Product> getProductById(Long id);
	Product createProduct(Product product);
	Optional<Product> updateProduct(Long id,Product ProductDetails);
	boolean deleteProduct(Long id);
	Optional<Product> findByName(String name);
}
