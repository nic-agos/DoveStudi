package logic.bean;

public class RoomBean {
	
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
	
	public RoomBean() {
		
	}
	
public RoomBean(String name, String description, String address, int numPartecipants, int numAvailableSeats, String date, String startTime, String endTime, String owner) {
		
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
	
	public int getId () {
		return this.id;
		
	}

	public String getName() {
		return this.name;
		
	}
	
	public String getDescription() {
		return this.description;
		
	}
	
	public String getAddress() {
		return this.address;
		
	}
	
	public int getNumPartecipants() {
		return this.numPartecipants;
		
	}
	
	public int getNumAvailableSeats() {
		return this.numAvailableSeats;
		
	}
	
	public String getDate() {
		return this.date;
		
	}
	
	public String getStartTime() {
		return this.startTime;
		
	}
	
	public String getEndTime() {
		return this.endTime;
		
	}
	
	public void setId (int id) {
		this.id = id;
		
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
		
	}
	
	public String getOwner() {
		return this.owner;
		
	}
}
