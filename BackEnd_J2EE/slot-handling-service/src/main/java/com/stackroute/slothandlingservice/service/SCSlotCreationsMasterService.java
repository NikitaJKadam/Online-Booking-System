package com.stackroute.slothandlingservice.service;

import com.stackroute.slothandlingservice.model.SCSlotCreationMaster;
import org.springframework.stereotype.Service;

@Service
public interface SCSlotCreationsMasterService {

    SCSlotCreationMaster getSlotMasterDetailsByDateAndEmail(String centerEmailId, String slotDate);

    SCSlotCreationMaster addOrUpdateSlot(SCSlotCreationMaster scSlotCreationDetails);

}
