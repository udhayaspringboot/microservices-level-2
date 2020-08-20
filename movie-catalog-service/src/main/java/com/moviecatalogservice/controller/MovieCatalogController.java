package com.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.moviecatalogservice.model.ListRatings;
import com.moviecatalogservice.model.Movie;
import com.moviecatalogservice.model.MovieCatalog;
import com.moviecatalogservice.model.Ratings;
import com.moviecatalogservice.service.MovieInfo;
import com.moviecatalogservice.service.UserRatings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;
	
	
	@Autowired
	
	MovieInfo movieInfo;
	
	@Autowired
	
	UserRatings userRatings;

	/*
	 * @Autowired WebClient.Builder webClientBuilder;
	 */

	@RequestMapping("/{userId}")
	//@HystrixCommand(fallbackMethod = "getFallbackMovies")
	public List<MovieCatalog> getMovies(@PathVariable String userId) {

		/* Map<Ratings,MovieCatalog> */

		// RestTemplate restTemplate = new RestTemplate();
		// = restTemplate.getForObject("http://localhost:8081/movies/movie1",
		// Movie.class);

//		List<Ratings> lisRat = new ArrayList<Ratings>();
//		lisRat.add(new Ratings("movie1", 4));
//		lisRat.add(new Ratings("movie2", 3));

		ListRatings rating = userRatings.getListRatings(userId);
		System.out.println("value at movie caalog controller " + rating.getListRatings().get(0).getMovieId());
		/*
		 * Map<Ratings,MovieCatalog> map = new HashMap<Ratings, MovieCatalog>(); for
		 * (Ratings ratings : lisRat) {
		 * 
		 * map.put(ratings, new MovieCatalog("Gravity","Sci-fy movie",4));
		 * 
		 * }
		 */

		return rating.getListRatings().stream().map(lisRa -> {
			//Movie mov =	webClientBuilder.build().get().uri("http://localhost:8081/movies/"+lisRa.getMovieId()).retrieve().bodyToMono(Movie.class).block();
			
			return movieInfo.getMovieCatalog(lisRa);

		}).collect(Collectors.toList());

	}
	
	
	
	
	/*public List<MovieCatalog> getFallbackMovies(@PathVariable String userId) {
		return Arrays.asList(new MovieCatalog("No Movie ", "", 0));
*/
//	}
}
