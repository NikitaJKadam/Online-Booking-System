package com.stackroute.slothandlingservice.service.impl;

import com.stackroute.slothandlingservice.exception.BookingByUserException;
import com.stackroute.slothandlingservice.exception.SCSlotCreationException;
import com.stackroute.slothandlingservice.model.SCSlotCreationMaster;
import com.stackroute.slothandlingservice.repository.SCSlotCreationMasterDetailsRepository;
import com.stackroute.slothandlingservice.service.SCSlotCreationsMasterService;
import com.stackroute.slothandlingservice.util.Constants;
import com.stackroute.slothandlingservice.util.UtilMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class SCSlotCreationsServiceImpl implements SCSlotCreationsMasterService {

    @Autowired
    private SCSlotCreationMasterDetailsRepository scSlotCreationMasterDetailsRepository;

    Logger log = LoggerFactory.getLogger(SCSlotCreationsServiceImpl.class);

    @Autowired
    Environment environment;

    @Override
    public SCSlotCreationMaster getSlotMasterDetailsByDateAndEmail(String centerEmailId, String slotDate) {

        try {
            log.info("Inside getSlotMasterDetailsByDateAndEmail");
            SCSlotCreationMaster listOfBookingWithdate = scSlotCreationMasterDetailsRepository.findFirstBySlotDateAndCenterEmailId(slotDate, centerEmailId);
            return listOfBookingWithdate;

        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Exception caught in getUserDetails :{0}", e);
            log.error(errorMsg);
            throw new SCSlotCreationException(environment.getProperty(Constants.SLOT_MASTER_DETAILS_NOT_FOUND),
                    e.getMessage());

        }

    }

    @Override
    public SCSlotCreationMaster addOrUpdateSlot(SCSlotCreationMaster scSlotCreationDetails) {
        try {
            log.info("Inside getSlotMasterDetailsByDateAndEmail");
            SCSlotCreationMaster scSlotCreationMasterRepo = scSlotCreationMasterDetailsRepository.findFirstBySlotDateAndCenterEmailId(scSlotCreationDetails.getSlotDate(),scSlotCreationDetails.getCenterEmailId());
            if(scSlotCreationMasterRepo != null){

                String id = scSlotCreationMasterRepo.getId();
                scSlotCreationDetails.setId(id);
//            scSlotCreationDetails.setId(UtilMethods.generateRandomId());
//            scSlotCreationDetails.setSlotCreationId(UtilMethods.generateLongRandomId());
                return scSlotCreationMasterDetailsRepository.save(scSlotCreationDetails);

            }else {
                scSlotCreationDetails.setId(UtilMethods.generateRandomId());
                scSlotCreationDetails.setSlotCreationId(UtilMethods.generateLongRandomId());
                return scSlotCreationMasterDetailsRepository.save(scSlotCreationDetails);
            }

        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Exception caught in getUserDetails :{0}", e);
            log.error(errorMsg);
            throw new SCSlotCreationException(environment.getProperty(Constants.SLOT_MASTER_DETAILS_CREATED),
                    e.getMessage());
        }

    }

}
