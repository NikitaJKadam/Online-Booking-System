package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.entity.AuthRequest;
import com.stackroute.authenticationservice.entity.User;
import com.stackroute.authenticationservice.exception.InvalidCredentialException;
import com.stackroute.authenticationservice.service.UserAuthenticationService;
import com.stackroute.authenticationservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
//@CrossOrigin(origins="http://localhost:3001")

public class UserAuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthenticationService userService;

    @GetMapping("/welcome")
    public String welcome() {

        return "Testing url.";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        //Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(authRequest.getUserRole().toString());
        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        updatedAuthorities.add(authority);
        //updatedAuthorities.addAll(oldAuthorities);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword(),updatedAuthorities)
            );
        } catch (Exception ex) {
            throw new InvalidCredentialException("Invalid username or password");
        }
        return jwtUtil.generateToken(authRequest.getEmail());
    }

    @PostMapping(value = "/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        System.out.println("In the post mapping");
        User returnedUser = userService.addUser(user);
        return new ResponseEntity<User>(returnedUser, HttpStatus.OK);
    }

//    @PutMapping(value = "/updatePassword")
//    public ResponseEntity<User> updateUserPassword(@RequestBody User user){
//        System.out.println("In the put mapping");
//        User returnedUser = userService.updatePassword(user);
//        return new ResponseEntity<User>(returnedUser, HttpStatus.OK);
//    }

    /*@GetMapping({"/forService"})
    @PreAuthorize("hasRole('SERVICE')")
    public String forAdmin() {
        return "This URL is only accessible to the Service";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('USER')")
    public String forService() {
        return "This URL is only accessible to the User";
    }*/
}
