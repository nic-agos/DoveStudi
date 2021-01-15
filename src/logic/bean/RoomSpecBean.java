package logic.bean;

public class RoomSpecBean {
	
	private int id;
	
	private String description;
	
	private String date;
	
	private String startTime;
	
	private String endTime;
	
	private String cap;
	
	public RoomSpecBean() {
		
	}
	
	public RoomSpecBean(String description, String date, String startTime, String endTime, String cap) {
		
		this.description = description;
		
		this.date = date;
		
		this.startTime = startTime;
		
		this.endTime = endTime;
		
		this.cap = cap;
		
	}
	
	public RoomSpecBean(int id, String description, String date, String startTime, String endTime, String cap) {
		
		this(description, date, startTime, endTime, cap);
		
		this.id = id;
		
	}
	
	public void setId (int id) {
		this.id = id;
		
	}
	
	public int getId () {
		return this.id;
		
	}
	
	public boolean setDescription (String description) {
		if (description.length() <= 300) {
			this.description = description;
			return true;
		}else {
			return false;
		}
		
	}
	
	public String getDescription() {
		return this.description;
		
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
	
	public void setCap(String cap) {
		this.cap = cap;
		
	}
	
	public String getCap() {
		return this.cap;
		
	}
}
