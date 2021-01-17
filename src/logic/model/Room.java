package logic.model;

import java.sql.SQLException;


import logic.bean.*;

public class Room {
	
private int id;
	
	private String name;
	
	private String address;
	
	private int numPartecipants;

	private int numAvailableSeats;
	
	private Account owner;
	
	private RoomSpec specification;
	
//	private List<Reservation> reservations;
	
	public Room(String name, String address, int numPartecipants, int numAvailableSeats) {
		
		this.name = name;
		
		this.address = address;
		
		this.numPartecipants = numPartecipants;
		
		this.numAvailableSeats = numAvailableSeats;
		
	}
	
	public Room(String name, String address, int numPartecipants, int numAvailableSeats, Account owner, RoomSpec specification) {
		
		this(name, address, numPartecipants, numAvailableSeats);
		
		this.owner = owner;
		
		this.specification = specification;
		
	}
	
	public Room(RoomBean roomBean) throws SQLException {
		
		this(roomBean.getName(), roomBean.getAddress(), roomBean.getNumPartecipants(), 
				roomBean.getNumAvailableSeats());
/*		
		AccountDAOImpl dao1 = new AccountDAOImpl();
		
		this.owner = new Account(dao1.getAccount(roomBean.getOwner()));
		
		RoomSpecDAOImpl dao2 = new RoomSpecDAOImpl();
		
		this.specification = new RoomSpec(dao2.getRoomSpec(roomBean.getSpecification()));
		
		ReservationDAOImpl dao3 = new ReservationDAOImpl();
		
		List<ReservationBean> reservationBeans = dao3.getRoomReservations(roomBean);
		if(!reservationBeans.isEmpty()) {
			for(ReservationBean reservationBean : reservationBeans) {
				this.reservations.add(new Reservation(reservationBean));
			}
		}
*/	
		this.id = roomBean.getId();
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
	
	public void setOwner(Account owner) {
		this.owner = owner;
		
	}
	
	public Account getOwner() {
		return this.owner;
		
	}
	
	public void setSpecification(RoomSpec specification) {
		this.specification = specification;
		
	}
	
	public RoomSpec getSpecification() {
		return this.specification;
		
	}
	
/*	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
		
	}
	
	public List<Reservation> getReservations(){
		return this.reservations;
		
	}
*/
}
