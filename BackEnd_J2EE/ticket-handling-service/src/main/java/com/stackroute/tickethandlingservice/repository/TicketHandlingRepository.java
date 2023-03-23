package com.stackroute.tickethandlingservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.tickethandlingservice.model.TicketHandling;

@Repository
public interface TicketHandlingRepository extends MongoRepository<TicketHandling, String> {
	
	   List<TicketHandling> findByUserEmail(String userEmail);
	   List<TicketHandling> findByCenterEmail(String centerEmail);




}
