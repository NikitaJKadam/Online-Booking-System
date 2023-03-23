package com.stackroute.authenticationservice.serviceimpl;

import com.stackroute.authenticationservice.entity.User;
import com.stackroute.authenticationservice.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthenticationRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = repository.findByEmail(userName);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().toString());    //added extra
        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();              //added extra
        updatedAuthorities.add(authority);                                                                      //added extra

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), updatedAuthorities);
    }





}

