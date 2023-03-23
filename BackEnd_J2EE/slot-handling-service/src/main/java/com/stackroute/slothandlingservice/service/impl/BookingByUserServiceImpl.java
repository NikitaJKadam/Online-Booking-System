package com.stackroute.slothandlingservice.service.impl;

import com.stackroute.slothandlingservice.enums.BookingStatus;
import com.stackroute.slothandlingservice.exception.BookingByUserException;
import com.stackroute.slothandlingservice.model.*;
import com.stackroute.slothandlingservice.producer.Producer;
import com.stackroute.slothandlingservice.repository.SCSlotCreationMasterDetailsRepository;
import com.stackroute.slothandlingservice.repository.SCSlotTimeRepository;
import com.stackroute.slothandlingservice.repository.UserBookingDetailsRepository;
import com.stackroute.slothandlingservice.repository.UserSlotBookingDetailsRepository;
import com.stackroute.slothandlingservice.service.BookingByUserService;
import com.stackroute.slothandlingservice.util.Constants;
import com.stackroute.slothandlingservice.util.UtilMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;


@Service
public class BookingByUserServiceImpl implements BookingByUserService {

    Logger log = LoggerFactory.getLogger(BookingByUserServiceImpl.class);

    @Autowired
    private UserBookingDetailsRepository userBookingDetailsRepository;

    @Autowired
    private UserSlotBookingDetailsRepository userSlotBookingDetailsRepository;

    @Autowired
    private SCSlotCreationMasterDetailsRepository scSlotCreationMasterDetailsRepository;

    @Autowired
    private SCSlotTimeRepository scSlotTimeRepository;

    @Autowired
    Environment environment;

    @Autowired
    Producer producer;

    @Override
    public List<UserBookingDetails> getUserDetails(String email) throws Exception {
        try {
            log.info("Inside getUserDetails");
            return userBookingDetailsRepository.findByUserEmail(email);

        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Exception caught in getUserDetails :{0}", e);
            log.error(errorMsg);
            throw new BookingByUserException(environment.getProperty(Constants.USER_DOES_NOT_EXIST),
                    e.getMessage());

        }

    }

    @Override
    public List<UserBookingDetails> getServiceCenterDetails(String sCEmail) throws Exception {
        try {
            log.info("Inside getServiceCenterDetails");
            return userBookingDetailsRepository.findByServiceEmail(sCEmail);

        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Exception caught in getServiceCenterDetails :{0}", e);
            log.error(errorMsg);
            throw new BookingByUserException(environment.getProperty(Constants.SERVICE_DOES_NOT_EXIST),
                    e.getMessage());
        }
    }

    @Override
    public UserBookingDetails createUserBooking(UserBookingDetails bookingByUser) {
        try {
            log.info("Inside createUserBooking");
            String slotDate = bookingByUser.getSlotDate();
            Long slotStartTime = bookingByUser.getSlotStartTime();
            String email = bookingByUser.getServiceEmail();
            Random random = new Random();
            /*int randomNumber = random.nextInt(900) + 100;
            String bookingId = String.valueOf(randomNumber);*/
            bookingByUser.setBookingId(UtilMethods.generateRandomId());
            Optional<UserSlotBookingDetails> userSlotBookingDetails = Optional.ofNullable(new UserSlotBookingDetails());
            Optional<SCSlotCreationMaster> scSlotCreationMasterDetails = Optional.ofNullable(scSlotCreationMasterDetailsRepository.findFirstBySlotDateAndCenterEmailId(slotDate, email));
            UserBookingDetails userBookingDetails = userBookingDetailsRepository.save(bookingByUser);
            if (scSlotCreationMasterDetails.isPresent()) {
                List<SCSlotTime> scSlotTime = scSlotCreationMasterDetails.get().getSlotTime();
                userSlotBookingDetails = Optional.ofNullable(userSlotBookingDetailsRepository.findByDate(slotDate));
                if (userSlotBookingDetails.isPresent()) {
                    if (userSlotBookingDetails.get().getSlotDetails().stream().anyMatch(r -> r.getStartTime().equals(slotStartTime))) {
                        UserSlotBookingDetails entity = userSlotBookingDetails.get();

                        Integer max = scSlotCreationMasterDetails.get().getSlotTime()
                                .stream().filter(r -> Objects.equals(r.getSlotStartTime(), slotStartTime)).map(SCSlotTime::getMaxNoOfUsersPerSlot).findAny().get();
                        UserSlotDetails slotDetail = userSlotBookingDetails.get().getSlotDetails().stream().filter(r -> Objects.equals(r.getStartTime(), slotStartTime)).findAny().get();
                        Integer bookedSlots = slotDetail.getBookedSlots();
                        if (bookedSlots < max) {
                            for (UserSlotDetails s : userSlotBookingDetails.get().getSlotDetails()) {
                                if (Objects.equals(s.getStartTime(), slotStartTime))
                                    s.setBookedSlots(++bookedSlots);
                                if (bookedSlots.equals(max)) {
                                    for (SCSlotTime slotTime : scSlotTime) {
                                        if (slotTime.getSlotStartTime().equals(slotStartTime))
                                            slotTime.setIsAvailableSlot(Boolean.FALSE);
                                    }
                                }

                            }
                        }
                        scSlotCreationMasterDetailsRepository.save(scSlotCreationMasterDetails.get());
                    } else {
                        UserSlotDetails slotDetails = new UserSlotDetails();
                        slotDetails.setStartTime(bookingByUser.getSlotStartTime());
                        slotDetails.setEndTime(bookingByUser.getSlotEndTime());
                        slotDetails.setBookedSlots(1);
                        userSlotBookingDetails.get().getSlotDetails().add(slotDetails);
                    }

                    userSlotBookingDetailsRepository.save(userSlotBookingDetails.get());
                } else {
                    UserSlotBookingDetails slotBookingDetails1 = new UserSlotBookingDetails();
                    List<UserSlotDetails> slotDetails1 = new ArrayList<>();
                    UserSlotDetails slotDetails = new UserSlotDetails();
                    String userSlotBookingId = String.valueOf(random.nextInt(900) + 100);

                    slotDetails.setStartTime(bookingByUser.getSlotStartTime());
                    slotDetails.setEndTime(bookingByUser.getSlotEndTime());
                    slotDetails.setBookedSlots(1);
                    slotDetails1.add(slotDetails);

                    slotBookingDetails1.setId(userSlotBookingId);
                    slotBookingDetails1.setDate(bookingByUser.getSlotDate());
                    slotBookingDetails1.setSlotDetails(slotDetails1);
                    slotBookingDetails1.setScEmail(bookingByUser.getServiceEmail());
                    userSlotBookingDetailsRepository.save(slotBookingDetails1);
                }
            }
            producer.sendMessageToRabbitMq(userBookingDetails);
            return userBookingDetails;

        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Exception caught in createUserBooking :{0}", e);
            log.error(errorMsg);
            throw new BookingByUserException(environment.getProperty(Constants.BOOKING_NOT_CREATED),
                    e.getMessage());
        }

    }

    @Override
    public Object updateBookingStatus(String bookingId, String price) {

        try {
            log.info("inside updateBookingStatus");
            if (!userBookingDetailsRepository.findById(bookingId).isPresent()) {
                log.info("booking does not exist");
                return new BookingByUserException(Constants.USER_DOES_NOT_EXIST, bookingId);
            }
            UserBookingDetails bookingDetailsFromRepo = userBookingDetailsRepository.findById(bookingId).get();
            bookingDetailsFromRepo.setUserBookingStatus(BookingStatus.COMPLETED);
            bookingDetailsFromRepo.setPrice(price);
            //added producer
            producer.sendMessageToRabbitMqTe(bookingDetailsFromRepo);
            return userBookingDetailsRepository.save(bookingDetailsFromRepo);
        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Exception caught in updateBookingStatus :{0}", e);
            log.error(errorMsg);
            throw new BookingByUserException(environment.getProperty(Constants.BOOKING_STATUS_UPDATED_SUCCESSFULLY),
                    e.getMessage());

        }


    }

}
