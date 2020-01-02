package com.ericcsson.employeeinfoservice.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ericcsson.employeeinfoservice.model.EmployeeInfo;
import com.ericcsson.employeeinfoservice.model.EmployeeInfoList;


@RestController
public class EmlpoyeeInfoControl {
	
	@Autowired
	private RestTemplate restTemplate;
	
	List<EmployeeInfo> employeeInfoAll = new ArrayList<>(Arrays.asList(
			new EmployeeInfo(123, "Subhayu", "6", "SA", "Java,SQL","Sports,Music"),
			new EmployeeInfo(124, "Saby", "6", "SA", "Java,SQL","Eating"),
			new EmployeeInfo(125, "Giraffe", "4", "SD", "Java,SQL","Sports,Music")
			)); 
	
	@RequestMapping("/info")
	public EmployeeInfoList getAllDetails(){

		EmployeeInfoList empInfoList = new EmployeeInfoList();
		empInfoList.setEmpInfoList(employeeInfoAll);
		return empInfoList;
	}

	@RequestMapping("/info/{id}")
	public EmployeeInfo employeeDetails(@PathVariable int id) {
		return employeeInfoAll.stream().filter(t -> t.getId() == id).findFirst().get();
	}
	
	@RequestMapping("/greetings")
	public String employeeDetails(){        
		return restTemplate.getForObject("http://employee-details-service:8181/hello",String.class);
	}
}
