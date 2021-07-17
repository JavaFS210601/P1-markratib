package com.revature.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class UserDAO implements UserDAOInterface
{
	@Override
	public void insertUser(User newUser)
	{
		Session ses = HibernateUtil.getSession();
		
		ses.save(newUser);
		
		HibernateUtil.closeSession();
	}
	
	@Override
	public User getUserById(int user_id) 
	{
		Session ses = HibernateUtil.getSession();
		
		User user = ses.get(User.class, user_id);
		
		HibernateUtil.closeSession();
		
		return user;
	}
	
	@Override
	public boolean isPresent(String username)
	{
		Session ses = HibernateUtil.getSession();
		boolean present = false;
		
		List<User> userList = getAllUsers();
		
		for(User u: userList)
		{
			//if the username supplied is the same as the current user...
			if(u.getUsername().compareTo(username) == 0)
			{
				present = true;
			}
		}
		return present;
	}

	@Override
	public List<User> getAllUsers()
	{
		Session ses = HibernateUtil.getSession();
		
		List<User> userList = ses.createQuery("from User").list();
		
		HibernateUtil.closeSession();
		
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	@Override 
	public User getUserByName(String username)
	{
		Session ses = HibernateUtil.getSession();
		
		List<User> userList = ses.createQuery("from User").list();
		User targetUser = new User();
		for(User u: userList)
		{
			if(u.getUsername().compareTo(username) == 0)
			{
				targetUser = u;
			}
		}
		
		return targetUser;
	}
	
	@Override
	public List<User> getUsersByRole(int user_role_id) 
	{
		Session ses = HibernateUtil.getSession();
		
		List<User> userList = ses.createQuery("from Users where user_role_id = " + user_role_id).list();
		
		HibernateUtil.closeSession();
		return userList;
	}

	@Override
	public void updateUser(User user) 
	{
		Session ses = HibernateUtil.getSession();
		
		ses.merge(user);
		
		HibernateUtil.closeSession();
	}

	@Override
	public void deleteUser(User user) 
	{
		Session ses = HibernateUtil.getSession();
		
		ses.delete(user);
		
		HibernateUtil.closeSession();		
	}

}
