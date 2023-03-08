/*package com.stackroute.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.service.UserService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class UserControllerTestcase {

	@Mock
	UserService userService;
	
	@InjectMocks
	UserController UserController;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void init()
	{
		mockMvc= MockMvcBuilders.standaloneSetup(UserController).build();
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
	public void givenUserToSaveReturnSaveUser() throws Exception {
		
		
	}
	
	
	
	
	
	
}*/
