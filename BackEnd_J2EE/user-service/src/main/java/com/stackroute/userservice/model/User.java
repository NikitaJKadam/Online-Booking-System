package com.stackroute.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
//@Data
@Document(collection = "User")
public class User {
	
	@Id
	@ApiModelProperty(notes = "email of the User", name = "userEmailId", dataType = "String")
	private String userEmailId;
	private String userPassword;
	private String userName;
	private long userContactNo;
	private Roles userRole;
	private Address userAddress;
	private ReviewAndRating reviewandrating;

	public User() {
	}

	public User(String userEmailId, String userPassword, String userName, long userContactNo, Roles userRole, Address userAddress, ReviewAndRating reviewandrating) {
		this.userEmailId = userEmailId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userContactNo = userContactNo;
		this.userRole = userRole;
		this.userAddress = userAddress;
		this.reviewandrating = reviewandrating;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public long getUserContactNo() {
		return userContactNo;
	}

	public Roles getUserRole() {
		return userRole;
	}

	public Address getUserAddress() {
		return userAddress;
	}

	public ReviewAndRating getReviewandrating() {
		return reviewandrating;
	}

	

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserContactNo(long userContactNo) {
		this.userContactNo = userContactNo;
	}

	public void setUserRole(Roles userRole) {
		this.userRole = userRole;
	}

	public void setUserAddress(Address userAddress) {
		this.userAddress = userAddress;
	}

	public void setReviewandrating(ReviewAndRating reviewandrating) {
		this.reviewandrating = reviewandrating;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
}
