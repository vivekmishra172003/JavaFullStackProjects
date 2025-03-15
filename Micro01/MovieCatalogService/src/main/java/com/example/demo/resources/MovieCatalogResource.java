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
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.CatalogItem;
import com.example.demo.model.Movie;
import com.example.demo.model.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		// get all rated movie IDs
		
		RestTemplate restTemplate = new RestTemplate();
		List<Rating> ratings = Arrays.asList(
				new Rating("1234",4),
				new Rating("123",3)
				);
		
        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);

            // Null check for movie response
            String movieName = (movie != null) ? movie.getName() : "Unknown Movie";
            return new CatalogItem(movieName, "Description not available", rating.getRating());
        }).collect(Collectors.toList());
    }
			
			
	
	@GetMapping("/")
	public String ram() {
		return "Ram Ram";
	}
}
