package com.stackroute.tickethandlingservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.stackroute.tickethandlingservice.model.TicketHandling;
import com.stackroute.tickethandlingservice.model.TicketStatus;


@ExtendWith(SpringExtension.class)
@DataMongoTest
class TicketHandlingRepositoryTest {

	@Mock
	@Autowired
	TicketHandlingRepository handlingRepositorytest;



	@Test
	void saveTicketTestRepo()
	{
		TicketHandling handling = new TicketHandling();

		handling.setTicketId("abc");
		handling.setUserEmail("testuser1@gmail.com");
		handling.setCenterEmail("testcenter1@gmail.com");
		handling.setInWarrenty(false);
		handling.setModelName("Poco M4");
		handling.setStatus(TicketStatus.OPEN);
		handling.setTicketDesceription("heating issue");

		TicketHandling handling2=handlingRepositorytest.save(handling);
		assertEquals(handling,handling2);	

		handlingRepositorytest.delete(handling);
		handlingRepositorytest.delete(handling2);


	}

	@Test
	void getTicketsbyUserTestRepo()
	{
		TicketHandling handling = new TicketHandling();
		handling.setUserEmail("testuser2@gmail.com");

		handling.setCenterEmail("testcenter2@gmail.com");
		handling.setInWarrenty(false); handling.setModelName("Hp Elitebook");
		handling.setStatus(TicketStatus.CLOSE);
		handling.setTicketDesceription("Power issue");
		handlingRepositorytest.save(handling);

		TicketHandling handling2=handlingRepositorytest.findByUserEmail("testuser2@gmail.com").get(0);
		assertThat(handling2).isNotNull();		

		handlingRepositorytest.delete(handling);
		handlingRepositorytest.delete(handling2);

	}

	@Test
	void getTicketsbyCenterTestRepo()
	{

		TicketHandling handling = new TicketHandling();
		handling.setUserEmail("testuser2@gmail.com");
		handling.setCenterEmail("testcenter2@gmail.com");
		handling.setInWarrenty(false);
		handling.setModelName("Hp Elitebook");
		handling.setStatus(TicketStatus.CLOSE);
		handling.setTicketDesceription("Power issue");		
		handlingRepositorytest.save(handling);

		TicketHandling handling2=handlingRepositorytest.findByCenterEmail("testcenter2@gmail.com").get(0);
		assertThat(handling2).isNotNull();

		handlingRepositorytest.delete(handling);
		handlingRepositorytest.delete(handling2); 

	}


	@Test
	void updateTicketStatusTest()
	{
		TicketHandling handling = new TicketHandling();
		handling.setTicketId("id1");

		handling.setStatus(TicketStatus.OPEN);
		TicketHandling before=handlingRepositorytest.save(handling);
		assertEquals(TicketStatus.OPEN, before.getStatus());

		handling.setStatus(TicketStatus.CLOSE);
		TicketHandling after=handlingRepositorytest.save(handling);
		assertEquals(TicketStatus.CLOSE, after.getStatus());


		assertEquals(before.getTicketId(), after.getTicketId());	

		handlingRepositorytest.delete(handling);
		handlingRepositorytest.delete(before);
		handlingRepositorytest.delete(after);

	}


}
