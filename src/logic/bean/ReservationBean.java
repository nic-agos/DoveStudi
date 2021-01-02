package logic.bean;

public class ReservationBean {
	
	private int id;

	private String reservingUser;
	
	private int linkedRoom;
	
	private int roomOwner;
	
	private String date;
	
	private String startTime;
	
	private String endTime;
	
	public ReservationBean() {
		
	}
	
	public ReservationBean(String reservingUser, int linkedRoom, int roomOwner, String date, String startTime, String endTime ) {
		
		this.reservingUser = reservingUser;
		
		this.linkedRoom = linkedRoom;
		
		this.roomOwner = roomOwner;
		
		this.date = date;
		
		this.startTime = startTime;
		
		this.endTime = endTime;
	}
	
	public ReservationBean(int id, String reservingUser, int linkedRoom, int roomOwner, String date, String startTime, String endTime ) {
		
		this.id = id;
		
		this.reservingUser = reservingUser;
		
		this.linkedRoom = linkedRoom;
		
		this.roomOwner = roomOwner;
		
		this.date = date;
		
		this.startTime = startTime;
		
		this.endTime = endTime;
	}
	
	public int getId() {
		return this.id;
			
	}
	public void setId(int id) {
		this.id = id;
		
	}
	
	public String getReservingUser() {
		return this.reservingUser;
		
	}
	
	public void setReservingUser(String reservingUser) {
		this.reservingUser = reservingUser;
		
	}
	
	public int getLinkedRoom() {
		return this.linkedRoom;
		
	}
	
	public void setLinkedRoom(int linkedRoom) {
		this.linkedRoom = linkedRoom;
		
	}
	
	public int getRoomOwner() {
		return this.roomOwner;
		
	}
	
	public void setRoomOwner(int roomOwner) {
		this.roomOwner = roomOwner;
		
	}
	
	public String getDate() {
		return this.date;
		
	}
	
	public void setDate(String date) {
		this.date = date;
		
	}
	
	public String getStartTime() {
		return this.startTime;
		
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
		
	}
	
	public String getEndTime() {
		return this.endTime;
		
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
		
	}
}
