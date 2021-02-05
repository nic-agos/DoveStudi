package logic.model;

import logic.bean.*;

public class Group {
	
	private int id;
	
	private String name;
	
	private Account admin;
	
	private int numPartecipants;
	
	private Person partecipant;
	
	
	public Group(String name, int numPartecipants) {
		
		this.name = name;
		
		this.numPartecipants = numPartecipants;
		
	}
	
	public Group(String name, Account admin, int numPartecipants, Person partecipant) {
		
		this(name, numPartecipants);
		
		this.admin = admin;
		
		this.partecipant = partecipant;
		
	}
	
	public Group(GroupBean groupBean) {


		this(groupBean.getName(), groupBean.getNumPartecipants());
		
		this.id = groupBean.getId();
	}
	
	
	
	public void setId(int id) {
		this.id = id;
		
	}
	
	public int getId() {
		return this.id;
		
	}
	
	public void setName(String name) {
		this.name = name;
		
	}
	
	public String getName() {
		return this.name;
		
	}
	
	public void setAdmin(Account admin) {
		this.admin = admin;
		
	}
	
	public Account getAdmin() {
		return this.admin;
		
	}
	
	public void setNumPartecipants(int numPartecipants) {
		this.numPartecipants = numPartecipants;
		
	}
	
	public int getNumPartecipants() {
		return this.numPartecipants;
		
	}
	
	public void setPartecipant(Person partecipant) {
		this.partecipant = partecipant;
		
	}
	
	public Person getPartecipant() {
		return this.partecipant;
		
	}
}
