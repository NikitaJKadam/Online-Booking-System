package com.stackroute.notificationservice.tickethandlingdto;

public class TicketHandlingDto {
	
	private String ticketId;
	private String ticketDesceription;
	private String modelName;
	private String productCategory;

	private String userEmail;
	private String centerEmail;
	
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketDesceription() {
		return ticketDesceription;
	}
	public void setTicketDesceription(String ticketDesceription) {
		this.ticketDesceription = ticketDesceription;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCenterEmail() {
		return centerEmail;
	}
	public void setCenterEmail(String centerEmail) {
		this.centerEmail = centerEmail;
	}

	

}
