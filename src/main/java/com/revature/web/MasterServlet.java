package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.AccountController;
import com.revature.controllers.LoginController;
import com.revature.controllers.TicketController;

public class MasterServlet extends HttpServlet
{
	private LoginController lc = new LoginController();
	private AccountController ac = new AccountController();
	private TicketController tc = new TicketController();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//set the content of the response object to be JSON
		response.setContentType("application/json");
		//set the default status code to 404
		response.setStatus(404);
		//strip out the part of the URL we don't want
		String URI = request.getRequestURI().replace("/P1-markratib/", "");
		//determine where the request will get sent
		switching(URI, request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//set the content of the response object to be JSON
		response.setContentType("application/json");
		//set the default status code to 404
		response.setStatus(404);
		//strip out the part of the URL we don't want
		String URI = request.getRequestURI().replace("/P1-markratib/", "");
		//determine where the request will get sent
		switching(URI, request, response);
	}
	
	public void switching(String URI, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		switch(URI)
		{
			case "login":
			{
				lc.login(request, response);
				break;
			}
			
			case "accountCreation":
			{
				ac.createAccount(request, response);
				break;
			}
			case "getAllTickets":
			{
				tc.getAllTickets(request, response);
				break;
			}
			case "getCompleteTickets":
			{
				tc.getCompleteTickets(request, response);
				break;
			}
			case "getPendingTickets":
			{
				tc.getPendingTickets(request,response);
				break;
			}
			case "approveTicket":
			{
				tc.approveTicket(request, response);
				break;
			}
			case "denyTicket":
			{
				tc.denyTicket(request, response);
				break;
			}
			case "redoTicket":
			{
				tc.redoTicket(request, response);
				break;
			}
			case "fmanager":
			{
				lc.financeForward(request, response);
				break;
			}
			case "createTicket":
			{
				tc.createTicket(request, response);
				break;
			}
		}
	}
}
