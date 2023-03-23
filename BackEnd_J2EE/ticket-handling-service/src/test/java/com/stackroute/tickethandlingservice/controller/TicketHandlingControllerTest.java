package com.stackroute.tickethandlingservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.tickethandlingservice.model.TicketHandling;
import com.stackroute.tickethandlingservice.model.TicketStatus;
import com.stackroute.tickethandlingservice.service.TicketHandlingService;

@ExtendWith(MockitoExtension.class)
class TicketHandlingControllerTest {

	@Mock
	TicketHandlingService handlingService;

	@InjectMocks
	TicketHandlingController handlingController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void init()
	{
		mockMvc= MockMvcBuilders.standaloneSetup(handlingController).build();
	}

	@AfterEach
	void cleanUp()
	{
		mockMvc=null;
	}


	private String jsonToString(final Object object)
	{
		String result;
		try {
			ObjectMapper objectMapper=new ObjectMapper();
			String jsonContent=objectMapper.writeValueAsString(object);
			result=jsonContent;
		}catch (JsonProcessingException exception) {

			result="error while converting to string";
		}
		return result;
	}

	@Disabled("Checking")
	@Test
	void saveTicketTest() throws Exception
	{
		TicketHandling ticketHandling=new TicketHandling();
		ticketHandling.setTicketId("abc");
		ticketHandling.setCenterEmail("center@gmail.com");
		ticketHandling.setUserEmail("user@gmail.com");
		when(handlingService.saveTicket(ticketHandling)).thenReturn(ticketHandling);
		assertEquals(handlingService.saveTicket(ticketHandling), ticketHandling);

		mockMvc.perform(get("/v1/api/saveTicket")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(ticketHandling)))
		.andExpect(status().isOk());

		verify(handlingService,times(1)).saveTicket(ticketHandling);
	}

	@Test
	void getTicketsbyUserTest() throws Exception
	{
		List<TicketHandling> handlingList=new ArrayList<>(); 
		TicketHandling handling = new TicketHandling();
		handling.setUserEmail("user@gmail.com");
		handling.setCenterEmail("testcenter1@gmail.com");
		handling.setInWarrenty(false);
		handling.setModelName("Poco M4");
		handling.setStatus(TicketStatus.OPEN);
		handling.setTicketDesceription("heating issue");

		handlingList.add(handling);
		when(handlingService.getTicketsbyUser("user@gmail.com")).thenReturn(handlingList);
		assertEquals(handlingService.getTicketsbyUser("user@gmail.com"),handlingList);

		mockMvc.perform(get("/v1/api/getTicketsbyUser/user@gmail.com")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(handlingList)))
		.andExpect(status().isOk());

		verify(handlingService,times(2)).getTicketsbyUser("user@gmail.com");


	}

	@Test
	void getTicketsbyCenterTest() throws Exception
	{
		List<TicketHandling> handlingList=new ArrayList<>(); 
		TicketHandling handling = new TicketHandling();
		handling.setUserEmail("user@gmail.com");
		handling.setCenterEmail("testcenter1@gmail.com");
		handling.setInWarrenty(false);
		handling.setModelName("Poco M4");
		handling.setStatus(TicketStatus.OPEN);
		handling.setTicketDesceription("heating issue");

		handlingList.add(handling);
		when(handlingService.getTicketsbyCenter("testcenter1@gmail.com")).thenReturn(handlingList);
		assertEquals(handlingService.getTicketsbyCenter("testcenter1@gmail.com"),handlingList);

		mockMvc.perform(get("/v1/api/getTicketsbyCenter/testcenter1@gmail.com")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(handlingList)))
		.andExpect(status().isOk());

		verify(handlingService,times(2)).getTicketsbyCenter("testcenter1@gmail.com");

	}

	@Test
	void updateTicketStatusTest() throws Exception
	{
		TicketHandling ticketHandling=new TicketHandling();
		ticketHandling.setTicketId("abc");
		ticketHandling.setStatus(TicketStatus.CLOSE);

		when(handlingService.updateTicketStatus("abc")).thenReturn(ticketHandling);

		mockMvc.perform(put("/v1/api/updateTicketStatus/abc")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(ticketHandling)))
		.andExpect(status().isOk());

		verify(handlingService,times(1)).updateTicketStatus("abc");

	}



}