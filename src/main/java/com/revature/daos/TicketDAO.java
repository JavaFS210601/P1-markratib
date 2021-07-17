package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

/*CURRENT METHODS:
 * public List<Ticket> getAllTickets()
 * public Ticket getTicketById(int id)
 * public List<Ticket> getTicketsByAuthor(int user_id)
 * public List<Ticket> getTicketsByStatus(int status_id)
 * public List<Ticket> getTicketsByStatus(int status_id, int user_id)
 * public List<Ticket> getTicketsByAmount(int amount, boolean greater)
 * public List<Ticket> getTicketsByResolver(int user_id)
 * 
*/
import com.revature.models.Ticket;
import com.revature.utils.HibernateUtil;

public class TicketDAO implements TicketDAOInterface
{
	private List<Ticket> ticketList = new ArrayList<>();
	private Ticket ticket;
	
	//Create a new Ticket in the DB
	public void createTicket(Ticket ticket)
	{
		Session ses = HibernateUtil.getSession();
		
		ses.save(ticket);
		
		HibernateUtil.closeSession();
	}	
	//get all tickets in the database
	@Override
	public List<Ticket> getAllTickets() 
	{
		Session ses = HibernateUtil.getSession();
		
		List<Ticket> ticketList = ses.createQuery("from Ticket").list();
		
		HibernateUtil.closeSession();
		
		return ticketList;
	}/*********************END getAllTickets*********************/
	//get a single ticket by it's id
	@Override
	public Ticket getTicketById(int id) 
	{
		Session ses = HibernateUtil.getSession();
		
		Ticket ticket = ses.get(Ticket.class, id);
		
		HibernateUtil.closeSession();
		
		return ticket;
	}/*********************END getTicketById*********************/
	//get all tickets submited by a single user
	@Override
	public List<Ticket> getTicketsByAuthor(int user_id) 
	{
		Session ses = HibernateUtil.getSession();
		
		List<Ticket> ticketList = ses.createQuery("from ticket where " + user_id).list();
		
		HibernateUtil.closeSession();
		
		return ticketList;
	}/*********************END getTicketsByAuthor*********************/
	//get all tickets that are not pending
	@Override
	public List<Ticket> getCompleteTickets(int status_id) 
	{
		Session ses = HibernateUtil.getSession();
		
		List<Ticket> ticketList = getAllTickets();
		
		List<Ticket> returnList = new ArrayList<>();
		
		for(Ticket t : ticketList)
		{
			//if the IDs are the same, ad the ticket to the return list
			if(t.getStatus().getId() > 1)
			{
				returnList.add(t);
			}
		}
		
		HibernateUtil.closeSession();
		
		return returnList;
	}/*********************END getCompleteTickets*********************/
	
	@Override
	public List<Ticket> getPendingTickets(int status_id) 
	{
		Session ses = HibernateUtil.getSession();
		
		List<Ticket> ticketList = getAllTickets();
		
		List<Ticket> returnList = new ArrayList<>();
		
		for(Ticket t : ticketList)
		{
			//if the IDs are the same, ad the ticket to the return list
			if(t.getStatus().getId() == 1)
			{
				returnList.add(t);
			}
		}
		
		HibernateUtil.closeSession();
		
		return returnList;
	}/*********************END getPendingTickets*********************/
	//get all tickets of a specific status submitted by a single user
	@Override
	public List<Ticket> getTicketsByStatus(int status_id, int user_id) 
	{
//		Session ses = HibernateUtil.getSession();
//		
//		List<Ticket> ticketList = ses.createQuery("from ticket where " + user_id).list();
//		
//		HibernateUtil.closeSession();
//		
//		return ticketList;
		return null;
	}/*********************END getTicketsByStatus*********************/
	//get all tickets greater than or less than a specified amount
	@Override
	public List<Ticket> getTicketsByAmount(int amount, boolean greater) 
	{
		// TODO Auto-generated method stub
		return null;
	}/*********************END getTicketsByAmount*********************/
	//get all tickets by their resolver
	@Override
	public List<Ticket> getTicketsByResolver(int user_id) 
	{
		Session ses = HibernateUtil.getSession();
		
		List<Ticket> ticketList = ses.createQuery("from ticket where " + user_id).list();
		
		HibernateUtil.closeSession();
		
		return ticketList;
	}/*********************END getTicketsByResolver*********************/
	//update a ticket in the DB
	public void updateTicket(Ticket ticket)
	{
		Session ses = HibernateUtil.getSession();
		String sql = "UPDATE Ticket SET reimb_status_id_fk = "+ ticket.getStatus().getId() +" WHERE reimb_id ="+ ticket.getId();
		ses.createQuery(sql);
		HibernateUtil.closeSession();
	}
	
	//Delete a ticket in the DB
	public void deleteTicket(Ticket ticket)
	{
		Session ses = HibernateUtil.getSession();
		
		ses.delete(ticket);
		
		HibernateUtil.closeSession();
	}

}
