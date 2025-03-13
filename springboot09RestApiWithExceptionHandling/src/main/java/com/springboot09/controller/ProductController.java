package com.springboot09.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot09.exception.ResourceNotFoundException;
import com.springboot09.model.Product;
import com.springboot09.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
	}
    // Get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        // Using orElseThrow to handle the case when product is not found
        Optional<Product> product = productService.getProductById(id);
        
             if(product.isPresent()) {
            	 return new ResponseEntity<>(product.get(), HttpStatus.OK);
             }else {
            	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }
    }
    
    @GetMapping("/search")
    public ResponseEntity<Product> findByName(@RequestParam String name){
    	Optional<Product> product = productService.findByName(name);
                if(product.isPresent()) {
               	 return new ResponseEntity<>(product.get(), HttpStatus.OK);
                }else {
               	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }	
    }
    
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
    	Product newProduct = productService.createProduct(product);
    	return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product){
    	Optional<Product> product1 = productService.updateProduct(id, product);
        if(product1.isPresent()) {
          	 return new ResponseEntity<>(product1.get(), HttpStatus.OK);
           }else {
          	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }	
    	
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        
        if (!deleted) {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
