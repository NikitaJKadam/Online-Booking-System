package com.stackroute.slothandlingservice.controller;

import com.stackroute.slothandlingservice.model.SCSlotCreationMaster;
import com.stackroute.slothandlingservice.model.UserSlotBookingDetails;
import com.stackroute.slothandlingservice.service.UserSlotsBookingDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/v1/api/checkSlot")
public class UserSlotsBookingDetailsController {

    @Autowired
    private UserSlotsBookingDetailsService userSlotsBookingDetailsService;

    @GetMapping(value = "/checkSlot/{date}/{email}")
    public ResponseEntity<UserSlotBookingDetails> getAvailabilityOfSlots(@PathVariable String date, @PathVariable String email){
        UserSlotBookingDetails response = userSlotsBookingDetailsService.getAvailabilityOfSlots(date,email);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
