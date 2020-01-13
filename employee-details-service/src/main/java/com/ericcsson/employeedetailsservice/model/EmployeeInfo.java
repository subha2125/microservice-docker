package com.ericcsson.employeedetailsservice.model;

public class EmployeeInfo {
	
	private int id;
	private String name;
	private String jobStage;
	private String jobRole;
	private String skills;
	private String hobbies;
	public EmployeeInfo(int id, String name, String jobStage, String jobRole, String skills, String hobbies) {
		super();
		this.id = id;
		this.name = name;
		this.jobStage = jobStage;
		this.jobRole = jobRole;
		this.skills = skills;
		this.hobbies = hobbies;
	}
	public EmployeeInfo() {
		
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
	
	@Override
	public String toString() {
		return "EmployeeInfo [name=" + name + ", jobStage=" + jobStage + ", jobRole=" + jobRole + ", skills=" + skills
				+ ", hobbies=" + hobbies + "]";
	}
	
}
