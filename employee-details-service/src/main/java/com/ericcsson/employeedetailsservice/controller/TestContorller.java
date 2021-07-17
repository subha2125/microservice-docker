package com.ericcsson.employeedetailsservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestContorller {

	@RequestMapping("/hello")
	public String greeting() {
		return "Hello Employee";
	}
}
