package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.ServiceCenter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GsRepo extends MongoRepository<ServiceCenter, String> {

}
