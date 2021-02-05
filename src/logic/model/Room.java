package logic.model;

import java.util.*;

import logic.bean.*;

public class Room {
	
private int id;
	
	private String name;
	
	private String address;
	
	private int numPartecipants;

	private int numAvailableSeats;
	
	private Account owner;
	
	private RoomSpec specification;
	
	private List<Person> partecipants;
	
	public Room(String name, String address, int numPartecipants, int numAvailableSeats) {
		
		this.name = name;
		
		this.address = address;
		
		this.numPartecipants = numPartecipants;
		
		this.numAvailableSeats = numAvailableSeats;
		
	}
	
	public Room(String name, String address, int numPartecipants, int numAvailableSeats, Account owner, RoomSpec specification) {
		
		this(name, address, numPartecipants, numAvailableSeats);
		
		this.owner = owner;
		
		this.specification = specification;
		
	}
	
	public Room(RoomBean roomBean) {
		
		this(roomBean.getName(), roomBean.getAddress(), roomBean.getNumPartecipants(), roomBean.getNumAvailableSeats());

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
	
	public void setNumPartecipants(int numPartecipants) {
		this.numPartecipants = numPartecipants;
		
	}
	
	public int getNumPartecipants() {
		return this.numPartecipants;
		
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
	
	public void setPartecipants(List<Person> partecipants) {
		this.partecipants = partecipants;
		
	}
	
	public List<Person> getPartecipants(){
		return this.partecipants;
		
	}
}
