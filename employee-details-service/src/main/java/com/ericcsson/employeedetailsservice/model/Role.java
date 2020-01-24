package com.ericcsson.employeedetailsservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int role_id;
	
	private String role;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}
