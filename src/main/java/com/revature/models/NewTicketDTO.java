package com.revature.models;

public class NewTicketDTO 
{
	private String username;
	private String amount;
	private String type;
	private String desc;
	private String date;
	
	public NewTicketDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewTicketDTO(String username, String amount, String type, String date) {
		super();
		this.username = username;
		this.amount = amount;
		this.type = type;
		this.desc = desc;
		this.date = date;
	}

	
	@Override
	public String toString() {
		return "NewTicketDTO [username=" + username + ", amount=" + amount + ", type=" + type +", desc=" + desc + ", date=" + date + "]";
	}

	public String getUsername() {
		return username;
	}

	public String getAmount() {
		return amount;
	}

	public String getType() {
		return type;
	}
	
	public String getDesc()
	{
		return desc;
	}
	public String getDate() {
		return date;
	}
	
	
}
