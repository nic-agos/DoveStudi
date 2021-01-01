package logic.model;

import java.sql.SQLException;

import logic.bean.*;
import logic.model.dao.*;

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
		
		this.name = name;
		
		this.admin = admin;
		
		this.numPartecipants = numPartecipants;
		
		this.partecipant = partecipant;
		
	}
	
	public Group(GroupBean groupBean) throws SQLException {
		this(groupBean.getName(), groupBean.getNumPartecipants());
		
		AccountDAOImpl dao1 = new AccountDAOImpl();
		
		this.admin = new Account(dao1.getAccount(groupBean.getAdmin()));
		
		PersonDAOImpl dao2 = new PersonDAOImpl();
		
		this.partecipant = new Person(dao2.getPerson(groupBean.getPartecipant()));
		
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
