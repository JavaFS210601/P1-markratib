package com.revature.daos;

import java.util.List;

import com.revature.models.ERS_Type;



public interface TypeDAOInterface 
{
	//get a type by it's ID
	public ERS_Type getType(int id);
	//get all typees
	public List<ERS_Type> getAllType();
	//insert a new type to the DB
	public void insertType(ERS_Type newType);
	//update a type already in the database
	public void updateType(ERS_Type type);
	//delete a type
	public void deleteType(ERS_Type type);
}
