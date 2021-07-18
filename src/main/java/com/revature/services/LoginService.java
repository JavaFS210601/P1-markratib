package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.controllers.LoginController;
import com.revature.daos.UserDAO;
import com.revature.models.User;

public class LoginService 
{
	public static UserDAO uDAO = new UserDAO();
	private static final Logger log = LogManager.getLogger(LoginService.class);
	//returns true if the user is logged in successfully
	public boolean login(String username, String password)
	{
		User user = uDAO.getUserByName(username);
		boolean authenticated = false;
		//compare the password in the DB to the password provided
		log.info("password in LoginService = " + password);
		log.info("user.getPassword() in LoginService = " + user.getPassword());
		if(user.getPassword().compareTo(password) == 0)
		{
			//change authenticated to true, username and password were correct
			authenticated = true;
		}
		return authenticated;
	}
}
