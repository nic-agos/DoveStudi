package logic.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;
import logic.model.dao.*;
import logic.exception.*;
import logic.model.*;

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
	
//	takes in input the id of the room and and the cf of the user
	public boolean makeReservation(RoomBean roomBean, AccountBean accountBean) throws DatabaseException, RoomException, AccountException, ReservationException {
		
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		ReservationDAOImpl reservationDao = ReservationDAOImpl.getInstance();
		RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
		RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		PersonBean pBean;
		AccountBean aBean = new AccountBean();
		AccountBean a2Bean;
		RoomBean rBean;
		RoomBean r2Bean = new RoomBean();
		RoomSpecBean rsBean;
		ReservationBean resBean = new ReservationBean();
		
		try {
			rBean = roomDao.getRoom(roomBean);
			a2Bean = accountDao.getAccount(accountBean);
			if(a2Bean.getNumberToken() > 0) {
				
				if ((rBean.getNumAvailableSeats()-1) >=0) {
					
					aBean.setCf(rBean.getOwner());
					pBean = personDao.getPersonFromAccount(aBean);
					rsBean = roomSpecDao.getRoomSpec(rBean);
					
					resBean.setReservingUser(accountBean.getCf());
					resBean.setLinkedRoom(rBean.getId());
					resBean.setRoomOwner(pBean.getId());
					resBean.setDate(rsBean.getDate());
					resBean.setStartTime(rsBean.getStartTime());
					resBean.setEndTime(rsBean.getEndTime());
					
					r2Bean.setId(rBean.getId());
					r2Bean.setNumParticipants(rBean.getNumParticipants());
					r2Bean.setNumAvailableSeats(rBean.getNumAvailableSeats()-1);
					
//					check if the user is already booked for the room
					if(reservationDao.getReservationId(resBean) == 0) {
						
//						update the number of token of reserving user
						a2Bean.setNumberToken(a2Bean.getNumberToken()-1);
						accountDao.updateNumberToken(a2Bean);
						
//						update the number of available seats for the room					
						roomDao.updateRoom(r2Bean);
		
						return (reservationDao.createReservation(resBean) != 0);
					}else {
						throw new ReservationException("You are already booked for this room ");
					}
				
				}else {
					throw new RoomException(rBean.getId() + " does not have enough seats");
				}
			}else {
				throw new AccountException("You does not have enough token to book this room, buy its!");
			}
			
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}	
	}
	
//	takes in input the id of a reservation	
	public boolean deleteReservation(ReservationBean reservationBean) throws DatabaseException, ReservationException {
		
		ReservationDAOImpl reservationDao = ReservationDAOImpl.getInstance();
		RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
		RoomBean roomBean;
		RoomBean tempRoomBean = new RoomBean();
		ReservationBean resBean;
		
		try {
			
			resBean = reservationDao.getReservation(reservationBean);
			tempRoomBean.setId(resBean.getLinkedRoom());
			
			if(reservationDao.removeReservation(reservationBean) != 0) {
			
				roomBean = roomDao.getRoom(tempRoomBean);
				roomBean.setNumAvailableSeats(roomBean.getNumAvailableSeats()+1);
				return (roomDao.updateRoom(roomBean)!=0);
				
			}else {
				throw new ReservationException("Unable to delete your reservation");
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
		
	}

//	get in input the cf of the user and return a list of reservation
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

//	get in input the cf of the user and return a list of reservation
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
	
//	takes in input the room id and return a list of person that will participate to the room
	public List<Person> getAllRoomParticipants(RoomBean roomBean) throws DatabaseException {
		
		ReservationDAOImpl reservationDao = ReservationDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		List<Person> roomParticipants = new ArrayList<>();
		List<ReservationBean> roomParticipantsBean;
		
		PersonBean persBean;
		AccountBean tempAccBean = new AccountBean();
		AccountBean accBean;
		Account account;
		Person person;
		
		
		try {
			
			roomParticipantsBean = reservationDao.getRoomReservations(roomBean);
			
				for(ReservationBean resBean : roomParticipantsBean) {
					
					tempAccBean.setCf(resBean.getReservingUser());
					accBean = accountDao.getAccount(tempAccBean);
					account = new Account(accBean);
					
					persBean = personDao.getPersonFromAccount(accBean);
					person = new Person(persBean);

					person.setAccount(account);
					
					roomParticipants.add(person);
				}
				
				return roomParticipants;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());		
		}
	}
}
