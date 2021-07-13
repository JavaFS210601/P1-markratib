package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.ERS_Type;
import com.revature.utils.HibernateUtil;

public class TypeDAO implements TypeDAOInterface 
{
	@Override
	public ERS_Type getType(int id) 
	{
		//open a new sesssion
		Session ses = HibernateUtil.getSession();
		//get the type code based on the id
		ERS_Type type = ses.get(ERS_Type.class, id);
		//close the session
//		ses.close();
		HibernateUtil.closeSession();
		//return the type object
		return type;
	}
	
	@Override
	public List<ERS_Type> getAllType() 
	{
		//open a new session
		Session ses = HibernateUtil.getSession();
		//geta list of typees
		List<ERS_Type> typeList = ses.createQuery("from ERS_Type").list();
		//close the session
		HibernateUtil.closeSession();
		//rethrn the list of typees
		return typeList;
	}

	@Override
	public void insertType(ERS_Type newType) 
	{
		//open a new session
		Session ses = HibernateUtil.getSession();
		//insert the new type into the table
		ses.save(newType);
		//close the session
		HibernateUtil.closeSession();
	}
	
	@Override
	public void updateType(ERS_Type type)
	{
		//open a new session
		Session ses = HibernateUtil.getSession();
		//update the type in the table
		ses.merge(type);
		//close the session
		HibernateUtil.closeSession();
	}

	@Override
	public void deleteType(ERS_Type type) 
	{
		Session ses = HibernateUtil.getSession();
		//update the type in the table
		ses.delete(type);
		//close the session
		HibernateUtil.closeSession();
		
	}
}
