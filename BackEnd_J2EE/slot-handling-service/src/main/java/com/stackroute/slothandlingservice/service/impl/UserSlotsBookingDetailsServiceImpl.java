package com.stackroute.slothandlingservice.service.impl;

import com.stackroute.slothandlingservice.exception.BookingByUserException;
import com.stackroute.slothandlingservice.exception.UserSlotBookingException;
import com.stackroute.slothandlingservice.model.SCSlotCreationMaster;
import com.stackroute.slothandlingservice.model.UserSlotBookingDetails;
import com.stackroute.slothandlingservice.repository.SCSlotCreationMasterDetailsRepository;
import com.stackroute.slothandlingservice.repository.UserSlotBookingDetailsRepository;
import com.stackroute.slothandlingservice.service.UserSlotsBookingDetailsService;
import com.stackroute.slothandlingservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class UserSlotsBookingDetailsServiceImpl implements UserSlotsBookingDetailsService {

    Logger log = LoggerFactory.getLogger(UserSlotsBookingDetailsServiceImpl.class);

    @Autowired
    private UserSlotBookingDetailsRepository slotBookingDetailsRepository;

    @Autowired
    private SCSlotCreationMasterDetailsRepository scSlotCreationMasterDetailsRepository;

    @Autowired
    Environment environment;

    @Override
    public UserSlotBookingDetails getAvailabilityOfSlots(String date, String email) {
        try {
            log.info("Inside getAvailabilityOfSlots");
            Optional<UserSlotBookingDetails> slotBookingDetails = Optional.ofNullable(slotBookingDetailsRepository.findByDateAndScEmail(date, email));
            if (slotBookingDetails.isPresent())
                return slotBookingDetails.get();
            else {
                Optional<SCSlotCreationMaster> scSlotCreationMasterDetails = Optional.ofNullable(scSlotCreationMasterDetailsRepository.findFirstBySlotDateAndCenterEmailId(date,email));
                if (scSlotCreationMasterDetails.isPresent())
                    return new UserSlotBookingDetails();
            }
            throw new RuntimeException("No Slots has been created for the booking date");

        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Exception caught in getAvailabilityOfSlots :{0}", e);
            log.error(errorMsg);
            throw new UserSlotBookingException(environment.getProperty(Constants.GET_AVAILABLE_SLOT_EXCEPTION),
                    e.getMessage());

        }

    }
}
