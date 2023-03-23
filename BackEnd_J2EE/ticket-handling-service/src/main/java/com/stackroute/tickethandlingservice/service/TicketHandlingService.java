package com.stackroute.tickethandlingservice.service;

import java.util.List;

import com.stackroute.tickethandlingservice.model.TicketHandling;

public interface TicketHandlingService {
	
	public Object saveTicket(TicketHandling handling);
	public List<TicketHandling> getallTickets();
    public List<TicketHandling> getTicketsbyUser(String userEmail);
    public List<TicketHandling> getTicketsbyCenter(String centerEmail);
	public Object updateTicketStatus( String ticketId);



}
