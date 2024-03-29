package com.ericcsson.employeedetailsservice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@NoArgsConstructor
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	
	@NotNull(message="User name cannot be null")
	@Size(min=2, message = "User name must not be less than 2 characters")
	private String username;
	
	/*@NotNull(message="Password cannot be null")
	@Size(min=6,max=16, message="Password must be equal or grater than 6 characters and less than 16 chaeracters")*/
	private String password;
	
	@NotNull(message="Email cannot be null")
	@Email
	private String email;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> roles;

	public int getUser_id() {
		return user_id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	@JsonManagedReference
	@JsonIgnore
	public Set<Role> getRoles() {
		return roles;
	}
	
}
