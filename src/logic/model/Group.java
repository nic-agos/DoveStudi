package logic.model;

import java.util.List;

import logic.bean.*;

public class Group {
	
	private int id;
	
	private String name;
	
	private Account admin;
	
	private int numParticipants;
	
	private Person participant;
	
	private List<Person> participants;
	
	
	public Group(String name, int numParticipants) {
		
		this.name = name;
		
		this.numParticipants = numParticipants;
		
	}
	
	public Group(String name, Account admin, int numParticipants, Person participant) {
		
		this(name, numParticipants);
		
		this.admin = admin;
		
		this.participant = participant;
		
	}
	
	public Group(GroupBean groupBean) {


		this(groupBean.getName(), groupBean.getNumParticipants());
		
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
	
	public void setNumParticipants(int numParticipants) {
		this.numParticipants = numParticipants;
		
	}
	
	public int getNumParticipants() {
		return this.numParticipants;
		
	}
	
	public void setParticipant(Person participant) {
		this.participant = participant;
		
	}
	
	public Person getParticipant() {
		return this.participant;
		
	}
	
	public void setParticipants(List<Person> participants) {
		this.participants = participants;
		
	}
	
	public List<Person> getParticipants(){
		return this.participants;
		
	}
}
