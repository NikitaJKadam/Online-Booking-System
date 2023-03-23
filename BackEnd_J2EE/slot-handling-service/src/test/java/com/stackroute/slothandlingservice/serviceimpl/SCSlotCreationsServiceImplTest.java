package com.stackroute.slothandlingservice.serviceimpl;

import com.stackroute.slothandlingservice.model.SCSlotCreationMaster;
import com.stackroute.slothandlingservice.model.SCSlotTime;
import com.stackroute.slothandlingservice.repository.SCSlotCreationMasterDetailsRepository;
import com.stackroute.slothandlingservice.service.impl.SCSlotCreationsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SCSlotCreationsServiceImplTest {


    @Mock
    SCSlotCreationMasterDetailsRepository scSlotCreationMasterDetailsRepository;

    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    SCSlotCreationsServiceImpl scSlotCreationsService;

    @BeforeEach
    void  setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSlotMasterDetailsByDateAndEmail(){
        SCSlotCreationMaster scSlotCreationMaster = new SCSlotCreationMaster();
        List<SCSlotTime> slotTimeList = new ArrayList<>();
        SCSlotTime slotTime = new SCSlotTime();
        scSlotCreationMaster.setSlotDate("12122022");
        scSlotCreationMaster.setSlotCreationId(1234L);
        scSlotCreationMaster.setCenterEmailId("service.com");
//        slotTime.setSCSlotId(789L);
        slotTime.setSlotStartTime(1200L);
        slotTime.setSlotEndTime(1000L);
        slotTime.setMaxNoOfUsersPerSlot(3);
        slotTimeList.add(slotTime);
        scSlotCreationMaster.setSlotTime(slotTimeList);

            Mockito.when(scSlotCreationMasterDetailsRepository.findFirstBySlotDateAndCenterEmailId("12122022","service.com")).thenReturn(scSlotCreationMaster);
        assertEquals(scSlotCreationMaster,scSlotCreationsService.getSlotMasterDetailsByDateAndEmail("service.com","12122022"));
    }

    @Test
    void addOrUpdateSlot(){
        SCSlotCreationMaster scSlotCreationMaster = new SCSlotCreationMaster();
        List<SCSlotTime> slotTimeList = new ArrayList<>();
        SCSlotTime slotTime = new SCSlotTime();
        scSlotCreationMaster.setSlotDate("12122022");
        scSlotCreationMaster.setSlotCreationId(1234L);
        scSlotCreationMaster.setCenterEmailId("service.com");
//        slotTime.setSCSlotId(789L);
        slotTime.setSlotStartTime(1200L);
        slotTime.setSlotEndTime(1000L);
        slotTime.setMaxNoOfUsersPerSlot(3);
        slotTimeList.add(slotTime);
        scSlotCreationMaster.setSlotTime(slotTimeList);

        Mockito.when(scSlotCreationMasterDetailsRepository.save(scSlotCreationMaster)).thenReturn(scSlotCreationMaster);
        assertEquals(scSlotCreationMaster,scSlotCreationsService.addOrUpdateSlot(scSlotCreationMaster));

    }





}
