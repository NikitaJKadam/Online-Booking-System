package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.entity.User;

public interface UserAuthenticationService {

	public User addUser(User user);
	public User updatePassword(User user);
}
