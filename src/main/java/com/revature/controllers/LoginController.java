package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private static final Logger log = LogManager.getLogger(LoginController.class);
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
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
//			log.info("lDTO = " + lDTO.toString());
//			log.info("lDTO.getPassword() = " + lDTO.getPassword()
//					+ "\nlDTO.getPassword().getClass() = " + lDTO.getPassword().getClass());
			if(ls.login(lDTO.getUsername(), lDTO.getPassword()))
			{
				String commonDomain = "http://127.0.0.1:5500";
				String financeView = "http://127.0.0.1:5500/ERS_Reimb%20Pages/financeView.html";
				String employeeView = "http://127.0.0.1:5500/ERS_Reimb%20Pages/employeeView.html";
				//return a session to hold user info
				HttpSession ses = request.getSession();
				User user = userDao.getUserByName(lDTO.getUsername());

//				cookie.setDomain(financeView);
//				cookie.setPath("/financeView.html");
//				cookie.setPath(financeView);
//				cookie.setPath(commonDomain);
				ses.setAttribute("user", lDTO);
				ses.setAttribute("logged in", true);
				response.setStatus(200);
				RequestDispatcher rd = null;
//				response.getWriter().print("Login Successful!");
				
				//forward the user based on their user role
				if( user.getUserRole().getId() == 2)// if they're a finance manager...
				{
					Cookie cookie = new Cookie("username", lDTO.getUsername());
					cookie.setMaxAge(1000);
					response.addCookie(cookie);
					response.setContentType("text/html");
					PrintWriter pw=response.getWriter(); 
//						rd = request.getRequestDispatcher("financeView.html");//if the username/password are good, initialize our RequestDispatcher
//						//this "success" parameter is going to be appended to the end of our base URL - to go to SuccessServlet
//						//The SuccessServlet will be the servlet to forward to upon successful login
//						rd.forward(request, response);//we forward the request and response objects to another servlet
					String URI = request.getRequestURI().replace("/P1-markratib/login	", "");
					log.info("Performing redirect..."
							+ "\nURL = " + URI + "/finnceView.html");
//					rd = request.getRequestDispatcher("fmanager");
//					rd.forward(request, response);
					response.sendRedirect(financeView);//THIS WORKS
					pw.close(); 
				}
				else if(user.getUserRole().getId() == 1)
				{
					PrintWriter pw=response.getWriter();
//					rd = request.getRequestDispatcher("financeView.html");//if the username/password are good, initialize our RequestDispatcher
//					//this "success" parameter is going to be appended to the end of our base URL - to go to SuccessServlet
//					//The SuccessServlet will be the servlet to forward to upon successful login
//					rd.forward(request, response);//we forward the request and response objects to another servlet
					String URI = request.getRequestURI().replace("/P1-markratib/login", "");
					log.info("Performing redirect..."
							+ "\nURL = " + URI + "/finnceView.html");
					response.sendRedirect(employeeView);
					pw.close(); 
				}
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
//				response.getWriter().print("Invalid login credentials");
			}
		}
	}

	public void financeForward(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
//		log.info("I got to the financeForward method!");
//		log.info(response.encodeURL("C:\\Users\\reape\\Desktop\\Programming\\Revature\\GitHubRepos\\P1-markratib\\frontend\\ERS_Reimb Pages\\"));
//		response.setContentType("text/html");
//		RequestDispatcher rd = null;
//		
//		PrintWriter pw = response.getWriter();
//		rd = request.getRequestDispatcher("C:\\Users\\reape\\Desktop\\Programming\\Revature\\GitHubRepos\\P1-markratib\\frontend\\ERS_Reimb Pages\\financeView.html");
	}
}