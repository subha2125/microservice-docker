package com.ericcsson.employeedetailsservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
	
    //Hazelcast Imap
	@Autowired
	private Map<Integer, EmployeeDetails> empDetailsMap;
	
	
	@GetMapping("/getAllEmployeeDetails")
	@ApiOperation(value="Get All Emp Details",response=EmployeeDetails.class)
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public List<EmployeeDetails> getAllDetails() {
		List<EmployeeDetails> empDetailsList = new ArrayList<EmployeeDetails>();
		logger.info("EmployeeDetail getAllEmployeeDetails called :");
		logger.info("Calling to employee-salary-service start");
		EmployeeSalaryList empsalAll = restTemplate.getForObject("http://employee-salary-service/empsalary/salary/",
				EmployeeSalaryList.class);
		logger.info("Call to employee-salary-service finished");

		for( EmployeeSalary empsal : empsalAll.getEmployeesalaryList()){
			EmployeeInfo empinfo = restTemplate.getForObject("http://employee-info-service/empinfo/info/"+empsal.getId(), EmployeeInfo.class);
			
            if(empinfo != null) {
            	logger.info("EmployeeDetails Service Employee found with details as .. " + empinfo.toString());
			 empDetailsList.add(new EmployeeDetails(empinfo.getId(), empinfo.getName(), empinfo.getJobStage(), empinfo.getJobRole(),
					empinfo.getSkills(), empinfo.getHobbies(), empsal.getSalary()));
            }else {
            	logger.info("EmployeeDetails Service No Emp Found with ID.. " + empsal.getId());
            }
		}
		
		logger.info("EmployeeDetails Sending to Kafka Broker");
		kafkaTemplate.send(topic, empDetailsList);
		
		//Put in HazelCast
		empDetailsMap = empDetailsList.stream().collect(
                Collectors.toMap(EmployeeDetails::getId, emp -> emp));
		
		logger.info("EmployeeDetails HazelCast Empdetails Imap..{}",empDetailsMap);
		
		return empDetailsList;

	}
	 
	@RequestMapping("/getCache/{id}")
	public EmployeeDetails getCache(@PathVariable int id) {
		if(Objects.nonNull(empDetailsMap)) {
			logger.info("hazelInst Emp Id :{} And Details:{}", id,empDetailsMap.get(id));
		}
		return Objects.nonNull(empDetailsMap) ? empDetailsMap.get(id) : new EmployeeDetails();
	}

	@RequestMapping("/{id}")
	public EmployeeDetails employeeDetails(@PathVariable int id) {
		logger.info("EmployeeDetail employeeDetails called id{}:",id);
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
