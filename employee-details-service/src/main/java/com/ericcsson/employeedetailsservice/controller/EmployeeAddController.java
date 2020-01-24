package com.ericcsson.employeedetailsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ericcsson.employeedetailsservice.model.Role;
import com.ericcsson.employeedetailsservice.model.User;
import com.ericcsson.employeedetailsservice.repository.RoleRepository;
import com.ericcsson.employeedetailsservice.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class EmployeeAddController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@CrossOrigin("*")
	@PostMapping("/saveUser")
	public @ResponseBody User saveUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@CrossOrigin("*")
	@PostMapping("/saveRole")
	public @ResponseBody Role saveRole(@RequestBody Role role) {
		return roleRepository.save(role);
	}
	
	@GetMapping("/hello")
	public String helloUser() {
		return "Hello User";
	}
}
