package com.revature.daos;

import java.util.List;

import org.hibernate.Session;

import com.revature.models.ERS_Status;
import com.revature.utils.HibernateUtil;

public class StatusDAO implements StatusDAOInterface 
{

	@Override
	public ERS_Status getStatus(int id) 
	{
		//open a new sesssion
		Session ses = HibernateUtil.getSession();
		//get the status code based on the id
		ERS_Status status = ses.get(ERS_Status.class, id);
		//close the session
//		ses.close();
		HibernateUtil.closeSession();
		//return the status object
		return status;
	}
	
	@Override
	public List<ERS_Status> getAllStatus() 
	{
		//open a new session
		Session ses = HibernateUtil.getSession();
		//geta list of statuses
		List<ERS_Status> statusList = ses.createQuery("from ERS_Status").list();
		//close the session
		HibernateUtil.closeSession();
		//rethrn the list of statuses
		return statusList;
	}

	@Override
	public void insertStatus(ERS_Status newStatus) 
	{
		//open a new session
		Session ses = HibernateUtil.getSession();
		//insert the new status into the table
		ses.save(newStatus);
		//close the session
		HibernateUtil.closeSession();
	}
	
	@Override
	public void updateStatus(ERS_Status status)
	{
		//open a new session
		Session ses = HibernateUtil.getSession();
		//update the status in the table
		ses.merge(status);
		//close the session
		HibernateUtil.closeSession();
	}

	@Override
	public void deleteStatus(ERS_Status status) 
	{
		Session ses = HibernateUtil.getSession();
		//update the status in the table
		ses.delete(status);
		//close the session
		HibernateUtil.closeSession();
		
	}

}
