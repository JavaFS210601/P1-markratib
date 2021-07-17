package com.revature.models;

/*A DTO is a Data Transfer Object. A DTO is a model of some data coming from the client
 * Your servlet will parse a JSON object sent by the user, containing their username and password,
 * which gets sent to the controller to be turned into a Java Object. The username and password
 * will be put into this DTO as fields, which will get checked and validated by the service layer.
 * You NEVER store a DTO in the database, it's purely for data transfer...
 */

public class LoginDTO 
{
	private String username;
	private String password;
	
	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
}
