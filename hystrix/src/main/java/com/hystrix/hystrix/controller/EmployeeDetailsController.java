package com.hystrix.hystrix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hystrix.hystrix.models.EmployeeDetails;
import com.hystrix.hystrix.service.EmployeeDetailsService;

@RestController
@RequestMapping("/hystrixempdetails")
public class EmployeeDetailsController {

	@Autowired
	private EmployeeDetailsService employeeDetailsService;
	
	@CrossOrigin("*")
	@GetMapping("/{userId}")
	public List<EmployeeDetails> getEmployeeDetails(){
		return employeeDetailsService.getAllDetails();
	} 
}
