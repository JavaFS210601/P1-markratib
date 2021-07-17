package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ers_user_role")
public class ERS_User_Role 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ers_user_role_id")
	private int id;
	
	@Column(name = "user_role", length = 10)
	private String role;
	
	public ERS_User_Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ERS_User_Role(String role) {
		super();
		this.role = role;
	}

	public ERS_User_Role(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	@Override
	public String toString() {
		return "ERS_User_Role [id=" + id + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ERS_User_Role other = (ERS_User_Role) obj;
		if (id != other.id)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

//	public void setId(int id) {
//		this.id = id;
//	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
