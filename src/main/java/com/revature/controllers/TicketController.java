package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.StatusDAO;
import com.revature.daos.TicketDAO;
import com.revature.daos.TypeDAO;
import com.revature.daos.UserDAO;
import com.revature.models.ERS_Status;
import com.revature.models.ERS_Type;
import com.revature.models.NewTicketDTO;
import com.revature.models.Ticket;
import com.revature.models.TicketDTO;
import com.revature.models.User;
import com.revature.services.TicketService;

public class TicketController 
{
	private static UserDAO userDao = new UserDAO();
	private static StatusDAO statusDao = new StatusDAO();
	private static TypeDAO typeDao = new TypeDAO();
	private static TicketDAO tDao = new TicketDAO();
	private static TicketService ts = new TicketService();
	private static ObjectMapper om = new ObjectMapper();
	private static final Logger log = LogManager.getLogger(TicketController.class);
	
	public void getAllTickets(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//response.getWriter().print("Hello :)");
		//if(request.getMethod().equals("GET"))
		{
			List<Ticket> ticketList = ts.getAllTickets();
			
			String data = null;
	
			data = om.writeValueAsString(ticketList);
	
			
			response.getWriter().print(data);
			response.setStatus(200);
		}
	}

	public void getCompleteTickets(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		List<Ticket> ticketList = ts.getCompletedTickets();
		
		String data = null;

		data = om.writeValueAsString(ticketList);
		
		response.getWriter().print(data);
		response.setStatus(200);
		
	}
	
	public void getPendingTickets(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		List<Ticket> ticketList = ts.getPendingTickets();
		
		String data = null;

		data = om.writeValueAsString(ticketList);
		
		response.getWriter().print(data);
		response.setStatus(200);
		
	}

	public void approveTicket(HttpServletRequest request, HttpServletResponse response) throws IOException 
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
				TicketDTO tDTO = om.readValue(body, TicketDTO.class);
				
				//if the approval was successful...
				if(ts.approveTicket(Integer.parseInt(tDTO.getTicketId())))
				{
					response.setStatus(200);
				}
				else//approval was unsuccessful
				{
					response.setStatus(2023);
				}
				
		
	}

	public void createTicket(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
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
		NewTicketDTO tDTO = om.readValue(body, NewTicketDTO.class);
		log.info(tDTO.toString());
		User user = userDao.getUserByName(tDTO.getUsername());
		ERS_Status status = statusDao.getStatus(1);
		ERS_Type type = typeDao.getType(Integer.parseInt(tDTO.getType()));
		Date date = new Date(Long.parseLong(tDTO.getDate()));
		log.info(date);
		Ticket newTicket = new Ticket(Integer.parseInt(tDTO.getAmount()), date, null, tDTO.getDesc(), user, null, status, type);
		
		tDao.createTicket(newTicket);
		
	}
}



