package com.stackroute.slothandlingservice.repository;

import com.stackroute.slothandlingservice.model.UserSlotBookingDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSlotBookingDetailsRepository extends MongoRepository<UserSlotBookingDetails,String> {


    UserSlotBookingDetails findByDateAndScEmail(String date, String sCEmail);

    UserSlotBookingDetails findByDate(String date);

//    Boolean findByUserSlotBookingId(String userSlotBookingId);
}
