package com.stackroute.tickethandlingservice.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "TicketsInfo")
public class TicketHandling implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String ticketId;

	private String ticketDesceription;
	private String modelName;
	private String productCategory;

	private boolean inWarrenty;

	TicketStatus status;

	IssueType issueType;

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}


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

	public boolean isInWarrenty() {
		return inWarrenty;
	}

	public void setInWarrenty(boolean inWarrenty) {
		this.inWarrenty = inWarrenty;
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

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}



}