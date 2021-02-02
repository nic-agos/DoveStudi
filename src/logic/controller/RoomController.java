package logic.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.exception.*;
import logic.model.*;
import logic.model.dao.*;
import logic.bean.*;

public class RoomController {

//	implemented with singleton pattern
	private static RoomController instance = null;
	
	private RoomController() {
		
	}
	
	public static synchronized RoomController getInstance() {
		if(RoomController.instance == null) {
			RoomController.instance = new RoomController();
		}
		return instance;
	}

//  takes in input a complete RoomBean and a complete RoomSpecBean (excepts the IDs)
	public boolean postRoom(RoomBean roomBean, RoomSpecBean roomSpecBean) throws DatabaseException {
		
		try{
			RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
			
			int spec = roomSpecDao.createRoomSpec(roomSpecBean);
			
			roomBean.setSpecification(spec);
			
			RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
			
//			at the end the number of available seats is the same of the number of partecipants			
			roomBean.setNumAvailableSeats(roomBean.getNumPartecipants());
			
			int id = roomDao.createRoom(roomBean);
			
			return (id != 0);
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}

//  takes in input the username of the host and return a list of user's rooms
	public List<Room> searchByHost(PersonBean personBean) throws DatabaseException, NotFoundException {
		
		List<RoomBean> roomBeans;
		List<Room> rooms = new ArrayList<>();
		PersonBean person;
		Room room;
		RoomSpec roomSpec;
		Account owner;
		
		try {
			PersonDAOImpl personDao = PersonDAOImpl.getInstance();
			
			person = personDao.getPersonByUsername(personBean);
			
			if(person != null) {
				String cf = person.getAccount();
				AccountBean temp1 = new AccountBean();
				temp1.setCf(cf);
				
				RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
				
				roomBeans = roomDao.getAllAccountRooms(temp1);
				RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
				AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
				
				for(RoomBean roomB: roomBeans) {
					room = new Room(roomB);
					roomSpec = new RoomSpec(roomSpecDao.getRoomSpec(roomB));
					room.setSpecification(roomSpec);
					owner = new Account(accountDao.getAccount(temp1));
					room.setOwner(owner);
					rooms.add(room);
				}
				return rooms;
			
			}else {
				throw new NotFoundException("Rooms of user " + personBean.getUsername());
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}

//	takes in input the cap and returns a list of rooms with that specificated cap
	public List<Room> searchByCap(RoomSpecBean roomSpecBean) throws DatabaseException, NotFoundException {
	
		List<RoomSpecBean> roomSpecBeans;
		List<Room> rooms = new ArrayList<>();
		RoomBean roomBean;
		Room room;
		AccountBean owner;
		AccountBean temp1 = new AccountBean();
		
		try {
			RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
			
			roomSpecBeans = roomSpecDao.getRoomsSpecByCap(roomSpecBean);
			
			RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
			
			AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
			
			if(!roomSpecBeans.isEmpty()) {
				for(RoomSpecBean rSpecBean: roomSpecBeans) {
					roomBean = roomDao.getRoomFromSpec(rSpecBean);
					room = new Room(roomBean);
					room.setSpecification(new RoomSpec(rSpecBean));
					temp1.setCf(roomBean.getOwner()); 
					owner = accountDao.getAccount(temp1);
					room.setOwner(new Account(owner));
					rooms.add(room);
				}
			return rooms;
			
			}else {
				throw new NotFoundException("Rooms with CAP " + roomSpecBean.getCap());
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
		
	}

//	takes in input a date and returns a list of rooms available in that date
	public List<Room> searchByDate(RoomSpecBean roomSpecBean) throws DatabaseException, NotFoundException {
		
		List<RoomSpecBean> roomSpecBeans;
		List<Room> rooms = new ArrayList<>();
		RoomBean roomBean;
		Room room;
		AccountBean owner;
		AccountBean temp1 = new AccountBean();
		
		try {
			RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
			
			roomSpecBeans = roomSpecDao.getRoomsSpecByDate(roomSpecBean);
			
			RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
			
			AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
			
			if(!roomSpecBeans.isEmpty()) {
				for(RoomSpecBean rSpecBean: roomSpecBeans) {
					roomBean = roomDao.getRoomFromSpec(rSpecBean);
					room = new Room(roomBean);
					room.setSpecification(new RoomSpec(rSpecBean));
					temp1.setCf(roomBean.getOwner()); 
					owner = accountDao.getAccount(temp1);
					room.setOwner(new Account(owner));
					rooms.add(room);
				}
			return rooms;
			
			}else {
				throw new NotFoundException("Rooms with Date " + roomSpecBean.getDate());
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}

// 	takes in input the number of seats and returns a list of rooms with at least that number of available seats
	public List<Room> searchByAvailableSeats(RoomBean roomBean) throws DatabaseException, NotFoundException {
		
		List<RoomBean> roomBeans;
		List<Room> rooms = new ArrayList<>();
		RoomSpecBean rSpecBean;
		RoomBean temp1 = new RoomBean();
		Room room;
		AccountBean owner;
		AccountBean temp2 = new AccountBean();
		
		try {
			RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
			
			roomBeans = roomDao.getRoomFilteredByAvailableSeats(roomBean);
			
			if(!roomBeans.isEmpty()) {
				
				RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
				
				AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
				
				for(RoomBean rBean: roomBeans) {
					room = new Room(rBean);
					temp1.setSpecification(rBean.getSpecification());
					rSpecBean = roomSpecDao.getRoomSpec(temp1);
					room.setSpecification(new RoomSpec(rSpecBean));
					temp2.setCf(rBean.getOwner()); 
					owner = accountDao.getAccount(temp2);
					room.setOwner(new Account(owner));
					rooms.add(room);
				}
			return rooms;
			
			}else {
				throw new NotFoundException("Rooms with " + roomBean.getNumAvailableSeats() + " or more available seats");
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}

//	takes in input the cf of the user and return a list of all his rooms
	public List<Room> getMyRooms(AccountBean accountBean) throws DatabaseException, NotFoundException {
		
		List<RoomBean> roomBeans;
		List<Room> rooms = new ArrayList<>();
		RoomSpecBean rSpecBean;
		RoomBean temp1 = new RoomBean();
		Room room;
		
		try {
			RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
			
			roomBeans = roomDao.getAllAccountRooms(accountBean);
			
			if(!roomBeans.isEmpty()) {
				
				RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
				
				for(RoomBean rBean: roomBeans) {
					room = new Room(rBean);
					temp1.setSpecification(rBean.getSpecification());
					rSpecBean = roomSpecDao.getRoomSpec(temp1);
					room.setSpecification(new RoomSpec(rSpecBean));
					rooms.add(room);
				}
			return rooms;
			
			}else {
				throw new NotFoundException("Rooms");
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}
	
//	return a list of all available rooms on the db
	public List<Room> searchRooms() throws DatabaseException, NotFoundException {
		
		List<RoomSpecBean> roomSpecBeans;
		List<Room> rooms = new ArrayList<>();
		RoomBean roomBean;
		Room room;
		AccountBean owner;
		AccountBean temp1 = new AccountBean();
		
		try {
			RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
			
			roomSpecBeans = roomSpecDao.getOrderedRoomsSpec();
			
			RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
			
			AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
			
			if(!roomSpecBeans.isEmpty()) {
				
				for(RoomSpecBean rSpecBean: roomSpecBeans) {
					
					roomBean = roomDao.getRoomFromSpec(rSpecBean);
					room = new Room(roomBean);
					room.setSpecification(new RoomSpec(rSpecBean));
					temp1.setCf(roomBean.getOwner()); 
					owner = accountDao.getAccount(temp1);
					room.setOwner(new Account(owner));
					rooms.add(room);
				}
				
			return rooms;
			
			}else {
				throw new NotFoundException("Rooms");
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
		
	}

//	takes in input the id of the room and and the cf of the user
	public boolean bookRoom(RoomBean roomBean, AccountBean accountBean) throws DatabaseException, RoomException, AccountException, ReservationException  {
		
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
					r2Bean.setNumPartecipants(rBean.getNumPartecipants());
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

//	takes in input the id of the room that wants to delete
	public boolean deleteRoom(RoomBean roomBean) throws DatabaseException {
		
		RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
		RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
		RoomBean rBean;
		RoomSpecBean rsBean;
		
		try{
			rBean = roomDao.getRoom(roomBean);
			rsBean = roomSpecDao.getRoomSpec(rBean);
			
			return roomSpecDao.removeRoomSpec(rsBean) != 0;
		
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
		
	}
}
