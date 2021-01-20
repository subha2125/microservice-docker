package com.mkyong.jdbc.model;

public class Employee {

	private int id;
	private String userName;
	private String password;
	private boolean active;
	private String roles;

	public Employee() {

	}

	public Employee(int id, String userName, String password, boolean active, String roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", userName=" + userName + ", password=" + password + ", active=" + active
				+ ", roles=" + roles + "]";
	}

}
