package com.stackroute.userservice.service;

import com.stackroute.userservice.excepistion.NotFoundException;
import com.stackroute.userservice.excepistion.UserAlredyExcit;
import com.stackroute.userservice.model.User;

public interface UserService {

	public User save(User user) throws UserAlredyExcit;
	public User updateUser(User user) throws NotFoundException;
	public User updatePassword(User password)throws NotFoundException;
	public User EmailId(String emailId)throws NotFoundException;
	
}
