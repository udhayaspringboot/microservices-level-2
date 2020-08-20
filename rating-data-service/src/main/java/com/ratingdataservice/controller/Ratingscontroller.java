package com.ratingdataservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingdataservice.model.ListRatings;
import com.ratingdataservice.model.Ratings;

@RestController
@RequestMapping("/ratingsdata")
public class Ratingscontroller {
	
	@RequestMapping("/{movieId}")
	public Ratings getRatings(@PathVariable String movieId)
	{
		
		return new Ratings(movieId,4);
	}
	
	@RequestMapping("/users/{userId}")
	public ListRatings getAllMovieRatings(@PathVariable String userId)
	{
		
		List<Ratings> lisRat = new ArrayList<Ratings>();
		lisRat.add(new Ratings("100", 4));
		lisRat.add(new Ratings("550", 3));
		System.out.println("entered into ratings");
		ListRatings listRating = new ListRatings();
		listRating.setListRatings(lisRat);
		
		return listRating;
	}
	

}
