package com.spring.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.entity.Person;
import com.spring.api.repository.PersonRepository;

@RestController
public class SpringSecurityController {

	@Autowired
	private PersonRepository repository;
	
	@GetMapping("/getAll")
	public List<Person> getPersons() {
		System.out.println("Controller getPersons() method called...");
		return repository.findAll();
	}
}
