package com.ericcsson.employeedetailsservice.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emptest")
public class TestContorller {
	
	@Autowired
    private Environment env;
	
	@RequestMapping("/mysqlhost")
	public String greeting() {
		if(Objects.isNull(env))
		 return "Env is set to Null";
		
		return Objects.nonNull(env.getProperty("MYSQL_HOST")) ? "Mysql Host Name is " + env.getProperty("MYSQL_HOST")
		 : "No Mysql Env Property";
	}
}
