package com.stackroute.authenticationservice.exception;

public class InvalidCredentialException extends Exception {

	private String message;

	public InvalidCredentialException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
