package com.revature.daos;

import java.util.List;

import com.revature.models.ERS_User_Role;

public interface UserRoleDAOInterface 
{
	//get a role by it's ID
	public ERS_User_Role getRole(int id);
	//get all rolees
	public List<ERS_User_Role> getAllRole();
	//insert a new role to the DB
	public void insertRole(ERS_User_Role newRole);
	//update a role already in the database
	public void updateRole(ERS_User_Role role);
	//delete a role
	public void deleteRole(ERS_User_Role role);
}
