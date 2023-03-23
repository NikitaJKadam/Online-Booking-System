package com.stackroute.tickethandlingservice.exception;

public class TicketHandlingException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private final String code;
	private final String message;
	
	public TicketHandlingException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	

	@Override
	public String getMessage() {
		return message;
	}



	
	}


