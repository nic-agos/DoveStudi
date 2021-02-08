package logic.bean;

import logic.exception.*;

public class RoomBean {
	
	private int id;
	
	private String name;
	
	private String address;
	
	private int numParticipants;

	private int numAvailableSeats;
	
	private String owner;
	
	private int specification;
	
	public RoomBean() {
		
	}
	
	public RoomBean(String name, String address, int numPartecipants, int numAvailableSeats, String owner, int specification) {
		
		this.name = name;
		
		this.address = address;
		
		this.numParticipants = numPartecipants;
		
		this.numAvailableSeats = numAvailableSeats;
		
		this.owner = owner;
		
		this.specification = specification;
		
	}
	
	public RoomBean(int id, String name, String address, int numPartecipants, int numAvailableSeats, String owner, int specification) {
		
		this(name, address, numPartecipants, numAvailableSeats, owner, specification);
		
		this.id = id;
		
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
	
	public void setOwner(String owner) {
		this.owner = owner;
		
	}
	
	public String getOwner() {
		return this.owner;
		
	}
	
	public void setSpecification(int specification) {
		this.specification = specification;
		
	}
	
	public int getSpecification() {
		return this.specification;
		
	}
	
	public void validate() throws RoomException {
		
		String errors = "";
		
		if(this.name.length() > 45 || this.name.isBlank()) {
			errors = errors + "Invalid room title   ";
		}
		if(this.address.length() > 100 || this.address.isBlank()) {
			errors = errors + "Invalid room address   ";
		}
		if(this.name.length() > 45 || this.name.isBlank()) {
			errors = errors + "Invalid room title   ";
		}
		
		if(!errors.isEmpty()) {
			
			throw new RoomException(errors);
		}	
	}
	
}
