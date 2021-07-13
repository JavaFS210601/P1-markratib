package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAOInterface 
{
	//insert a new user into the database
	void insertUser(User newUser);
	//get a single user by their id
	public User getUserById(int user_id);
	//get all users
	public List<User> getAllUsers();
	//get all users with a specified id
	public List<User> getUsersByRole(int user_role_id);
	//update a single user
	public void updateUser(User user);
	//delete a single user
	public void deleteUser(User user);
}
