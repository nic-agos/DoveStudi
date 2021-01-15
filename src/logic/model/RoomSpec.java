package logic.model;

import logic.bean.RoomSpecBean;

public class RoomSpec {

	private int id;
	
	private String description;
	
	private String date;
	
	private String startTime;
	
	private String endTime;
	
	private String cap;
	
	public RoomSpec(String description, String date, String startTime, String endTime, String cap) {
		
		this.description = description;
		
		this.date = date;
		
		this.startTime = startTime;
		
		this.endTime = endTime;
		
		this.cap = cap;
		
	}
	
	public RoomSpec(int id, String description, String date, String startTime, String endTime, String cap) {
		
		this(description, date, startTime, endTime, cap);
		
		this.id = id;
		
	}
	
	public RoomSpec(RoomSpecBean roomSpecBean) {
		
		this(roomSpecBean.getId(), roomSpecBean.getDescription(), roomSpecBean.getDate(), roomSpecBean.getStartTime(), roomSpecBean.getEndTime(), roomSpecBean.getCap());
	
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
	
	public void setCap(String cap) {
		this.cap = cap;
		
	}
	
	public String getCap() {
		return this.cap;
		
	}
}
