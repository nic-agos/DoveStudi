package logic.model;

import java.sql.SQLException;

import logic.bean.ReservationBean;
import logic.model.dao.*;

public class Reservation {
	
	private int id;

	private Account reservingUser;
	
	private Room linkedRoom;
	
	private Person roomOwner;
	
	private boolean isGroup;
	
	private String date;
	
	private String startTime;
	
	private String endTime;
	
	public Reservation(Account reservingUser, Room linkedRoom, Person roomOwner, boolean isGroup, String date, String startTime, String endTime ) {
		
		this.reservingUser = reservingUser;
		
		this.linkedRoom = linkedRoom;
		
		this.roomOwner = roomOwner;
		
		this.isGroup = isGroup;
		
		this.date = date;
		
		this.startTime = startTime;
		
		this.endTime = endTime;
	}
	
	public Reservation(boolean isGroup, String date, String startTime, String endTime) {
		
		this.isGroup = isGroup;
		
		this.date = date;
		
		this.startTime = startTime;
		
		this.endTime = endTime;
	}
	
	public Reservation(ReservationBean reservationBean) throws SQLException {
		
		this(reservationBean.getIsGroup(), reservationBean.getDate(), reservationBean.getStartTime(), reservationBean.getEndTime());
		
		AccountDAOImpl dao1 = new AccountDAOImpl();
		
		this.reservingUser = new Account(dao1.getAccount(reservationBean.getReservingUser()));
		
		RoomDAOImpl dao2 = new RoomDAOImpl();
		
		this.linkedRoom = new Room(dao2.getRoom(reservationBean.getRoomOwner()));
		
		PersonDAOImpl dao3 = new PersonDAOImpl();
		
		this.roomOwner = new Person(dao3.getPerson(reservationBean.getRoomOwner()));
		
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
	
	public boolean getIsGroup() {
		return this.isGroup;
		
	}
	
	public void setIsGroup (boolean isGroup) {
		this.isGroup = isGroup;
		
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
