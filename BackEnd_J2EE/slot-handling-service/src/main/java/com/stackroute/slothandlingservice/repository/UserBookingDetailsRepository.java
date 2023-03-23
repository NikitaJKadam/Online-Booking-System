package com.stackroute.slothandlingservice.repository;

import com.stackroute.slothandlingservice.model.UserBookingDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserBookingDetailsRepository extends MongoRepository<UserBookingDetails, String> {

    List<UserBookingDetails> findByUserEmail(String email);

    List<UserBookingDetails> findByServiceEmail(String sCEmail);

//    UserBookingDetails findByBookingid(String bookingId);

}
