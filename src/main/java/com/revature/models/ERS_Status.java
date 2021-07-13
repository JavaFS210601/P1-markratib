 package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ers_reimbursement_status")
public class ERS_Status 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimb_status_id")
//	@OneToMany(mappedBy = "ERS_Status", fetch=FetchType.EAGER)
	private int id;
	
	@Column(name = "reimb_status", length = 10)
	private String status;
	
	public ERS_Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ERS_Status(String status) {
		super();
		this.status = status;
	}

	public ERS_Status(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	@Override
	public String toString() {
		return "ERS_Status [id=" + id + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ERS_Status other = (ERS_Status) obj;
		if (id != other.id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

//	public void setId(int id) {
//		this.id = id;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
