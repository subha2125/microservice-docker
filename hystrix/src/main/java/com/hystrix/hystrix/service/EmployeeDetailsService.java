package com.hystrix.hystrix.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hystrix.hystrix.models.EmployeeDetails;
import com.hystrix.hystrix.models.EmployeeInfo;
import com.hystrix.hystrix.models.EmployeeSalary;
import com.hystrix.hystrix.models.EmployeeSalaryList;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class EmployeeDetailsService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	Logger logger = LoggerFactory.getLogger(EmployeeDetailsService.class);

	/** Circuit Breaker and BulkHead Pattern with threadPoolProperties **/
	@HystrixCommand(fallbackMethod="getAllDetailsFallBack", 
			commandProperties = { @HystrixProperty(name="execution.isolation.thread.timeoutInMilliSeconds", value="500")},
			threadPoolProperties = { @HystrixProperty(name="hystrix.threadpool.S1.coreSize", value="15"),
					@HystrixProperty(name="coreSize", value="25"),
					@HystrixProperty(name="maxQueueSize", value="25")})
	public List<EmployeeDetails> getAllDetails() {

		List<EmployeeDetails> empDetailsList = new ArrayList<EmployeeDetails>();
		logger.info("Calling to employee-salary-service start");
		EmployeeSalaryList empsalAll = restTemplate.getForObject("http://employee-salary-service/salary/",
				EmployeeSalaryList.class);
		logger.info("Call to employee-salary-service finished");

		for( EmployeeSalary empsal : empsalAll.getEmployeesalaryList()){
			EmployeeInfo empinfo = restTemplate.getForObject("http://employee-info-service/info/"+empsal.getId(), EmployeeInfo.class);
			
            if(empinfo != null) {
            	logger.info("Employee found with details as .. " + empinfo.toString());
			 empDetailsList.add(new EmployeeDetails(empinfo.getId(), empinfo.getName(), empinfo.getJobStage(), empinfo.getJobRole(),
					empinfo.getSkills(), empinfo.getHobbies(), empsal.getSalary()));
            }else {
            	logger.info("No Emp Found with ID.. " + empsal.getId());
            }
		}
		
		return empDetailsList;
	}
	
	public List<EmployeeDetails> getAllDetailsFallBack() {
		logger.info("Inside getAllDetailsFallBack");
		List<EmployeeDetails> empDetailsList = new ArrayList<EmployeeDetails>();
		EmployeeSalaryList empsalAll = restTemplate.getForObject("http://localhost:8183/salary/",
				EmployeeSalaryList.class);

		for( EmployeeSalary empsal : empsalAll.getEmployeesalaryList()){
			EmployeeInfo empinfo = restTemplate.getForObject("http://localhost:8184/info/"+empsal.getId(), EmployeeInfo.class);

			empDetailsList.add(new EmployeeDetails(empinfo.getId(), empinfo.getName(), empinfo.getJobStage(), empinfo.getJobRole(),
					empinfo.getSkills(), empinfo.getHobbies(), empsal.getSalary()));
		}
		
		return empDetailsList;
	}
}
