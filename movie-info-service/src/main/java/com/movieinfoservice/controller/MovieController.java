package com.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.movieinfoservice.model.Movie;
import com.movieinfoservice.model.MovieSummery;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${api.key}")
	private String apiKey;
	@RequestMapping("/{movieId}")
	public Movie getMovies(@PathVariable String movieId)
	{
		
		
		MovieSummery movSum = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key=" + apiKey, MovieSummery.class);
		
		System.out.println("entered into movie info");
		return new Movie(movieId,movSum.getTitle(),movSum.getOverview());
	}

}
