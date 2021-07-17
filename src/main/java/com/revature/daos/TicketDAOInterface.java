package com.revature.daos;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDAOInterface 
{
	//create a new ticket in the DB
	public void createTicket(Ticket ticket);
	//get all tickets in the database
	public List<Ticket> getAllTickets();
	//get a single ticket by it's id
	public Ticket getTicketById(int id);
	
	//get all tickets submited by a single user
	public List<Ticket> getTicketsByAuthor(int user_id);
	
	//get all tickets of a specific status
	//THIS WON'T WORK WITH CURRENT IMPLEMENTATION
//	public List<Ticket> getTicketsByStatus(int status_id);
	
	//get all tickets of a specific status submitted by a single user
	public List<Ticket> getTicketsByStatus(int status_id, int user_id);
	
	//get all tickets greater than or less than a specified amount
	public List<Ticket> getTicketsByAmount(int amount, boolean greater);
	
	//get all tickets after a specified date
//	public List<Ticket> getTicketsByDate();//Not sure what to put for the parameter
	//get all tickets by their resolver
	
	public List<Ticket> getTicketsByResolver(int user_id);
	//update a ticket in the DB
	public void updateTicket(Ticket ticket);
	//delete a ticket in the DB
	public void deleteTicket(Ticket ticket);
	/*********************END getTicketsByAuthor*********************/
	List<Ticket> getCompleteTickets(int status_id);
	/*********************END getCompleteTickets*********************/
	List<Ticket> getPendingTickets(int status_id);
}
