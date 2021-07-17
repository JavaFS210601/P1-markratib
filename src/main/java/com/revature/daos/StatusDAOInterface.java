package com.revature.daos;

import java.util.List;

import com.revature.models.ERS_Status;

public interface StatusDAOInterface 
{
	//get a status by it's ID
	public ERS_Status getStatus(int id);
	//get all statuses
	public List<ERS_Status> getAllStatus();
	//insert a new status to the DB
	public void insertStatus(ERS_Status newStatus);
	//update a status already in the database
	public void updateStatus(ERS_Status status);
	//delete a status
	public void deleteStatus(ERS_Status status);
	
}
