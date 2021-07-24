package com.ericcsson.employeesalaryservice.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ericcsson.employeesalaryservice.model.EmployeeSalary;
import com.ericcsson.employeesalaryservice.model.EmployeeSalaryList;


@RestController
@RequestMapping("/empsalary")
public class EmployeeSalaryControl {
	
	Logger logger = LoggerFactory.getLogger(EmployeeSalaryControl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	List<EmployeeSalary> employeeInfoAll = new ArrayList<>(Arrays.asList(
			new EmployeeSalary(1, "100000"),
			new EmployeeSalary(2, "300000"),
			new EmployeeSalary(3, "700000"),
			new EmployeeSalary(4, "206500"),
			new EmployeeSalary(6, "975750")
			)); 
	
	@RequestMapping("/salary")
	public EmployeeSalaryList getAllDetails(){
		logger.info("EmployeeSalary Service called ");
        EmployeeSalaryList salaryList = new EmployeeSalaryList();
        salaryList.setEmployeesalaryList(employeeInfoAll);
		return salaryList;
	}

	@RequestMapping("/salary/{id}")
	public EmployeeSalary employeeDetails(@PathVariable int id) {
		logger.info("EmployeeSalary called with id {} :", id);
		return employeeInfoAll.stream().filter(t -> t.getId() == id).findFirst().get();
	}
	
	@RequestMapping("/greetings")
	public String employeeDetails(){        
		return restTemplate.getForObject("http://employee-details-service:8181/hello",String.class);
	}

}
