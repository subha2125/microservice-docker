package com.ericcsson.employeedetailsservice.model;

import io.swagger.annotations.ApiModel;

@ApiModel(value="Details about Employee")
public class EmployeeDetails {

	private int id;
	private String name;
	private String jobStage;
	private String jobRole;
	private String skills;
	private String hobbies;
	private String salary;
	public EmployeeDetails(int id, String name, String jobStage, String jobRole, String skills, String hobbies,
			String salary) {
		super();
		this.id = id;
		this.name = name;
		this.jobStage = jobStage;
		this.jobRole = jobRole;
		this.skills = skills;
		this.hobbies = hobbies;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobStage() {
		return jobStage;
	}
	public void setJobStage(String jobStage) {
		this.jobStage = jobStage;
	}
	public String getJobRole() {
		return jobRole;
	}
	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	
	
	
	
}
