package com.stackroute.slothandlingservice.controller;

import com.stackroute.slothandlingservice.model.UserBookingDetails;
import com.stackroute.slothandlingservice.service.BookingByUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value = "/v1/api/userBooking")
public class BookingByUserController {

    @Autowired
    BookingByUserService bookingByUserService;

    @GetMapping(value = "/userDetails/{email}")
    public ResponseEntity<List<UserBookingDetails>> getUserDetails(@PathVariable String email) throws Exception{
        return new ResponseEntity<>(bookingByUserService.getUserDetails(email),HttpStatus.OK);

    }

    @PostMapping(value = "/bookingByUser")
    public ResponseEntity<UserBookingDetails> getCreateUserBooking(@RequestBody UserBookingDetails bookingByUser) throws Exception{
        UserBookingDetails response = bookingByUserService.createUserBooking(bookingByUser);
        return new ResponseEntity<UserBookingDetails>(response, HttpStatus.OK);

    }

    @GetMapping(value = "/serviceCentreDetails/{sCEmail}")
    public ResponseEntity<List<UserBookingDetails>> getServiceCenterDetails(@PathVariable String sCEmail) throws Exception{
        return new ResponseEntity<>(bookingByUserService.getServiceCenterDetails(sCEmail),HttpStatus.OK);

    }

    @PutMapping(value = "/updateBookingStatus/{bookingId}/{price}")
    public ResponseEntity<Object> updateBookingStatus(@PathVariable String bookingId, @PathVariable String price) {
        Object response = bookingByUserService.updateBookingStatus( bookingId,price);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
