package com.stackroute.userservice.model;


import lombok.Builder;
import lombok.Data;

@Data
public class Address {
	private String state;
	private String city;
	private String street;
	private Long pinCode;

	
}
