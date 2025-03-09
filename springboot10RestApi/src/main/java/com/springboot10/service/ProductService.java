package com.springboot10.service;

import java.util.List;
import java.util.Optional;

import com.springboot10.model.Product;

public interface ProductService {
	List<Product> getAllProducts();
	Optional<Product> getProductById(Long id);
	Product createProduct(Product product);
	Optional<Product> updateProduct(Long id,Product productDetails);
	boolean deleteProduct(Long id);
	Optional<Product> findByName(String name);

}
