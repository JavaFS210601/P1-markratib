package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.ERS_User_Role;
import com.revature.models.User;
import com.revature.daos.UserRoleDAOInterface;
import com.revature.utils.HibernateUtil;

public class UserRoleDAO implements UserRoleDAOInterface 
{

	@Override
	public ERS_User_Role getRole(int id) 
	{
		//open a new sesssion
		Session ses = HibernateUtil.getSession();
		//get the role code based on the id
		ERS_User_Role role = ses.get(ERS_User_Role.class, id);
		//close the session
//		ses.close();
		HibernateUtil.closeSession();
		//return the role object
		return role;
	}

	@Override
	public List<ERS_User_Role> getAllRole() 
	{
		//open a new session
		Session ses = HibernateUtil.getSession();
		//geta list of rolees
		List<ERS_User_Role> roleList = ses.createQuery("from ERS_User_Role").list();
		//close the session
		HibernateUtil.closeSession();
		//rethrn the list of rolees
		return roleList;
	}

	@Override
	public void insertRole(ERS_User_Role newRole) 
	{
		//open a new session
		Session ses = HibernateUtil.getSession();
		//insert the new role into the table
		ses.save(newRole);
		//close the session
		HibernateUtil.closeSession();
		
	}

	@Override
	public void updateRole(ERS_User_Role role) {
		
		//open a new session
		Session ses = HibernateUtil.getSession();
		//update the role in the table
		ses.merge(role);
		//close the session
		HibernateUtil.closeSession();
		
	}

	@Override
	public void deleteRole(ERS_User_Role role) 
	{
		Session ses = HibernateUtil.getSession();
		//update the role in the table
		ses.delete(role);
		//close the session
		HibernateUtil.closeSession();
		
	}



}
