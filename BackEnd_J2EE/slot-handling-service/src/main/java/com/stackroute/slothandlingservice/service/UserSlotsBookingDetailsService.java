package com.stackroute.slothandlingservice.service;

import com.stackroute.slothandlingservice.model.UserSlotBookingDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserSlotsBookingDetailsService {
    UserSlotBookingDetails getAvailabilityOfSlots(String date, String email);
}
