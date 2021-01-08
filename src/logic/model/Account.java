package logic.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;
import logic.model.dao.*;

public class Account {

	private String cf;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private String dateBirth;
	
	private int numberToken;
	
	private List<Reservation> reservations;
	
	private List<Room> rooms;
	
	private Person person;
	
	private List<Group> administeredGroups;
	
	public Account(String cf, String name, String surname, String email, String password, String dateBirth, int numberToken) {
		
		this.cf = cf;
		
		this.name = name;
		
		this.surname = surname;
		
		this.email = email;
		
		this.password = password;
		
		this.dateBirth = dateBirth;
		
		this.numberToken = numberToken;
		
		this.reservations = new ArrayList<>();
		
		this.rooms = new ArrayList<>();
		
		this.administeredGroups = new ArrayList<>();
		
	}
	
	public Account(AccountBean accountBean) throws SQLException {
		
		this(accountBean.getCf(), accountBean.getName(), accountBean.getSurname(), accountBean.getEmail(), 
				accountBean.getPassword(), accountBean.getDateBirth(), accountBean.getNumberToken());
		
/*	ReservationDAOImpl dao1 = new ReservationDAOImpl();
		
		List<ReservationBean> reservationBeans = dao1.getAllAccountReservations(accountBean);
		if(!reservationBeans.isEmpty()) {
			for(ReservationBean reservationBean : reservationBeans) {
				this.reservations.add(new Reservation(reservationBean));
			}
		}
		
		RoomDAOImpl dao2 = new RoomDAOImpl(); 
		
		List<RoomBean> roomBeans = dao2.getAllAccountRooms(accountBean);
		if(!roomBeans.isEmpty()) {
			for(RoomBean roomBean : roomBeans) {
				this.rooms.add(new Room(roomBean));
			}	
		}
		
		GroupDAOImpl dao4 = new GroupDAOImpl();
		
		List<GroupBean> groupBeans = dao4.getAllAdministeredGroups(accountBean);
		if(!groupBeans.isEmpty()) {
			for(GroupBean groupBean : groupBeans) {
				this.administeredGroups.add(new Group(groupBean));
			}
		}
		
		PersonDAOImpl dao3 = new PersonDAOImpl();
		
		PersonBean personBean = dao3.getPersonFromAccount(accountBean);
		if(personBean.getId() != 0) {
			this.person = new Person(personBean);
		}
*/
		
	}
	
	public void setCf(String cf) {
		this.cf = cf;
		
	}
	
	public String getCf() {
		return this.cf;
		
	}
	
	public void setName(String name) {
		this.name = name;
		
	}
	
	public String getName() {
		return this.name;
		
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
		
	}
	
	public String getSurname() {
		return this.surname;
		
	}
	
	public void setEmail(String email) {
		this.email = email;
		
	}
	
	public String getEmail() {
		return this.email;
		
	}
	
	public void setPassword(String password) {
		this.password = password;
		
	}
	
	public String getPassword() {
		return this.password;
		
	}
	
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
		
	}
	
	public String getDateBirth() {
		return this.dateBirth;
		
	}
	
	public void setNumberToken(int numberToken) {
		this.numberToken = numberToken;
		
	}
	
	public int getNumberToken() {
		return this.numberToken;
		
	}
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	
	}
	
	public List<Reservation> getReservations(){
		return this.reservations;
		
	}
	
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
		
	}
	
	public List<Room> getRooms(){
		return this.rooms;
		
	}
	
	public void setPerson(Person person) {
		this.person = person;
		
	}
	
	public Person getPerson() {
		return this.person;
		
	}
}
