package logic.bean;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import logic.exception.*;

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
	
	public void validate() throws RoomException, ParseException {
		
		String errors = "";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime currentDate = LocalDateTime.now();
		
		String dateTemp = this.date + " " + this.startTime;
		LocalDateTime resDateTime = LocalDateTime.parse(dateTemp, formatter);
		
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    Date d1 = sdf.parse(this.startTime);
	    Date d2 = sdf.parse(this.endTime);
		
		if(this.description.length() > 300 || this.description.isBlank()) {
			errors = errors + "description not valid   ";
		}
		if(this.cap.length() != 5) {
			errors = errors + "CAP not valid   ";
		}
		if(this.date.isBlank()) {
			errors = errors + "date not valid   ";
		}
		if(currentDate.compareTo(resDateTime) > 0) {
			errors = errors + "Past date not allowed   ";
		}
		if(d1.compareTo(d2) > 0) {
			errors = errors + "Start time must be earlier than end time   ";
		}
		
		if(!errors.isEmpty()) {
			
			throw new RoomException(errors);
		}	
	}
	
	public void validateDate() throws RoomException {
		
		if(this.date.length() != 10 || this.date.isBlank()) {
			
			throw new RoomException ("Date not valid, format YYYY:MM:DD required");
		}
	}
}
