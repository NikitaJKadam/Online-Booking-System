package com.stackroute.slothandlingservice.repository;

import com.stackroute.slothandlingservice.model.SCSlotCreationMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SCSlotCreationMasterDetailsRepository extends MongoRepository<SCSlotCreationMaster, String> {

    SCSlotCreationMaster findFirstBySlotDateAndCenterEmailId(String slotDate, String centerEmailId);

}
