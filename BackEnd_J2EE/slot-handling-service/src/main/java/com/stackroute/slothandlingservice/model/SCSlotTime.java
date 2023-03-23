package com.stackroute.slothandlingservice.model;

import com.stackroute.slothandlingservice.enums.SlotAvailability;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "sCSlotTime")
public class SCSlotTime {


    private Long slotStartTime;
    private Long slotEndTime;
    private Boolean isAvailableSlot = Boolean.TRUE;
    private Integer maxNoOfUsersPerSlot;
}
