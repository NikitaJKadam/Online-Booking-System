package com.stackroute.authenticationservice.serviceimpl;

import com.stackroute.authenticationservice.entity.User;
import com.stackroute.authenticationservice.repository.UserAuthenticationRepository;
import com.stackroute.authenticationservice.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	@Autowired
	private UserAuthenticationRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User addUser(User user) {
		user.setPassword(getEncodedPassword(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User updatePassword(User user) {
		User userFromRepo = userRepository.findByEmail(user.getEmail());
		if (userFromRepo!=null){
			userFromRepo.setEmail(user.getEmail());
			userFromRepo.setPassword(getEncodedPassword(user.getPassword()));
//			userFromRepo.setUserRole(user.getUserRole());
			userRepository.save(userFromRepo);
		}else {
			throw new RuntimeException("user not exits");
		}
		return userFromRepo;
	}


	public String getEncodedPassword(String password) {
		String str = passwordEncoder.encode(password);
		return str;
	}




}

