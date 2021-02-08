package logic.model;

import java.util.*;

import logic.bean.*;

public class Room {
	
private int id;
	
	private String name;
	
	private String address;
	
	private int numParticipants;

	private int numAvailableSeats;
	
	private Account owner;
	
	private RoomSpec specification;
	
	private List<Person> participants;
	
	public Room(String name, String address, int numParticipants, int numAvailableSeats) {
		
		this.name = name;
		
		this.address = address;
		
		this.numParticipants = numParticipants;
		
		this.numAvailableSeats = numAvailableSeats;
		
	}
	
	public Room(String name, String address, int numParticipants, int numAvailableSeats, Account owner, RoomSpec specification) {
		
		this(name, address, numParticipants, numAvailableSeats);
		
		this.owner = owner;
		
		this.specification = specification;
		
	}
	
	public Room(RoomBean roomBean) {
		
		this(roomBean.getName(), roomBean.getAddress(), roomBean.getNumParticipants(), roomBean.getNumAvailableSeats());

		this.id = roomBean.getId();
	}
	
	public void setId (int id) {
		this.id = id;
		
	}
	
	public int getId () {
		return this.id;
		
	}
	
	public void setName(String name) {
		this.name = name;
		
	}
	
	public String getName() {
		return this.name;
		
	}
	
	public void setAddress(String address) {
		this.address = address;
		
	}
	
	public String getAddress() {
		return this.address;
		
	}
	
	public void setNumParticipants(int numParticipants) {
		this.numParticipants = numParticipants;
		
	}
	
	public int getNumParticipants() {
		return this.numParticipants;
		
	}
	
	public void setNumAvailableSeats(int numAvailableSeats) {
		this.numAvailableSeats = numAvailableSeats;
		
	}
	
	public int getNumAvailableSeats() {
		return this.numAvailableSeats;
		
	}
	
	public void setOwner(Account owner) {
		this.owner = owner;
		
	}
	
	public Account getOwner() {
		return this.owner;
		
	}
	
	public void setSpecification(RoomSpec specification) {
		this.specification = specification;
		
	}
	
	public RoomSpec getSpecification() {
		return this.specification;
		
	}
	
	public void setParticipants(List<Person> participants) {
		this.participants = participants;
		
	}
	
	public List<Person> getParticipants(){
		return this.participants;
		
	}
}
