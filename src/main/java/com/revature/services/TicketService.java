package com.revature.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.revature.daos.StatusDAO;
import com.revature.daos.TicketDAO;
import com.revature.models.ERS_Status;
import com.revature.models.Ticket;

public class TicketService 
{
	
	private static StatusDAO sDao = new StatusDAO();
	private static TicketDAO tDao = new TicketDAO();
	
	public List<Ticket> getAllTickets()
	{
		return tDao.getAllTickets();
	}
	
	public List<Ticket> getCompletedTickets()
	{
		return tDao.getCompleteTickets(2);
	}
	
	public List<Ticket> getPendingTickets()
	{
		return tDao.getPendingTickets(1);
	}
	
	//returns true if the opperation was completed successfully
	public boolean approveTicket(int id)
	{
		Ticket targetTicket = tDao.getTicketById(id);
		boolean success = false;
		//check if the ticket is approved or denied
		if((targetTicket.getStatus().getId() == 2) || (targetTicket.getStatus().getId() == 3))
		{
			//ticket was already judged, do nothing to it
		}
		else//ticket is awaiting decision
		{
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy ");
			//get a status object of approval
			ERS_Status status = sDao.getStatus(2);
			targetTicket.setStatus(status);
			targetTicket.setDateResolved(date);
			//set the ticket's status to approved
			
			targetTicket.setStatus(status);
			//push the ticket to the DB
			tDao.updateTicket(targetTicket);
			success = true;
		}

		
		return success;
	}

	public boolean denyTicket(int id) 
	{
		Ticket targetTicket = tDao.getTicketById(id);
		boolean success = false;
		//check if the ticket is approved or denied
		if((targetTicket.getStatus().getId() == 2) || (targetTicket.getStatus().getId() == 3))
		{
			//ticket was already judged, do nothing to it
		}
		else//ticket is awaiting decision
		{
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy ");
			//get a status object of approval
			ERS_Status status = sDao.getStatus(3);
			//set the ticket's status to approved
			targetTicket.setStatus(status);
			targetTicket.setDateResolved(date);
			//push the ticket to the DB
			tDao.updateTicket(targetTicket);
			success = true;
		}

		
		return success;
	}

	public boolean redoTicket(int id) 
	{
		Ticket targetTicket = tDao.getTicketById(id);
		boolean success = false;
		//check if the ticket is approved or denied
		if((targetTicket.getStatus().getId() == 1))
		{
			//ticket was already judged, do nothing to it
		}
		else//ticket is awaiting decision
		{
			//get a status object of approval
			ERS_Status status = sDao.getStatus(1);
			//set the ticket's status to approved
			targetTicket.setStatus(status);
			targetTicket.setDateResolved(null);
			//push the ticket to the DB
			tDao.updateTicket(targetTicket);
			success = true;
		}

		
		return success;
	}
}
