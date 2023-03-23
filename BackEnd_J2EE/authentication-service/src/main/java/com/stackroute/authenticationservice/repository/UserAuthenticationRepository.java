package com.stackroute.authenticationservice.repository;

import com.stackroute.authenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAuthenticationRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String username);

}
