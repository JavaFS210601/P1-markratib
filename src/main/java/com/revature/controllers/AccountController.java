package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UserDAO;
import com.revature.daos.UserRoleDAO;
import com.revature.models.AccountDTO;
import com.revature.models.ERS_User_Role;
import com.revature.models.User;
import com.revature.services.AccountService;

public class AccountController 
{
	private AccountService as = new AccountService();
	private ObjectMapper om = new ObjectMapper();
	private UserDAO userDao = new UserDAO();
	private UserRoleDAO roleDAO = new UserRoleDAO();
	
	public void createAccount(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		//make a buffered reader to read text from the input string
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		//read the contents of the bufferedReader into a String
		String line = reader.readLine();
		
		while(line != null)
		{
			//add the contents of line to the stringbulder
			sb.append(line);
			//assign line to the next line of data in the reader
			line = reader.readLine();
		}
		//ObjectMapper only works with Strings
		String body = new String(sb);
		//use the ObjectMapper to read the JSON into the DTO
		AccountDTO aDTO = om.readValue(body, AccountDTO.class);
		//i need a userRole object to pass into the new user object....
		ERS_User_Role userRole = new ERS_User_Role();
		
		switch(aDTO.getRole().toLowerCase())
		{
			case "employee":
			{
				userRole = roleDAO.getRole(1);
				break;
			}
			case "finance manager":
			{
				userRole = roleDAO.getRole(2);
				break;
			}
			default:
			{
				userRole = roleDAO.getRole(1);
			}
		}
		
		//check if the user is already in the database
		if(!userDao.isPresent(aDTO.getUsername()))
		{
			//if they are not, add them ot the batabase
			User newUser = new User(aDTO.getUsername(), aDTO.getPassword(), aDTO.getFirstName(), aDTO.getLastName(), aDTO.getEmail(), userRole);
			userDao.insertUser(newUser);
			response.setStatus(201);
			response.getWriter().print("Account creation successful!");
		}
		else//getUserByName did not return null, user exists in the DB
		{
			response.setStatus(200);
			response.getWriter().print("Duplicate username submitted!");
		}
	}
	
	
	
}
