package logic.model;

import logic.bean.*;
import java.time.LocalDate;

public class Room {
	
	private int id;
	
	private String name;
	
	private String description;
	
	private String address;
	
	private int numPartecipants;

	private int numAvailableSeats;
	
	private LocalDate date;
	
	private String startTime;
	
	private String endTime;
	

	public Room(String name, String description, String address, int numPartecipants, int numAvailableSeats, LocalDate date, String startTime, String endTime) {
		
		this.name = name;
		
		this.description = description;
		
		this.address = address;
		
		this.numPartecipants = numPartecipants;
		
		this.numAvailableSeats = numAvailableSeats;
		
		this.date = date;
		
		this.startTime = startTime;
		
		this.endTime = endTime;
		
	}
	
	public Room(RoomBean roomBean) {
		// not yet implemented
		
	}
	
	public void setId (int id) {
		this.id = id;
		
	}
	
	public int getId () {
		return this.id;
		
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
	
	public void setDate(LocalDate date) {
		this.date = date;
		
	}
	
	public LocalDate getDate() {
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
	
	
	
}
