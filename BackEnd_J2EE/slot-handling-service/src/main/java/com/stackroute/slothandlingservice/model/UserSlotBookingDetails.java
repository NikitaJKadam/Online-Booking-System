package com.stackroute.slothandlingservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "userSlotBookingDetails")
public class UserSlotBookingDetails {
    @Indexed
    private String id;
    private String date;
    private String scEmail;
    private List<UserSlotDetails> slotDetails;
}
