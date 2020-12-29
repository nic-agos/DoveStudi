package logic.bean;

import java.util.ArrayList;
import java.util.List;

import logic.model.Reservation;
import logic.model.Room;

public class AccountBean {

private String CF;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private String dateBirth;
	
	private String cityBirth;
	
	private int numberToken;
	
	private List<Reservation> reservations;
	
	private List<Room> rooms;
	
	public AccountBean(String cf, String name, String surname, String email, String password, String dateBirth, String cityBirth, int numberToken) {
		
		this.CF = cf;
		
		this.name = name;
		
		this.surname = surname;
		
		this.email = email;
		
		this.password = password;
		
		this.dateBirth = dateBirth;
		
		this.cityBirth = cityBirth;
		
		this.numberToken = numberToken;
		
		this.reservations = new ArrayList<>();
		
		this.rooms = new ArrayList<>();
		
	}
	
	public AccountBean(AccountBean accountBean) {
		
	}
	
	public void setCF(String cf) {
		this.CF = cf;
		
	}
	
	public String getCF() {
		return this.CF;
		
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
	
	public void setCityBirth(String cityBirth) {
		this.cityBirth = cityBirth;
		
	}
	
	public String getCityBirth() {
		return this.cityBirth;
		
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
}
