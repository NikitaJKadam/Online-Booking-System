package com.stackroute.userservice.model;


import lombok.Data;

@Data
public class ReviewAndRating {

	private String userEmailId;
	private float userRating;
	private String userReview;
}
