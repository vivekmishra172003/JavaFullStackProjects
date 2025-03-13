package com.springboot09.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot09.exception.ResourceNotFoundException;
import com.springboot09.model.Product;
import com.springboot09.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id);
	}

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> updateProduct(Long id, Product productDetails) {
		// TODO Auto-generated method stub
		return productRepository.findById(id)
				.map(product ->{
					product.setName(productDetails.getName());
					product.setDescription(product.getDescription());
					product.setPrice(product.getPrice());
					product.setQuantity(product.getQuantity());
					return productRepository.save(product);
					
				});
	}

	@Override
	public boolean deleteProduct(Long id) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepository.findById(id);
		product.ifPresent(p->productRepository.delete(p));
		return product.isPresent();
		
		
	}

	@Override
	public Optional<Product> findByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(name);
	}

	

}
