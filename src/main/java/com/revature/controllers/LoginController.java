package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UserDAO;
import com.revature.models.LoginDTO;
import com.revature.models.User;
import com.revature.services.LoginService;

public class LoginController 
{
	private LoginService ls = new LoginService();
	private ObjectMapper om = new ObjectMapper();
	private UserDAO userDao = new UserDAO();
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//check if it is a post request
		if(request.getMethod().equals("POST"))
		{
			//make a buffered reader to read text from the input string
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			//read the contents of the BufferedReader into a String
			String line = reader.readLine();
			//while there is data to read from the response data (response >> reader >> line)
			while(line != null)
			{
				//add the contetnts of line to the StringBuilder
				sb.append(line);
				//assign line to the next line of data in the reader
				line = reader.readLine();
			}
			//ObjectMapper only works with Strings, not StringBuilders
			//make a new String to hold the StringBuilder content
			String body = new String(sb);
			//Use the ObjectMapper to read our JSON username and password
			//put it into a loginDTO
			LoginDTO lDTO = om.readValue(body, LoginDTO.class);
			//if the login is successful
			if(ls.login(lDTO.getUsername(), lDTO.getPassword()))
			{
				//return a session to hold user info
				HttpSession ses = request.getSession();
				User user = userDao.getUserByName(lDTO.getUsername());
				ses.setAttribute("user", user);
				ses.setAttribute("logged in", true);
				response.setStatus(200);
				response.getWriter().print("Login Successful!");
			}
			else//login was unsuccessful
			{
				HttpSession ses = request.getSession(false);
				//if a session exists...
				if(ses != null)
				{
					ses.invalidate();
				}
				//401 means unauthorized
				response.setStatus(401);
				response.getWriter().print("Invalid login credentials");
			}
		}
	}
}