package com.stackroute.userservice.excepistion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class UserAlredyExcit   extends Exception {
    private static final long serialVersionUID = 1L;

    public UserAlredyExcit() {
    	
    }
    
}
