package com.ericcsson.employeedetailsservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ericcsson.employeedetailsservice.controller.EmployeeDetailController;
import com.ericcsson.employeedetailsservice.model.User;
import com.ericcsson.employeedetailsservice.repository.UserRepository;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(EmployeeUserDetailsService.class);
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		EmployeeUserDetails userDetails = null;
		if (user != null) {
			logger.debug("User found with name= "+ user.getUsername() + user.getPassword() 
			   +"Roles.." );
			user.getRoles().stream().forEach(role -> logger.debug(role.getRole()));
			userDetails = new EmployeeUserDetails();
			userDetails.setUser(user);
		} else {
			logger.error("User Not found with" + username);
			throw new UsernameNotFoundException("User not exist with name : " + username);
		}
		return userDetails;

	}

}
