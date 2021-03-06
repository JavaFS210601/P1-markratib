package com.revature;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.TicketController;
import com.revature.daos.StatusDAO;
import com.revature.daos.TicketDAO;
import com.revature.daos.TypeDAO;
import com.revature.daos.UserDAO;
import com.revature.daos.UserRoleDAO;
import com.revature.models.AccountDTO;
import com.revature.models.ERS_Status;
import com.revature.models.ERS_Type;
import com.revature.models.ERS_User_Role;
import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.services.TicketService;

public class Main 
{
	
	private static StatusDAO statusDao = new StatusDAO();
	private static TypeDAO typeDao = new TypeDAO();
	private static UserRoleDAO roleDao = new UserRoleDAO();
	private static UserDAO userDao = new UserDAO();
	
	private static TicketDAO ticketDao = new TicketDAO();
	private static TicketService ticketS = new TicketService();
	private static TicketController ticketC = new TicketController();
	
	//these are for testing out the AccountDTO...
	private static String newUserEx = "{\"username\":\"mark\", \"password\":\"123\",\"firstname\":\"123\",\"lastname\":\"123\",\"email\":\"123\",\"role\":\"finance manager\"}";
	private static AccountDTO aDTO = new AccountDTO();
	private static ObjectMapper om = new ObjectMapper();
	public static void main(String[] args) 
	{
//		createDB();
//		getDB();
//		ERS_User_Role role = roleDao.getRole(2);
//		System.out.println(role.toString());
//		User user1 = new User("merk", "123", "mark", "ratib", "fake@email.com", role);
//		userDao.insertUser(user1);
//		System.out.println(userDao.getUserByName("123"));;
		
//		try {
//			aDTO = om.readValue(newUserEx, AccountDTO.class);
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		System.out.println(userDao.isPresent("John"));

		
		List<Ticket> ticketList = ticketS.getCompletedTickets();
		
//		System.out.println("AFTER COMPLETEDTICKETS IS CALLED" + ticketList.toString());
//		ticketList.clear();
//		ticketList = ticketS.getPendingTickets();
//		System.out.println("AFTER PENDINGTICKETS IS CALLED " + ticketList.toString());
//		ticketList.clear();
		
//		ticketS.approveTicket(1);
		
		User user1 = userDao.getUserById(1);
		User user2 = userDao.getUserById(2);
		ERS_Status status1 = statusDao.getStatus(1);
		ERS_Type type4 = typeDao.getType(4);
		
		Ticket ticket1 = new Ticket(1, 200, null, null, "I bought stuff", user2, user1, status1, type4);
		
		ticketDao.updateTicket(ticket1);
		
		System.out.println("Exiting...");
		
//		Ticket ticket2 = new Ticket(1, null, null, "I bought stuff", user2, null, status1, type4);
//		
//		ticketDao.createTicket(ticket2);
		
		
	}

	private static void createDB()
	{
		ERS_Status status1 = new ERS_Status("closed");
		ERS_Status status2 = new ERS_Status("open");
		
		statusDao.insertStatus(status1);
		statusDao.insertStatus(status2);
		
		ERS_User_Role role1 = new ERS_User_Role("babby");
		ERS_User_Role role2 = new ERS_User_Role("FManager");
		
		roleDao.insertRole(role1);
		roleDao.insertRole(role2);
		
		ERS_Type type1 = new ERS_Type("lodging");
		ERS_Type type2 = new ERS_Type("travel");
		ERS_Type type3 = new ERS_Type("food");
		ERS_Type type4 = new ERS_Type("other");
		
		typeDao.insertType(type1);
		typeDao.insertType(type2);
		typeDao.insertType(type3);
		typeDao.insertType(type4);
		//THE ABOVE STATEMENTS WILL BUILD THE DATABASE WITH ERS STATUS, TYPE, and ROLE WITH CORRECT ENTRIES.
		//these next statements are to test users and tickets. BOTH ARE WORKING AS OF July 12th
		User user1 = new User("Merk", "123", "mark", "ratib", "nothing", role2);
		User user2 = new User("Timmman", "123", "tim", "timmy", "nothing", role1);
		
		userDao.insertUser(user1);
		userDao.insertUser(user2);
		
		Ticket ticket1 = new Ticket(100, null, null, "I bought stuff", user2, null, status1, type4);
		
		ticketDao.createTicket(ticket1);
	}
	
	private static void getDB()
	{
		List<ERS_Status> statusList = statusDao.getAllStatus();
		List<ERS_Type> typeList = typeDao.getAllType();
		List<ERS_User_Role> roleList = roleDao.getAllRole();
		List<Ticket> ticketList = ticketDao.getAllTickets();
		List<User> userList = userDao.getAllUsers();
		
		System.out.println("Status*************************************************************************");
		for(ERS_Status item : statusList)
		{
			System.out.println(item.toString());
		}
		System.out.println("Type*************************************************************************");
		for(ERS_Type item : typeList)
		{
			System.out.println(item.toString());
		}
		System.out.println("Role*************************************************************************");
		for(ERS_User_Role item : roleList)
		{
			System.out.println(item.toString());
		}
		System.out.println("Ticket*************************************************************************");
		for(Ticket item : ticketList)
		{
			System.out.println(item.toString());
		}
		System.out.println("User*************************************************************************");
		for(User item : userList)
		{
			System.out.println(item.toString());
		}
	}
}
 