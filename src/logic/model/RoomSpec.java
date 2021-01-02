package logic.model;

import logic.bean.RoomSpecBean;

public class RoomSpec {

	private int id;
	
	private String description;
	
	private String date;
	
	private String startTime;
	
	private String endTime;
	
	public RoomSpec(String description, String date, String startTime, String endTime) {
		
		this.description = description;
		
		this.date = date;
		
		this.startTime = startTime;
		
		this.endTime = endTime;
		
	}
	
	public RoomSpec(int id, String description, String date, String startTime, String endTime) {
		
		this(description, date, startTime, endTime);
		
		this.id = id;
		
	}
	
	public RoomSpec(RoomSpecBean roomSpecBean) {
		
		this(roomSpecBean.getId(), roomSpecBean.getDescription(), roomSpecBean.getDate(), roomSpecBean.getStartTime(), roomSpecBean.getEndTime());
	
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
	
	
}
