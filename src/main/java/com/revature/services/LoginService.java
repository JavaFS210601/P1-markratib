package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;

public class LoginService 
{
	public static UserDAO uDAO = new UserDAO();
	
	public boolean login(String username, String password)
	{
		User user = uDAO.getUserByName(username);
		boolean authenticated = false;
		//compare the password in the DB to the password provided
		if(user.getPassword().compareTo(password) == 0)
		{
			//change authenticated to true, usernam and password were correct
			authenticated = true;
		}
		return authenticated;
	}
}
