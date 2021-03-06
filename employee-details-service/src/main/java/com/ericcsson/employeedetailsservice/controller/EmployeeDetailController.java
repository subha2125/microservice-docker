package com.ericcsson.employeedetailsservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ericcsson.employeedetailsservice.model.EmployeeDetails;
import com.ericcsson.employeedetailsservice.model.EmployeeInfo;
import com.ericcsson.employeedetailsservice.model.EmployeeSalary;
import com.ericcsson.employeedetailsservice.model.EmployeeSalaryList;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/empdetails")
public class EmployeeDetailController {

	Logger logger = LoggerFactory.getLogger(EmployeeDetailController.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    private KafkaTemplate<String, List<EmployeeDetails>> kafkaTemplate;

	@Value("${kafka.topic}")
    private String topic;
	
	
	@GetMapping("/getAllEmployeeDetails")
	@ApiOperation(value="Get All Emp Details",response=EmployeeDetails.class)
	public List<EmployeeDetails> getAllDetails() {
		List<EmployeeDetails> empDetailsList = new ArrayList<EmployeeDetails>();
		logger.info("EmployeeDetail Service called :");
		logger.info("Calling to employee-salary-service start");
		EmployeeSalaryList empsalAll = restTemplate.getForObject("http://employee-salary-service/empsalary/salary/",
				EmployeeSalaryList.class);
		logger.info("Call to employee-salary-service finished");

		for( EmployeeSalary empsal : empsalAll.getEmployeesalaryList()){
			EmployeeInfo empinfo = restTemplate.getForObject("http://employee-info-service/empinfo/info/"+empsal.getId(), EmployeeInfo.class);
			
            if(empinfo != null) {
            	logger.info("EmployeeDetail Service Employee found with details as .. " + empinfo.toString());
			 empDetailsList.add(new EmployeeDetails(empinfo.getId(), empinfo.getName(), empinfo.getJobStage(), empinfo.getJobRole(),
					empinfo.getSkills(), empinfo.getHobbies(), empsal.getSalary()));
            }else {
            	logger.info("EmployeeDetail Service No Emp Found with ID.. " + empsal.getId());
            }
		}
		
		logger.info("Sending to Kafka Broker");
		kafkaTemplate.send(topic, empDetailsList);
		
		return empDetailsList;

	}
	 

	@RequestMapping("/{id}")
	public EmployeeDetails employeeDetails(@PathVariable int id) {
		EmployeeInfo empinfo = restTemplate.getForObject("http://employee-info-service/empinfo/info/" + id, EmployeeInfo.class);
		EmployeeSalary empsal = restTemplate.getForObject("http://employee-salary-service/empsalary/salary/" + id,
				EmployeeSalary.class);

		return new EmployeeDetails(empinfo.getId(), empinfo.getName(), empinfo.getJobStage(), empinfo.getJobRole(),
				empinfo.getSkills(), empinfo.getHobbies(), empsal.getSalary());

	}
	
	@RequestMapping("/hello")
	public String greeting() {
		return "Hello Employee";
	}
	
	@RequestMapping("/empInfo")
	public List<EmployeeInfo> getEmployeeInfo() {
		List<EmployeeInfo> employeeInfos = new ArrayList<EmployeeInfo>();
		employeeInfos.add(new EmployeeInfo(1, "Donald", "4", "SA", "Docker", "Cricket"));
		return employeeInfos;
	}
}
