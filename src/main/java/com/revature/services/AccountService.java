package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.AccountDTO;
import com.revature.models.User;

public class AccountService 
{
	public static UserDAO userDAO = new UserDAO();
	private static AccountDTO aDTO = new AccountDTO();
	
	public boolean CreateAccount(User newUser)
	{
		boolean isPresent = false;
		//check if the new user is present
		if(userDAO.isPresent(newUser.getUsername()))//if they're present
		{
			isPresent = true;
		}
		else//they're not present
		{
			userDAO.insertUser(newUser);
		}
		
		return isPresent;
	}
}
