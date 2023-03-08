package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GdRepo extends MongoRepository<User, String> {

}
