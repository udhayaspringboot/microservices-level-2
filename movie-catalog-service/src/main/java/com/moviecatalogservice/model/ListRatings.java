package com.moviecatalogservice.model;

import java.util.List;

public class ListRatings {
	private List<Ratings> listRatings;
private String userId;

	public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

	public List<Ratings> getListRatings() {
		return listRatings;
	}

	public void setListRatings(List<Ratings> listRatings) {
		this.listRatings = listRatings;
	}

	public ListRatings(List<Ratings> listRatings, String userId) {
		super();
		this.listRatings = listRatings;
		this.userId = userId;
	}

	public ListRatings() {
		super();
	}
	

}
