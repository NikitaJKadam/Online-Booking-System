package com.stackroute.slothandlingservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "sCSlotCretionMaster")
public class SCSlotCreationMaster {

    @Id
    private String id;
    private Long slotCreationId;
    private String centerEmailId;
    private String slotDate;
    private List<SCSlotTime> slotTime;



}
