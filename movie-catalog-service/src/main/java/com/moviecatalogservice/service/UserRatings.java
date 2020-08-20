package com.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviecatalogservice.model.ListRatings;
import com.moviecatalogservice.model.Ratings;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@Service
public class UserRatings {

	@Autowired
	RestTemplate restTemplate;
	@HystrixCommand(fallbackMethod = "getFallbackListRatings")
	public ListRatings getListRatings(String userId) {
		return restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId, ListRatings.class);
	}

	
	private ListRatings getFallbackListRatings(String userId)
	{
		ListRatings lisRating = new ListRatings();
		lisRating.setUserId(userId);
		lisRating.setListRatings(Arrays.asList(new Ratings("0",0)));
		
		return lisRating;
		
	}
}
