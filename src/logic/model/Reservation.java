package logic.model;

import logic.bean.ReservationBean;

public class Reservation {
	
	private int id;

	private Account reservingUser;
	
	private Room linkedRoom;
	
	private Person roomOwner;
	
	private String date;
	
	private String startTime;
	
	private String endTime;
	
	public Reservation(String date, String startTime, String endTime) {
		
		this.date = date;
		
		this.startTime = startTime;
		
		this.endTime = endTime;
	}
	
	public Reservation(Account reservingUser, Room linkedRoom, Person roomOwner, String date, String startTime, String endTime ) {
		
		this(date, startTime, endTime);
	
		this.reservingUser = reservingUser;
		
		this.linkedRoom = linkedRoom;
		
		this.roomOwner = roomOwner;
		
	}
	
	public Reservation(ReservationBean reservationBean) {
		
		this(reservationBean.getDate(), reservationBean.getStartTime(), reservationBean.getEndTime());

		this.id = reservationBean.getId();
	}
	
	public int getId() {
		return this.id;
			
	}
	public void setId(int id) {
		this.id = id;
		
	}
	
	public Account getReservingUser() {
		return this.reservingUser;
		
	}
	
	public void setReservingUser(Account reservingUser) {
		this.reservingUser = reservingUser;
		
	}
	
	public Room getLinkedRoom() {
		return this.linkedRoom;
		
	}
	
	public void setLinkedRoom(Room linkedRoom) {
		this.linkedRoom = linkedRoom;
		
	}
	
	public Person getRoomOwner() {
		return this.roomOwner;
		
	}
	
	public void setRoomOwner(Person roomOwner) {
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
