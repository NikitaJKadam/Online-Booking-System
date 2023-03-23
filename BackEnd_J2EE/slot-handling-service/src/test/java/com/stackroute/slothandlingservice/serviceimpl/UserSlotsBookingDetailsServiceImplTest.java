package com.stackroute.slothandlingservice.serviceimpl;

import com.stackroute.slothandlingservice.model.SCSlotCreationMaster;
import com.stackroute.slothandlingservice.model.UserSlotBookingDetails;
import com.stackroute.slothandlingservice.repository.SCSlotCreationMasterDetailsRepository;
import com.stackroute.slothandlingservice.repository.UserSlotBookingDetailsRepository;
import com.stackroute.slothandlingservice.service.impl.SCSlotCreationsServiceImpl;
import com.stackroute.slothandlingservice.service.impl.UserSlotsBookingDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSlotsBookingDetailsServiceImplTest {

    @Mock
    private UserSlotBookingDetailsRepository slotBookingDetailsRepository;

    @Mock
    private SCSlotCreationMasterDetailsRepository scSlotCreationMasterDetailsRepository;

    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    UserSlotsBookingDetailsServiceImpl userSlotsBookingDetailsService;

    @BeforeEach
    void  setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAvailabilityOfSlots(){
        UserSlotBookingDetails userSlotBookingDetails = new UserSlotBookingDetails();
//        List<UserSlotBookingDetails> slotBookingDetailsList = new ArrayList<>();

        userSlotBookingDetails.setDate("12122022");
        userSlotBookingDetails.setScEmail("service.com");

        SCSlotCreationMaster scSlotCreationMaster = new SCSlotCreationMaster();
        scSlotCreationMaster.setCenterEmailId("service.com");
        scSlotCreationMaster.setSlotDate("12122022");

        Mockito.when(slotBookingDetailsRepository.findByDateAndScEmail("12122022","service.com")).thenReturn(userSlotBookingDetails);
        assertEquals(userSlotBookingDetails,userSlotsBookingDetailsService.getAvailabilityOfSlots("12122022","service.com"));

        Mockito.when(scSlotCreationMasterDetailsRepository.findFirstBySlotDateAndCenterEmailId("12122022","service.com")).thenReturn(scSlotCreationMaster);
        assertEquals(userSlotBookingDetails,userSlotsBookingDetailsService.getAvailabilityOfSlots("12122022","service.com"));
    }



}
