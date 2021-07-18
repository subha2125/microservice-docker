package com.ericcsson.employeeinfoservice.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ericcsson.employeeinfoservice.model.EmployeeInfo;
import com.ericcsson.employeeinfoservice.repository.EmployeeInfoRepository;

@RestController
@RequestMapping("/empinfo")
public class EmlpoyeeInfoControl {

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
		return infoRepository.findById(Long.parseLong(id)).orElse(null);
	}

	@PostMapping("/saveEmployee")
	public @ResponseBody EmployeeInfo saveEmployeeDetails(@RequestBody EmployeeInfo employeeInfo) {
		return infoRepository.save(employeeInfo);
	}

	@RequestMapping("/greetings")
	public String employeeDetails() {
		return restTemplate.getForObject("http://employee-details-service:8181/hello", String.class);
	}
}
