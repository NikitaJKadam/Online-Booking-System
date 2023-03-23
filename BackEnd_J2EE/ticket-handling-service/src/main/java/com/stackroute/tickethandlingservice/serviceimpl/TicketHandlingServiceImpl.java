package com.stackroute.tickethandlingservice.serviceimpl;


import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.stackroute.tickethandlingservice.exception.TicketHandlingException;
import com.stackroute.tickethandlingservice.model.TicketHandling;
import com.stackroute.tickethandlingservice.model.TicketStatus;
import com.stackroute.tickethandlingservice.producer.Producer;
import com.stackroute.tickethandlingservice.repository.TicketHandlingRepository;
import com.stackroute.tickethandlingservice.service.TicketHandlingService;
import com.stackroute.tickethandlingservice.util.Constants;

@Service
public class TicketHandlingServiceImpl implements TicketHandlingService{

	Logger log = LoggerFactory.getLogger(TicketHandlingServiceImpl.class);

	
	@Autowired
	TicketHandlingRepository handlingRepository;
	
	@Autowired
	Producer producer;
	
	@Autowired
	Environment environment;
	

	private  Random random = new Random();

	@Override
	public Object saveTicket(TicketHandling handling) {
		try {
			log.info("Inside saveTicket");
			handling.setTicketId(generateRandomId());
      		handling.setStatus(TicketStatus.OPEN);
      		producer.sendMessageToRabbitMq(handling);
      		producer.sendMessageToRabbitMqTe(handling);
      		return handlingRepository.save(handling);
		}catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in saveTicket of TicketHandlingServiceImpl :{0}", e);
			log.error(errorMsg);
			throw new TicketHandlingException(e.getMessage(), errorMsg); 
		}
		
		
	}

	@Override
	public List<TicketHandling> getallTickets() {
		return handlingRepository.findAll();
	}



	@Override
	public List<TicketHandling> getTicketsbyUser(String userEmail) {
		try {
			log.info("Inside getTicketsbyUser");
		return handlingRepository.findByUserEmail(userEmail);
		}catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in fetching of ticket :{0}", e);
			log.error(errorMsg);
			throw new TicketHandlingException(environment.getProperty(Constants.DEFAULT_EXCEPTION_CODE),e.getMessage());
	}
		}



	@Override
	public List<TicketHandling> getTicketsbyCenter(String centerEmail) {
		try {
			log.info("Inside getTicketsbyCenter");

		return handlingRepository.findByCenterEmail(centerEmail);
		}catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in fetching of ticket :{0}", e);
			log.error(errorMsg);
			throw new TicketHandlingException(environment.getProperty(Constants.DEFAULT_EXCEPTION_CODE),e.getMessage());
	}
	}



	@Override
	public Object updateTicketStatus( String ticketId) {
		try {
			log.info("Inside updateTicketStatus");
			
			  if(!handlingRepository.findById(ticketId).isPresent()) {
			  log.info("Ticket does not exist"); return new
			  TicketHandlingException(Constants.TICKET_DOES_NOT_EXIST, ticketId); } 
			  TicketHandling  ticketFromRepo = handlingRepository.findById(ticketId).get();
			  ticketFromRepo.setStatus(TicketStatus.CLOSE);
			  producer.sendMessageToRabbitMqTes(ticketFromRepo);
			  return  handlingRepository.save(ticketFromRepo);
		} catch (Exception e) {
			String errorMsg = MessageFormat.format("Exception caught in updateTicketStatus :{0}", e);
			log.error(errorMsg);
			throw new TicketHandlingException(environment.getProperty(Constants.DEFAULT_EXCEPTION_CODE),
					e.getMessage());
		}
	}

	private String generateRandomId(){
		
	    String alphabet = "0123456789";
	    StringBuilder sb = new StringBuilder();
    	sb.append("TK");
	    for(int i = 0; i < 3; i++) {
	    	int index = random.nextInt(alphabet.length());
	    	char randomChar = alphabet.charAt(index);
	    	sb.append(randomChar);
	    }
	   return sb.toString();
	}

}
