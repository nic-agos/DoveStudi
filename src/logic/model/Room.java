package logic.model;

import logic.bean.*;

public class Room {
	
	private int id;
	
	private String name;
	
	private String description;
	
	private String address;
	
	private int numPartecipants;

	private int numAvailableSeats;
	
	private String date;
	
	private String startTime;
	
	private String endTime;
	
	private String owner;
	

	public Room(String name, String description, String address, int numPartecipants, int numAvailableSeats, String date, String startTime, String endTime, String owner) {
		
		this.name = name;
		
		this.description = description;
		
		this.address = address;
		
		this.numPartecipants = numPartecipants;
		
		this.numAvailableSeats = numAvailableSeats;
		
		this.date = date;
		
		this.startTime = startTime;
		
		this.endTime = endTime;
		
		this.owner = owner;
		
	}
	
	public Room(RoomBean roomBean) {
		
		this(roomBean.getName(), roomBean.getDescription(), roomBean.getAddress(), roomBean.getNumPartecipants(), 
				roomBean.getNumAvailableSeats(), roomBean.getDate(),roomBean.getStartTime(), roomBean.getEndTime(), roomBean.getOwner());
		
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
	
	public void setDescription (String description) {
		this.description = description;
		
	}
	
	public String getDescription () {
		return this.description;
		
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
	
	public void setDate(String date) {
		this.date = date;
		
	}
	
	public String getDate() {
		return this.date;
		
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
		
	}
	
	public String getStartTime() {
		return this.startTime;
		
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
		
	}
	
	public String getEndTime() {
		return this.endTime;
		
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
		
	}
	
	public String getOwner() {
		return this.owner;
		
	}
	
	
}
