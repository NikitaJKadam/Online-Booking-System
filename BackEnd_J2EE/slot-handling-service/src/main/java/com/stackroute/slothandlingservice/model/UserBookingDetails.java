package com.stackroute.slothandlingservice.model;

import com.stackroute.slothandlingservice.enums.BookingStatus;
import com.stackroute.slothandlingservice.enums.ServiceProvided;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;



@Data
@Document(collection = "userBookingDetails")
public class UserBookingDetails {

    @Id
    private String bookingId;
    private String userEmail;
    private String serviceEmail;
    private String userIssueDescription;
    private String productCategory;
    private String modelName;
    private Boolean inWarrenty;
    private BookingStatus userBookingStatus;
    private Long slotStartTime;
    private Long slotEndTime;
    private String slotDate;
    private String price;
}
