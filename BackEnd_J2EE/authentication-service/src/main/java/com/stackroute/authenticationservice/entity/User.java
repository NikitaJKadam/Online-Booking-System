package com.stackroute.authenticationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_table1")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String email;
    private String password;

    private UserRole userRole;


}
