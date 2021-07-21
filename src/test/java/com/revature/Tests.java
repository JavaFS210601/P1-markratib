package com.revature;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.revature.daos.UserDAO;
import com.revature.models.ERS_User_Role;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.LoginService;
import com.revature.services.TicketService;

public class Tests 
{
	AccountService accountService = new AccountService();
	LoginService loginService = new LoginService();
	TicketService ticketService = new TicketService();
	
	UserDAO userDao = new UserDAO();
	//needed in order for this to be recognized as a testing method
	//1
//	@Test
	public void testValidLogin()
	{
		System.out.println("Testing valid login...");
		
		assertTrue(loginService.login("merk", "123"));
	}
	//2
//	@Test
	public void testInvalidLogin()
	{
		System.out.println("Testing invalid login...");
		
		assertFalse(loginService.login("1111111111", "123"));
	}
	//3
//	@Test
	public void testAccountSuccess()
	{
		System.out.println("Testing account success...");
		ERS_User_Role role = new ERS_User_Role("FManager");
		User user = new User("babbu", "123", "TEST", "TEST", "TEST", role);
		
		assertFalse(accountService.CreateAccount(user));
	}
	//4
//	@Test
	public void testAccountFailure()
	{
		System.out.println("Testing account failure...");
		User user = userDao.getUserById(1);
		assertTrue(accountService.CreateAccount(user));
	}
	//5
//	@Test
	public void testGetAllTickets()
	{
		System.out.println("Testing get all tickets");
		assertNotNull(ticketService.getAllTickets());
	}
	//6
//	@Test
	public void testCompletedTickets()
	{
		System.out.println("Testing get completed tickets");
		assertNotNull(ticketService.getCompletedTickets());
	}
	//7
//	@Test
	public void testPendingTickets()
	{
		System.out.println("Testing get pending tickets");
		assertNotNull(ticketService.getPendingTickets());
	}
	//8
//	@Test
	public void testIsPresent()
	{
		System.out.println("Testing isPresent");
		assertTrue(userDao.isPresent("I AM TEST"));
	}
	//9
//	@Test
	public void testIsNotPresent()
	{
		System.out.println("Testing isPresent");
		assertFalse(userDao.isPresent("helloiamnotpresent"));
	}
	//10
//	@Test
	public void testGetAllUsers()
	{
		System.out.println("Testing get all users");
		assertNotNull(userDao.getAllUsers());
	}
	
}
