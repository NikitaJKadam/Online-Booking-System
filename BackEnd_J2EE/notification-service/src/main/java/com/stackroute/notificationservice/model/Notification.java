package com.stackroute.notificationservice.model;

import com.stackroute.notificationservice.tickethandlingdto.TicketHandlingDto;

public class Notification {
	
	private String toUser;
    private String toCenter;
    private String msgBody;
    private String subject;
    
    private TicketHandlingDto handlingDto;
    
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getToCenter() {
		return toCenter;
	}
	public void setToCenter(String toCenter) {
		this.toCenter = toCenter;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public TicketHandlingDto getHandlingDto() {
		return handlingDto;
	}
	public void setHandlingDto(TicketHandlingDto handlingDto) {
		this.handlingDto = handlingDto;
	}
    
    

}
