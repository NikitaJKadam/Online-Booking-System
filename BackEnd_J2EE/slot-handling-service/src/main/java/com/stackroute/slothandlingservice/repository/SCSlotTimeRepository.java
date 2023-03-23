package com.stackroute.slothandlingservice.repository;

import com.stackroute.slothandlingservice.model.SCSlotTime;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SCSlotTimeRepository extends MongoRepository<SCSlotTime,String> {
}
