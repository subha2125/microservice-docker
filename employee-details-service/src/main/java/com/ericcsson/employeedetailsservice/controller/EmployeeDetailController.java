package com.ericcsson.employeedetailsservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ericcsson.employeedetailsservice.model.EmployeeDetails;
import com.ericcsson.employeedetailsservice.model.EmployeeInfo;
import com.ericcsson.employeedetailsservice.model.EmployeeSalary;
import com.ericcsson.employeedetailsservice.model.EmployeeSalaryList;

import org.slf4j.Logger;

@RestController
@RequestMapping("/employee")
public class EmployeeDetailController {

	Logger logger = LoggerFactory.getLogger(EmployeeDetailController.class);

	@Autowired
	private RestTemplate restTemplate;

	
	@GetMapping("/getAllEmployeeDetails")
	public List<EmployeeDetails> getAllDetails() {
		List<EmployeeDetails> empDetailsList = new ArrayList<EmployeeDetails>();
		logger.info("Calling to employee-salary-service start");
		EmployeeSalaryList empsalAll = restTemplate.getForObject("http://employee-salary-service/salary/",
				EmployeeSalaryList.class);
		logger.info("Call to employee-salary-service finished");

		for( EmployeeSalary empsal : empsalAll.getEmployeesalaryList()){
			EmployeeInfo empinfo = restTemplate.getForObject("http://employee-info-service/info/"+empsal.getId(), EmployeeInfo.class);

			empDetailsList.add(new EmployeeDetails(empinfo.getId(), empinfo.getName(), empinfo.getJobStage(), empinfo.getJobRole(),
					empinfo.getSkills(), empinfo.getHobbies(), empsal.getSalary()));
		}
		
		return empDetailsList;
		 
		/*
		 * return new EmployeeDetails(empinfo.getId(), empinfo.getName(),
		 * empinfo.getJobStage(), empinfo.getJobRole(), empinfo.getSkills(),
		 * empinfo.getHobbies(), empsal.getSalary());
		 * 
		 * return ratings.stream().map(rating -> { Movie movie =
		 * restTemplate.getForObject("http://movie-info-service/movies/" +
		 * rating.getMovieId(), Movie.class); return new
		 * EmployeeDetails(movie.getName(), "Romantic", rating.getRating());
		 * }).collect(Collectors.toList());
		 */
		
	

	}
	 

	@RequestMapping("/employee/{id}")
	public EmployeeDetails employeeDetails(@PathVariable int id) {
		// return employeeDetailsAll.stream().filter(t -> t.getId() ==
		// id).findFirst().get();
		EmployeeInfo empinfo = restTemplate.getForObject("http://employee-info-service/info/" + id, EmployeeInfo.class);
		EmployeeSalary empsal = restTemplate.getForObject("http://employee-salary-service/salary/" + id,
				EmployeeSalary.class);

		return new EmployeeDetails(empinfo.getId(), empinfo.getName(), empinfo.getJobStage(), empinfo.getJobRole(),
				empinfo.getSkills(), empinfo.getHobbies(), empsal.getSalary());

	}
	
	@RequestMapping("/hello")
	public String greeting() {
		return "Hello User";
	}
}
