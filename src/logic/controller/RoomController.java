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
	public boolean postRoom(RoomBean roomBean, RoomSpecBean roomSpecBean) throws DatabaseException, RoomException {
		
		AccountBean accBean = new AccountBean();
		AccountBean tempAccBean = new AccountBean();
		RoomSpecBean rSBean = new RoomSpecBean();
		
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		try{
			RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
			
			int spec = roomSpecDao.createRoomSpec(roomSpecBean);
			
			roomBean.setSpecification(spec);
			
			RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
			
//			at the end the number of available seats is the same of the number of participants			
			roomBean.setNumAvailableSeats(roomBean.getNumParticipants());
			
			int id = roomDao.createRoom(roomBean);
			
			if(id != 0) {
				tempAccBean.setCf(roomBean.getOwner());
				
				accBean = accountDao.getAccount(tempAccBean) ;
				accBean.setNumberToken(accBean.getNumberToken()+2);
				return (accountDao.updateNumberToken(accBean) != 0);
			
			}else {
				rSBean.setId(spec);
				roomSpecDao.removeRoomSpec(rSBean);
				
				throw new RoomException("cannot be created");
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}

//  takes in input the username of the host and return a list of user's rooms
	public List<Room> searchRoomByHost(PersonBean personBean) throws DatabaseException, NotFoundException {
		
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		List<RoomBean> roomBeans;
		List<Room> rooms = new ArrayList<>();
		
		PersonBean person;
		Room room;
		RoomSpec roomSpec;
		
		Account owner;
		Person ownerPerson;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now();
		
		try {
			
			person = personDao.getPersonByUsername(personBean);
			
			if(person != null) {
				
				String cf = person.getAccount();
				AccountBean temp1 = new AccountBean();
				temp1.setCf(cf);
				
				RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
				
				roomBeans = roomDao.getAllAccountRooms(temp1);
				RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
				AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
				
				if(!roomBeans.isEmpty()) {
					
					for(RoomBean roomB: roomBeans) {
						
						roomSpec = new RoomSpec(roomSpecDao.getRoomSpec(roomB));
						
						String dateTemp = roomSpec.getDate() + " " + roomSpec.getStartTime();
						LocalDateTime resDateTime = LocalDateTime.parse(dateTemp, formatter);
						
						if(currentDate.compareTo(resDateTime) < 0) {
							
							room = new Room(roomB);
							room.setSpecification(roomSpec);
							owner = new Account(accountDao.getAccount(temp1));
							
							ownerPerson = new Person(personDao.getPersonFromAccount(temp1));
							owner.setPerson(ownerPerson);
							
							room.setOwner(owner);
							room.setParticipants(getAllRoomParticipants(roomB));
							rooms.add(room);
						}
					}
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
	public List<Room> searchRoomByCap(RoomSpecBean roomSpecBean) throws DatabaseException, NotFoundException {
		
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
		RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		List<RoomSpecBean> roomSpecBeans;
		List<Room> rooms = new ArrayList<>();
		RoomBean roomBean;
		Room room;
		AccountBean ownerBean;
		AccountBean temp1 = new AccountBean();
		Person ownerPerson;
		Account ownerAccount;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now();
		
		try {
			
			roomSpecBeans = roomSpecDao.getRoomsSpecByCap(roomSpecBean);
			
			if(!roomSpecBeans.isEmpty()) {
				
				for(RoomSpecBean rSpecBean: roomSpecBeans) {
					
					String dateTemp = rSpecBean.getDate() + " " + rSpecBean.getStartTime();
					LocalDateTime resDateTime = LocalDateTime.parse(dateTemp, formatter);
					
					if(currentDate.compareTo(resDateTime) < 0) {
						
						roomBean = roomDao.getRoomFromSpec(rSpecBean);
						
						room = new Room(roomBean);
						room.setSpecification(new RoomSpec(rSpecBean));
						temp1.setCf(roomBean.getOwner()); 
						ownerBean = accountDao.getAccount(temp1);
						
						ownerAccount = new Account(ownerBean);
						
						ownerPerson = new Person(personDao.getPersonFromAccount(temp1));
						ownerAccount.setPerson(ownerPerson);
						
					
						room.setOwner(ownerAccount);
						room.setParticipants(getAllRoomParticipants(roomBean));
						rooms.add(room);
					}	
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
	public List<Room> searchRoomByDate(RoomSpecBean roomSpecBean) throws DatabaseException, NotFoundException {
		
		RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		List<RoomSpecBean> roomSpecBeans;
		List<Room> rooms = new ArrayList<>();
		RoomBean roomBean;
		Room room;
		AccountBean ownerBean;
		AccountBean temp1 = new AccountBean();
		
		Person ownerPerson;
		Account ownerAccount;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now();
		
		try {
			
			roomSpecBeans = roomSpecDao.getRoomsSpecByDate(roomSpecBean);
			
			if(!roomSpecBeans.isEmpty()) {
				
				for(RoomSpecBean rSpecBean: roomSpecBeans) {
					
					String dateTemp = rSpecBean.getDate() + " " + rSpecBean.getStartTime();
					LocalDateTime resDateTime = LocalDateTime.parse(dateTemp, formatter);
					
					if(currentDate.compareTo(resDateTime) < 0) {
						
						roomBean = roomDao.getRoomFromSpec(rSpecBean);
						room = new Room(roomBean);
						room.setSpecification(new RoomSpec(rSpecBean));
						temp1.setCf(roomBean.getOwner()); 
						ownerBean = accountDao.getAccount(temp1);
						
						ownerAccount = new Account(ownerBean);
						
						ownerPerson = new Person(personDao.getPersonFromAccount(temp1));
						ownerAccount.setPerson(ownerPerson);
						
						room.setOwner(ownerAccount);
						room.setParticipants(getAllRoomParticipants(roomBean));
						rooms.add(room);
					}
					
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
	public List<Room> searchRoomByAvailableSeats(RoomBean roomBean) throws DatabaseException, NotFoundException {
		
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		List<RoomBean> roomBeans;
		List<Room> rooms = new ArrayList<>();
		RoomSpecBean rSpecBean;
		RoomBean temp1 = new RoomBean();
		Room room;
		AccountBean owner;
		AccountBean temp2 = new AccountBean();
		Person ownerPerson;
		Account ownerAccount;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now();
		
		try {
			RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
			
			roomBeans = roomDao.getRoomFilteredByAvailableSeats(roomBean);
			
			if(!roomBeans.isEmpty()) {
				
				RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
				
				AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
				
				for(RoomBean rBean: roomBeans) {
					
					temp1.setSpecification(rBean.getSpecification());
					rSpecBean = roomSpecDao.getRoomSpec(temp1); 
					
					String dateTemp = rSpecBean.getDate() + " " + rSpecBean.getStartTime();
					LocalDateTime resDateTime = LocalDateTime.parse(dateTemp, formatter);
					
					if(currentDate.compareTo(resDateTime) < 0) {
						
						room = new Room(rBean);
						room.setSpecification(new RoomSpec(rSpecBean));
						temp2.setCf(rBean.getOwner()); 
						owner = accountDao.getAccount(temp2);
						
						ownerAccount = new Account(owner);
						
						ownerPerson = new Person(personDao.getPersonFromAccount(temp2));
						ownerAccount.setPerson(ownerPerson);
						
						room.setOwner(ownerAccount);
						room.setParticipants(getAllRoomParticipants(rBean));
						rooms.add(room);
					}
				
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
	public List<Room> getMyRooms(AccountBean accountBean) throws DatabaseException {
		
		List<RoomBean> roomBeans;
		List<Room> rooms = new ArrayList<>();
		RoomSpecBean rSpecBean;
		RoomBean temp1 = new RoomBean();
		Room room;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now();
		
		try {
			RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
			
			roomBeans = roomDao.getAllAccountRooms(accountBean);
				
			RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
			
			if(!roomBeans.isEmpty()) {
				
				for(RoomBean rBean: roomBeans) {
					
					temp1.setSpecification(rBean.getSpecification());
					rSpecBean = roomSpecDao.getRoomSpec(temp1);
					
					String dateTemp = rSpecBean.getDate() + " " + rSpecBean.getStartTime();
					LocalDateTime resDateTime = LocalDateTime.parse(dateTemp, formatter);
					
					if(currentDate.compareTo(resDateTime) < 0) {
						
						room = new Room(rBean);
						room.setSpecification(new RoomSpec(rSpecBean));
						room.setParticipants(getAllRoomParticipants(rBean));
						rooms.add(room);
					}
				}
			}
				
			return rooms;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}
	
//	return a list of all available rooms on the db
	public List<Room> searchRooms() throws DatabaseException, NotFoundException {
		
		List<RoomSpecBean> roomSpecBeans = new ArrayList<>();
		List<Room> rooms = new ArrayList<>();
		
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		RoomBean roomBean;
		Room room;
		AccountBean ownerBean;
		Account owner;
		PersonBean persBean;
		AccountBean temp1 = new AccountBean();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime currentDate = LocalDateTime.now();
		
		try {
			RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
			
			roomSpecBeans = roomSpecDao.getOrderedRoomsSpec();
			
			RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
			
			AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
			
			if(!roomSpecBeans.isEmpty()) {
					
				for(RoomSpecBean rSpecBean: roomSpecBeans) {
					
					String dateTemp = rSpecBean.getDate() + " " + rSpecBean.getStartTime();
					LocalDateTime resDateTime = LocalDateTime.parse(dateTemp, formatter);
						
					if(currentDate.compareTo(resDateTime) < 0) {
							
						roomBean = roomDao.getRoomFromSpec(rSpecBean);
						room = new Room(roomBean);
						room.setSpecification(new RoomSpec(rSpecBean));
							
						temp1.setCf(roomBean.getOwner()); 
						ownerBean = accountDao.getAccount(temp1);
						owner = new Account(ownerBean);
							
						persBean = personDao.getPersonFromAccount(ownerBean);
						owner.setPerson(new Person(persBean));
							
						room.setOwner(owner);
						room.setParticipants(getAllRoomParticipants(roomBean));	
						rooms.add(room);
					}
				}
				
				return rooms;
			
			}else {
				throw new NotFoundException("Available rooms not found");
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
