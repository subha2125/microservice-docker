package com.ericcsson.employeedetailsservice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	private String username;
	private String password;
	private String email;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> roles;

}
