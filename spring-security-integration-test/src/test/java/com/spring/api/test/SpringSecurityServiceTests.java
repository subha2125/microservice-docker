package com.spring.api.test;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.spring.api.entity.Person;
import com.spring.api.repository.PersonRepository;
import com.spring.api.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class SpringSecurityServiceTests {

	@Mock
	private PersonRepository personRepository;
	
	@InjectMocks
	private PersonService service;
	
	@Test
	public void testgetAllPerson() throws Exception {
		List<Person> pList =new ArrayList<>();
		pList.add(new Person(1,"Dhima","Computer"));
		when(personRepository.findAll()).thenReturn(pList);
		service.findAllPersons().forEach(p -> System.out.println("Person="+p));
	}
}
