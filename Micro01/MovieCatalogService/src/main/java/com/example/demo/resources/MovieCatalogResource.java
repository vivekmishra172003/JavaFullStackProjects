package com.example.demo.resources;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CatalogItem;
import com.example.demo.model.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		// get all rated movie IDs
		
		List<Rating> ratings = Arrays.asList(
				new Rating("1234",4),
				new Rating("123",3)
				);
		
	return	ratings.stream().map(rating->new CatalogItem("Transfomrer", "Test", 4)).collect(Collectors.toList());
		// for each movie ID, call movie info service and get details
		
		// put them all together
//		return Collections.singletonList(
//				new CatalogItem("Transfomrer", "Test", 4)
//				);
	}
	
	@GetMapping("/")
	public String ram() {
		return "Ram Ram";
	}
}
