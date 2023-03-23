package com.stackroute.slothandlingservice.serviceimpl;

import com.stackroute.slothandlingservice.enums.BookingStatus;
import com.stackroute.slothandlingservice.enums.ServiceProvided;
import com.stackroute.slothandlingservice.model.UserBookingDetails;
import com.stackroute.slothandlingservice.repository.UserBookingDetailsRepository;
import com.stackroute.slothandlingservice.service.BookingByUserService;
import com.stackroute.slothandlingservice.service.impl.BookingByUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingByUserServiceImplTest {

    @Mock
    UserBookingDetailsRepository userBookingDetailsRepository;

    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    BookingByUserServiceImpl bookingByUserService;

    @BeforeEach
    void  setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserDetails() throws Exception {
        List<UserBookingDetails> userBookingDetailsList = new ArrayList<>();
        UserBookingDetails userBookingDetails = new UserBookingDetails();
        userBookingDetails.setUserBookingStatus(BookingStatus.INPROGRESS);
        userBookingDetails.setUserIssueDescription("abcde");
        userBookingDetails.setBookingId("123");
        userBookingDetails.setSlotDate("12345");
        userBookingDetails.setSlotStartTime(1200L);
        userBookingDetails.setSlotEndTime(1000L);
        userBookingDetails.setModelName("nokia");
        userBookingDetails.setInWarrenty(true);
        userBookingDetails.setServiceEmail("service.com");
        userBookingDetails.setUserEmail("user.com");
        userBookingDetails.setProductCategory("Mobile");
        userBookingDetailsList.add(userBookingDetails);

        Mockito.when(userBookingDetailsRepository.findByUserEmail("user.com")).thenReturn(userBookingDetailsList);
        assertEquals(userBookingDetailsList,bookingByUserService.getUserDetails("user.com"));
    }

    @Test
    void getServiceCenterDetails() throws Exception {
        List<UserBookingDetails> userBookingDetailsList = new ArrayList<>();
        UserBookingDetails userBookingDetails = new UserBookingDetails();
        userBookingDetails.setUserBookingStatus(BookingStatus.INPROGRESS);
        userBookingDetails.setUserIssueDescription("abcde");
        userBookingDetails.setBookingId("123");
        userBookingDetails.setSlotDate("12345");
        userBookingDetails.setSlotStartTime(1200L);
        userBookingDetails.setSlotEndTime(1000L);
        userBookingDetails.setModelName("nokia");
        userBookingDetails.setInWarrenty(true);
        userBookingDetails.setServiceEmail("service.com");
        userBookingDetails.setUserEmail("user.com");
        userBookingDetails.setProductCategory("Mobile");
        userBookingDetailsList.add(userBookingDetails);

        Mockito.when(userBookingDetailsRepository.findByServiceEmail("service.com")).thenReturn(userBookingDetailsList);
        assertEquals(userBookingDetailsList,bookingByUserService.getServiceCenterDetails("service.com"));

    }

/*    @Test
    void createUserBooking(){
        UserBookingDetails userBookingDetails = new UserBookingDetails();
        userBookingDetails.setUserBookingStatus(BookingStatus.INPROGRESS);
        userBookingDetails.setUserIssueDescription("abcde");
        userBookingDetails.setBookingId("123");
        userBookingDetails.setSlotDate("12345");
        userBookingDetails.setSlotStartTime(1200L);
        userBookingDetails.setSlotEndTime(1000L);
        userBookingDetails.setModelName("nokia");
        userBookingDetails.setInWarrenty(true);
        userBookingDetails.setServiceEmail("service.com");
        userBookingDetails.setUserEmail("user.com");
        userBookingDetails.setProductCategory(ServiceProvided.MOBILE);


        Mockito.when(.save(userBookingDetails)).thenReturn(userBookingDetails);
        assertEquals(userBookingDetails,bookingByUserService.createUserBooking(userBookingDetails));

    }*/


//    @Test
//    void updateBookingStatus(){
//        UserBookingDetails bookingDetails = new UserBookingDetails();
//        bookingDetails.setBookingId("abc");
//        bookingDetails.setUserBookingStatus(BookingStatus.INPROGRESS);
//
//        Mockito.when(userBookingDetailsRepository.findById("abc")).thenReturn(Optional.of(bookingDetails));
//
//        bookingDetails.setUserBookingStatus(BookingStatus.COMPLETED);
//
//        Mockito.when(userBookingDetailsRepository.save(bookingDetails)).thenReturn(bookingDetails);
//        assertEquals(bookingDetails,bookingByUserService.updateBookingStatus("abc"));
//    }




}
