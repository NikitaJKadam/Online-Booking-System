package com.stackroute.userservice.model;

import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Document(collection = "ServiceCenter")
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class ServiceCenter {
	
	@Id
	@ApiModelProperty(notes = "email of the ServiceCenter",name="centerEmailId",dataType = "String")
	private String centerEmailId;
	private String password;
	private String centerName;
	private long centerContactNo;
	private String scBranch;
	private Address scAddress;
	private List<String> productCategory;
	private List<ReviewAndRating> reviewAndRating;
	private float avgRating;
	private Roles userRole;

	public ServiceCenter() {
	}
	public ServiceCenter(String centerEmailId, String password, String centerName, long centerContactNo, String scBranch, Address scAddress, List<String> productCategory, List<ReviewAndRating> reviewAndRating, float avgRating,Roles userRole) {
		this.centerEmailId = centerEmailId;
		this.password = password;
		this.centerName = centerName;
		this.centerContactNo = centerContactNo;
		this.scBranch = scBranch;
		this.scAddress = scAddress;
		this.productCategory = productCategory;
		this.reviewAndRating = reviewAndRating;
		this.avgRating = avgRating;
		this.userRole = userRole;
	}


	public String getCenterEmailId() {
		return centerEmailId;
	}

	public String getPassword() {
		return password;
	}

	public String getCenterName() {
		return centerName;
	}

	public long getCenterContactNo() {
		return centerContactNo;
	}

	public String getScBranch() {
		return scBranch;
	}

	public Address getScAddress() {
		return scAddress;
	}

	public List<String> getProductCategory() {
		return productCategory;
	}

	public List<ReviewAndRating> getReviewAndRating() {
		return reviewAndRating;
	}

	public float getAvgRating() {
		return avgRating;
	}

	public Roles getUserRole() {
		return userRole;
	}

	public void setCenterEmailId(String centerEmailId) {
		this.centerEmailId = centerEmailId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public void setCenterContactNo(long centerContactNo) {
		this.centerContactNo = centerContactNo;
	}

	public void setScBranch(String scBranch) {
		this.scBranch = scBranch;
	}

	public void setScAddress(Address scAddress) {
		this.scAddress = scAddress;
	}

	public void setProductCategory(List<String> productCategory) {
		this.productCategory = productCategory;
	}

	public void setReviewAndRating(List<ReviewAndRating> reviewAndRating) {
		this.reviewAndRating = reviewAndRating;
	}

	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}

	public void setUserRole(Roles userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "ServiceCenter{" +
				"centerEmailId='" + centerEmailId + '\'' +
				", password='" + password + '\'' +
				", centerName='" + centerName + '\'' +
				", centerContactNo=" + centerContactNo +
				", scBranch='" + scBranch + '\'' +
				", scAddress=" + scAddress +
				", productCategory=" + productCategory +
				", reviewAndRating=" + reviewAndRating +
				", avgRating=" + avgRating +
				", userRole=" + userRole +
				'}';
	}
}










