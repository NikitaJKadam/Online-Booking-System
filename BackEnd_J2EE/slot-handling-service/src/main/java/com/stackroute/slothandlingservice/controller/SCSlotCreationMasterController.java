package com.stackroute.slothandlingservice.controller;

import com.stackroute.slothandlingservice.model.SCSlotCreationMaster;
import com.stackroute.slothandlingservice.service.SCSlotCreationsMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/api/createSlots")
//@CrossOrigin(origins="http://localhost:3000")
public class SCSlotCreationMasterController {

    @Autowired
    SCSlotCreationsMasterService scSlotCreationsMasterService;

    @PutMapping(value = "/addOrUpdateSlot")
    public ResponseEntity<SCSlotCreationMaster> addOrUpdateSlot(@RequestBody SCSlotCreationMaster sCSlotCreationMaster){
        SCSlotCreationMaster response = scSlotCreationsMasterService.addOrUpdateSlot(sCSlotCreationMaster);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/availableSlotMasterDetails/{email}/{slotDate}")
    public ResponseEntity<SCSlotCreationMaster> getSlotMasterDetailsByDateAndEmail(@PathVariable String email, @PathVariable String slotDate) throws  Exception{
        SCSlotCreationMaster response = scSlotCreationsMasterService.getSlotMasterDetailsByDateAndEmail(email, slotDate);
        return new ResponseEntity(response, HttpStatus.OK);
    }



}
