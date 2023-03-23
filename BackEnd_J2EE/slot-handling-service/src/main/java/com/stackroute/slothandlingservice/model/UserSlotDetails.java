package com.stackroute.slothandlingservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "slotDetails")
public class UserSlotDetails {
    private Long startTime;
    private Long endTime;
    private Integer bookedSlots;
    private Boolean isAvailable = Boolean.TRUE;
}
