package com.stackroute.userservice.ConfigRabbitmq;

import com.stackroute.userservice.model.Roles;

import lombok.Data;
@Data
public class Userdto {

	
	private String email;
    private String password;
    private Roles userRole;
}
