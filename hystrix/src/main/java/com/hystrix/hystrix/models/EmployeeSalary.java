package com.hystrix.hystrix.models;


public class EmployeeSalary {

	private int id;
	private String salary;

	public EmployeeSalary(int id, String salary) {
		super();
		this.id = id;
		this.salary = salary;
	}
	
	public EmployeeSalary() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

}
