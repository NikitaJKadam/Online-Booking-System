package com.stackroute.authenticationservice.consumer;

import com.stackroute.authenticationservice.entity.User;
import com.stackroute.authenticationservice.serviceimpl.UserAuthenticationServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private UserAuthenticationServiceImpl userAuthenticationService;
    @RabbitListener(queues="user_queue")
    public void getUserDetails(User user){

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setUserRole(user.getUserRole());
        userAuthenticationService.addUser(newUser);
    }

    @RabbitListener(queues="user_queue2")
    public void getDetailsForUpdatePaaword(User user){

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
//        newUser.setUserRole(user.getUserRole());
        userAuthenticationService.updatePassword(newUser);
    }

}
