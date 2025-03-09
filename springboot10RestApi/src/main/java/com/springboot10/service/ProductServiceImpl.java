package com.springboot10.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot10.model.Product;
import com.springboot10.repository.ProductRepository;
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
				.map(ep->{
					ep.setName(productDetails.getName());
					ep.setDescription(productDetails.getDescription());
					ep.setPrice(productDetails.getPrice());
					ep.setQuantity(productDetails.getQuantity());
					return productRepository.save(ep);
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
