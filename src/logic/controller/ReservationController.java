package logic.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import logic.exception.*;
import logic.model.*;
import logic.model.dao.*;
import logic.bean.*;

public class ReservationController {

//	implemented with singleton pattern
	private static ReservationController instance = null;
	
	private ReservationController() {
		
	}
	
	public static synchronized ReservationController getInstance() {
		if(ReservationController.instance == null) {
			ReservationController.instance = new ReservationController();
		}
		return instance;
	}
	
	/*
	 	* public List<Reservation> removeReservation() {
		* 
		* }
	 */
	
	public List<Reservation> getMyPastReservations(AccountBean accountBean) throws DatabaseException {
		
		ReservationDAOImpl reservationDao = ReservationDAOImpl.getInstance();
		RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
		RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		List<Reservation> futureReservationsList = new ArrayList<>();
		List<ReservationBean> reservationsList;
		AccountBean reservingUser;
		AccountBean tempRoomOwnerBean = new AccountBean();
		AccountBean roomOwnerAccountBean;
		RoomBean roomBean;
		RoomBean tempRoomBean = new RoomBean();
		RoomSpecBean roomSpecBean;
		PersonBean roomOwnerPersonBean;
		PersonBean tempResRoomOwnerBean = new PersonBean();
		
		Person roomOwnerPerson;
		Room room;
		RoomSpec roomSpec;
		Account roomOwnerAccount;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now();
		
		try {
			reservationsList = reservationDao.getAllAccountReservations(accountBean);
			
			for(ReservationBean resBean : reservationsList) {
			  
				String dateTemp = resBean.getDate() + " " + resBean.getEndTime();
				LocalDateTime resDateTime = LocalDateTime.parse(dateTemp, formatter);
			  
				if(currentDate.compareTo(resDateTime) > 0) {
					Reservation reservation = new Reservation(resBean);

//					getting the reserving user form db and linked the Account entity
					reservingUser = accountDao.getAccount(accountBean);
					reservation.setReservingUser(new Account(reservingUser));

//					getting the room from db and creates the entity
					tempRoomBean.setId(resBean.getLinkedRoom());
					roomBean = roomDao.getRoom(tempRoomBean);
					room = new Room (roomBean);
					
//					getting the room specification from db and creates the entity
					roomSpecBean = roomSpecDao.getRoomSpec(roomBean);
					roomSpec = new RoomSpec(roomSpecBean);

//					link the specification entity to the room entity
					room.setSpecification(roomSpec);

//					getting the room owner Account from db and creates the entity
					tempRoomOwnerBean.setCf(roomBean.getOwner());
					roomOwnerAccountBean = accountDao.getAccount(tempRoomOwnerBean);
					roomOwnerAccount = new Account(roomOwnerAccountBean);
					
//					link the room owner Account to the room					
					room.setOwner(roomOwnerAccount);

//					link the room to the reservation
					reservation.setLinkedRoom(room);
					
//					getting the room owner Person (reservation) from db and creates the entity				
					tempResRoomOwnerBean.setId(resBean.getRoomOwner());
					roomOwnerPersonBean = personDao.getPerson(tempResRoomOwnerBean);
					roomOwnerPerson = new Person(roomOwnerPersonBean);
					
//					link the room owner account(reservation) to the person(reservation) entity					
					roomOwnerPerson.setAccount(roomOwnerAccount);
					
//					link the room owner person (reservation) to the reservation entity			
					reservation.setRoomOwner(roomOwnerPerson);
					
					futureReservationsList.add(reservation);
				}
			}
			 
			return futureReservationsList;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
				
		}	
	}
	
	public List<Reservation> getMyFutureReservations(AccountBean accountBean) throws DatabaseException {
		
		ReservationDAOImpl reservationDao = ReservationDAOImpl.getInstance();
		RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
		RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		List<Reservation> futureReservationsList = new ArrayList<>();
		List<ReservationBean> reservationsList;
		AccountBean reservingUser;
		AccountBean tempRoomOwnerBean = new AccountBean();
		AccountBean roomOwnerAccountBean;
		RoomBean roomBean;
		RoomBean tempRoomBean = new RoomBean();
		RoomSpecBean roomSpecBean;
		PersonBean roomOwnerPersonBean;
		PersonBean tempResRoomOwnerBean = new PersonBean();
		
		Person roomOwnerPerson;
		Room room;
		RoomSpec roomSpec;
		Account roomOwnerAccount;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now();
		
		try {
			reservationsList = reservationDao.getAllAccountReservations(accountBean);
			
			for(ReservationBean resBean : reservationsList) {
			  
				String dateTemp = resBean.getDate() + " " + resBean.getEndTime();
				LocalDateTime resDateTime = LocalDateTime.parse(dateTemp, formatter);
			  
				if(currentDate.compareTo(resDateTime) < 0) {
					Reservation reservation = new Reservation(resBean);

//					getting the reserving user form db and linked the Account entity
					reservingUser = accountDao.getAccount(accountBean);
					reservation.setReservingUser(new Account(reservingUser));

//					getting the room from db and creates the entity
					tempRoomBean.setId(resBean.getLinkedRoom());
					roomBean = roomDao.getRoom(tempRoomBean);
					room = new Room (roomBean);
					
//					getting the room specification from db and creates the entity
					roomSpecBean = roomSpecDao.getRoomSpec(roomBean);
					roomSpec = new RoomSpec(roomSpecBean);

//					link the specification entity to the room entity
					room.setSpecification(roomSpec);

//					getting the room owner Account from db and creates the entity
					tempRoomOwnerBean.setCf(roomBean.getOwner());
					roomOwnerAccountBean = accountDao.getAccount(tempRoomOwnerBean);
					roomOwnerAccount = new Account(roomOwnerAccountBean);
					
//					link the room owner Account to the room					
					room.setOwner(roomOwnerAccount);

//					link the room to the reservation
					reservation.setLinkedRoom(room);
					
//					getting the room owner Person (reservation) from db and creates the entity				
					tempResRoomOwnerBean.setId(resBean.getRoomOwner());
					roomOwnerPersonBean = personDao.getPerson(tempResRoomOwnerBean);
					roomOwnerPerson = new Person(roomOwnerPersonBean);
					
//					link the room owner account(reservation) to the person(reservation) entity					
					roomOwnerPerson.setAccount(roomOwnerAccount);
					
//					link the room owner person (reservation) to the reservation entity			
					reservation.setRoomOwner(roomOwnerPerson);
					
					futureReservationsList.add(reservation);
				}
			}
			 
			return futureReservationsList;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
				
		}		
	}
}
