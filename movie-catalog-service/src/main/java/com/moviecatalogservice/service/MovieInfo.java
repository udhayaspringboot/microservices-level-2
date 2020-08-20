package com.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviecatalogservice.model.Movie;
import com.moviecatalogservice.model.MovieCatalog;
import com.moviecatalogservice.model.Ratings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfo {
	
	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackMovieCatalog")
	public MovieCatalog getMovieCatalog(Ratings lisRa) {
		Movie mov = restTemplate.getForObject("http://movie-info-service/movies/" + lisRa.getMovieId(), Movie.class);

	return new MovieCatalog(mov.getMovieName(), mov.getMovieDescription(), lisRa.getRating());
	}
	
	
	private MovieCatalog getFallbackMovieCatalog(Ratings lisRa)
	{
		
		return new MovieCatalog("Movie name not found","",lisRa.getRating());
		
	}
	

}
