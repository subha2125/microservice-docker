package com.ericcsson.employeeinfoservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EmployeeInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String jobStage;
	private String jobRole;
	private String skills;
	private String hobbies;

	public EmployeeInfo(long id, String name, String jobStage, String jobRole, String skills, String hobbies) {
		super();
		this.id = id;
		this.name = name;
		this.jobStage = jobStage;
		this.jobRole = jobRole;
		this.skills = skills;
		this.hobbies = hobbies;
	}

}
