package com.stackroute.authenticationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;



@RestControllerAdvice
public class GlobalExceptionHandler extends Exception {

	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<ErrorInfo> handleExceptionForIdNotFound(InvalidCredentialException exception){

		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode("404");
		errorInfo.setErrorMessage(exception.getMessage());
		errorInfo.setTime(LocalDate.now());
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
	}
}
