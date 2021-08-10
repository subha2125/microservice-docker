package com.spring.api.test;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.spring.api.controller.SpringSecurityController;
import com.spring.api.repository.PersonRepository;
import com.spring.api.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(SpringSecurityController.class)
public class SpringSecurityUnitTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService service;
	
	@MockBean
	private PersonRepository personRepository;
	
	
	
	@Test
	public void testgetAllPerson() throws Exception {
		when(personRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.get("/getAll").accept(MediaType.APPLICATION_JSON)
				.with(user("javatechie").password("pws")))
				.andReturn();
		
		System.out.print(result.getResponse());
		
		Mockito.verify(personRepository).findAll();
	}
	
	
}
