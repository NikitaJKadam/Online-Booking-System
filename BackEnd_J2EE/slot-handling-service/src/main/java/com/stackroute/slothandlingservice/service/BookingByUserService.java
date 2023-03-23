package com.stackroute.slothandlingservice.service;

import com.stackroute.slothandlingservice.model.UserSlotBookingDetails;
import com.stackroute.slothandlingservice.model.UserBookingDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingByUserService {

    List<UserBookingDetails> getUserDetails(String email) throws Exception;

    List<UserBookingDetails> getServiceCenterDetails(String sCEmail) throws Exception;
    UserBookingDetails createUserBooking(UserBookingDetails bookingByUser);

    Object updateBookingStatus(String bookingId, String price);

}
