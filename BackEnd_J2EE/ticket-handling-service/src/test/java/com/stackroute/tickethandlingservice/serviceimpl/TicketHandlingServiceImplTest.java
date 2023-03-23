package com.stackroute.tickethandlingservice.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.stackroute.tickethandlingservice.model.TicketHandling;
import com.stackroute.tickethandlingservice.model.TicketStatus;
import com.stackroute.tickethandlingservice.producer.Producer;
import com.stackroute.tickethandlingservice.repository.TicketHandlingRepository;


class TicketHandlingServiceImplTest {

	@Mock
	TicketHandlingRepository handlingRepository;

	@Mock
	MongoTemplate mongoTemplate;

	@InjectMocks
	TicketHandlingServiceImpl ticketHandlingServiceImpl;

	@InjectMocks
	Producer producer;

	@BeforeEach
	void  setUp(){
		MockitoAnnotations.openMocks(this);
	}

	@Disabled("Checking")
	@Test
	void saveTicketTest() {
		TicketHandling handling = new TicketHandling();
		handling.setUserEmail("testuser1@gmail.com");
		handling.setCenterEmail("testcenter1@gmail.com");
		handling.setInWarrenty(false);
		handling.setModelName("Poco M4");
		handling.setStatus(TicketStatus.OPEN);
		handling.setTicketDesceription("heating issue");
		producer.sendMessageToRabbitMq(handling);
		Mockito.when(handlingRepository.save(handling)).thenReturn(handling);
		assertEquals(handling,ticketHandlingServiceImpl.saveTicket(handling));

	}

	@Test
	void getTicketsbyUserTest()
	{
		List<TicketHandling> handlingList=new ArrayList<>(); 
		TicketHandling handling = new TicketHandling();
		handling.setCenterEmail("testcenter1@gmail.com");
		handling.setInWarrenty(false);
		handling.setModelName("Poco M4");
		handling.setStatus(TicketStatus.OPEN);
		handling.setTicketDesceription("heating issue");
		handlingList.add(handling);

		Mockito.when(handlingRepository.findByUserEmail("testuser1@gmail.com")).thenReturn(handlingList);
		assertEquals(handlingList,ticketHandlingServiceImpl.getTicketsbyUser("testuser1@gmail.com"));
	}

	@Test
	void getTicketsbyCenterTest()
	{
		List<TicketHandling> handlingList=new ArrayList<>(); 
		TicketHandling handling = new TicketHandling();
		handling.setUserEmail("testuser2@gmail.com");
		handling.setInWarrenty(false);
		handling.setModelName("Hp Elitebook");
		handling.setStatus(TicketStatus.CLOSE);
		handling.setTicketDesceription("Power issue");
		handlingList.add(handling);

		Mockito.when(handlingRepository.findByCenterEmail("testcenter2@gmail.com")).thenReturn(handlingList);
		assertEquals(handlingList,ticketHandlingServiceImpl.getTicketsbyCenter("testcenter2@gmail.com"));
	}	
	
	@Disabled("Checking")
	@Test
	void updateTicketStatusTest()
	{
		TicketHandling handling2 =new TicketHandling();
		handling2.setTicketId("abc");
		handling2.setStatus(TicketStatus.CLOSE);


		Mockito.when(handlingRepository.findById(handling2.getTicketId())).thenReturn(Optional.ofNullable(handling2));
		ticketHandlingServiceImpl.updateTicketStatus(handling2.getTicketId());

		verify(handlingRepository).save(handling2);
	}
}