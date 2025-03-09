package com.springboot10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot10.exception.ResourceNotFoundException;
import com.springboot10.model.Product;
import com.springboot10.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllproduct(){
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		Product product = productService.getProductById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product not found with id: " + id));
		
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	@GetMapping("/search")
	public ResponseEntity<Product> findfindByName(@RequestParam String name){
		Product product = productService.findByName(name)
				.orElseThrow(()-> new ResourceNotFoundException("Product not found with id: " + name));
		
		return new ResponseEntity<>(product,HttpStatus.OK);	
	}
	
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product newProduct = productService.createProduct(product);
		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
	}
	   @PutMapping("/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
	        // Using orElseThrow to handle the case when product is not found
	        Product updatedProduct = productService.updateProduct(id, product)
	                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
	        
	        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	    }
	   
	    // Delete a product
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        boolean deleted = productService.deleteProduct(id);
	        
	        if (!deleted) {
	            throw new ResourceNotFoundException("Product not found with id: " + id);
	        }
	        
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}
