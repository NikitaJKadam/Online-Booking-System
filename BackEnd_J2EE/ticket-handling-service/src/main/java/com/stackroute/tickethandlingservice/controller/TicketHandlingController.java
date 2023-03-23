package com.stackroute.tickethandlingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.tickethandlingservice.model.TicketHandling;
import com.stackroute.tickethandlingservice.service.TicketHandlingService;

@RestController
@RequestMapping(value ="/v1/api")
public class TicketHandlingController {

	@Autowired
	TicketHandlingService handlingService;

	//@CrossOrigin(origins="http://localhost:3000")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/saveTicket")
	public ResponseEntity<Object> saveTicket(@RequestBody TicketHandling handling)
	{
		Object response = handlingService.saveTicket(handling);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	//@CrossOrigin(origins="http://localhost:3000")
	@GetMapping(value ="/getallTickets")
	public List<TicketHandling> getallTickets()
	{
		return handlingService.getallTickets();
	}

	//@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/getTicketsbyUser/{userEmail}")
	public ResponseEntity<List<TicketHandling>> getTicketDetailsForUser(@PathVariable String userEmail) {
		return new ResponseEntity<>(handlingService.getTicketsbyUser(userEmail), HttpStatus.OK);
	}

	//@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/getTicketsbyCenter/{centerEmail}")
	public ResponseEntity<List<TicketHandling>> getTicketDetailsForCenter(@PathVariable String centerEmail) {
		return new ResponseEntity<>(handlingService.getTicketsbyCenter(centerEmail), HttpStatus.OK);
	}

	//@CrossOrigin(origins="http://localhost:3000")
	@PutMapping(value = "/updateTicketStatus/{ticketId}")
	public ResponseEntity<Object> updateTicketStatus(@PathVariable String ticketId) {
		Object response = handlingService.updateTicketStatus( ticketId);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
