package com.stackroute.notificationservice.bookingdto;

public class BookingDto {
	
	private String bookingId;
    private String userEmail;
    private String serviceEmail;
    private String userIssueDescription;
    private String productCategory;
    private String modelName;
    private String slotDate;
    private String price;
    private Long slotStartTime;
    private Long slotEndTime;
    
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getServiceEmail() {
		return serviceEmail;
	}
	public void setServiceEmail(String serviceEmail) {
		this.serviceEmail = serviceEmail;
	}
	public String getUserIssueDescription() {
		return userIssueDescription;
	}
	public void setUserIssueDescription(String userIssueDescription) {
		this.userIssueDescription = userIssueDescription;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getSlotDate() {
		return slotDate;
	}
	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Long getSlotStartTime() {
		return slotStartTime;
	}
	public void setSlotStartTime(Long slotStartTime) {
		this.slotStartTime = slotStartTime;
	}
	public Long getSlotEndTime() {
		return slotEndTime;
	}
	public void setSlotEndTime(Long slotEndTime) {
		this.slotEndTime = slotEndTime;
	}

    
    


}
