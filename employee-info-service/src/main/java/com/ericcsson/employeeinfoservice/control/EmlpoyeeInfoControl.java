package com.ericcsson.employeeinfoservice.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ericcsson.employeeinfoservice.model.EmployeeInfo;
import com.ericcsson.employeeinfoservice.repository.EmployeeInfoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/empinfo")
@Lazy
public class EmlpoyeeInfoControl {
	
	Logger logger = LoggerFactory.getLogger(EmlpoyeeInfoControl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	EmployeeInfoRepository infoRepository;

	@RequestMapping("/info")
	public List<EmployeeInfo> getAllDetails() {
		return infoRepository.findAll();
	}

	@RequestMapping("/info/{id}")
	public EmployeeInfo employeeDetails(@PathVariable String id) {
		logger.info("EmployeeInfo: called with id {} :", id);
		return infoRepository.findById(Long.parseLong(id)).orElse(null);
	}

	@PostMapping("/saveEmployee")
	public @ResponseBody EmployeeInfo saveEmployeeDetails(@RequestBody EmployeeInfo employeeInfo) {
		try {
			logger.info("EmployeeInfo Request : {}",new ObjectMapper().writeValueAsString(employeeInfo));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return infoRepository.save(employeeInfo);
	}

	@RequestMapping("/greetings")
	public String employeeDetails() {
		logger.info("EmployeeInfo: greetings");
		return restTemplate.getForObject("http://employee-details-service:8181/hello", String.class);
	}
	
	@RequestMapping("/hello/{messgae}")
	public String hello(@PathVariable String messgae) {
		logger.info("EmployeeInfo: greetings :{}",messgae);
		return "greetings:"+messgae;
	}
}
