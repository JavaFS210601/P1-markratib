package com.revature.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDTO 
{
	@JsonProperty
	private String username;
	@JsonProperty
	private String password;
	@JsonProperty
	private String firstname;
	@JsonProperty
	private String lastname;
	@JsonProperty
	private String email;
	@JsonProperty
	private String role;
	
	public AccountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDTO(String username, String password, String firstName, String lastName, String email, String role) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstName;
		this.lastname = lastName;
		this.email = email;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}
	
	
}
