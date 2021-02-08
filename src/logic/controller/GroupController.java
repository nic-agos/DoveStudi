package logic.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;
import logic.model.*;
import logic.model.dao.*;
import logic.exception.*;

public class GroupController {

//	implemented with singleton pattern
	private static GroupController instance = null;
	
	private GroupController() {
		
	}
	
	public static synchronized GroupController getInstance() {
		if(GroupController.instance == null) {
			GroupController.instance = new GroupController();
		}
		return instance;
	}

//	takes in input a group bean with filled in the group's name and the admin's cf
	public boolean createGroup(GroupBean groupBean) throws DatabaseException {
		
		GroupDAOImpl groupDao = GroupDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		GroupBean grBean = new GroupBean();
		PersonBean persBean;
		AccountBean accountBean = new AccountBean();
		
		try {
			grBean.setAdmin(groupBean.getAdmin());
			grBean.setName(groupBean.getName());
			
			accountBean.setCf(groupBean.getAdmin());
			persBean = personDao.getPersonFromAccount(accountBean);
			grBean.setNumParticipants(1);
			grBean.setParticipant(persBean.getId());
			
			return (groupDao.createGroup(grBean) != 0);
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());		
		}
	}
	
//	takes in input group's name and admin and the username of the person that want's to add to the group
	public boolean addGroupParticipant(GroupBean groupBean, PersonBean personBean) throws DatabaseException, AccountException {
		
		GroupDAOImpl groupDao = GroupDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		List<PersonBean> groupParticipants;
		PersonBean persBean;
		PersonBean tempPersBean = new PersonBean();
		GroupBean grBean = new GroupBean();
		GroupBean tempGroupBean = new GroupBean();
		
		try {
			persBean = personDao.getPersonByUsername(personBean);
			
			if(persBean != null) {
				
				groupParticipants = personDao.getGroupParticipants(groupBean);
				
				for(PersonBean perBean : groupParticipants) {
					if(persBean.getId() == perBean.getId()) {
						throw new AccountException("User with username " + personBean.getUsername() + " is already in the group");
					}
				}
				
				tempGroupBean.setName(groupBean.getName());
				tempGroupBean.setAdmin(groupBean.getAdmin());
				
				grBean.setName(groupBean.getName());
				grBean.setAdmin(groupBean.getAdmin());
				grBean.setNumParticipants(groupDao.getAdministeredGroup(tempGroupBean).getNumParticipants()+1);
				
				tempPersBean = personDao.getPersonByUsername(personBean);
				grBean.setParticipant(tempPersBean.getId());
				
				groupDao.addGroupParticipant(grBean);
				return (groupDao.updateNumParticipantsGroup(grBean) != 0);
				
			}else {
				throw new AccountException("User with username " + personBean.getUsername() + " not found");
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());		
		}
		
	}
	 

//  takes in input the name and the admin of the group
	public boolean deleteGroup(GroupBean groupBean) throws DatabaseException {
		
		GroupDAOImpl groupDao = GroupDAOImpl.getInstance();
		
		try {
			return (groupDao.removeGroup(groupBean) != 0);
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());		
		}
	}
	
//	takes in input the name and the admin of the group and the id of the person that want to leave the group	
	public boolean leaveGroup(GroupBean groupBean) throws DatabaseException, AccountException {
			
		GroupDAOImpl groupDao = GroupDAOImpl.getInstance();
			
		GroupBean gBean = new GroupBean(); 
		GroupBean tempGroupBean;
			
		try {
			if(groupDao.leaveGroup(groupBean) != 0) {
				gBean.setName(groupBean.getName());
				gBean.setAdmin(groupBean.getAdmin());
				
				tempGroupBean = groupDao.getAdministeredGroup(groupBean);
				
				gBean.setNumParticipants(tempGroupBean.getNumParticipants()-1);
				return (groupDao.updateNumParticipantsGroup(gBean) != 0);
				
			}else {
				throw new AccountException("Unable to leave the group");
			}
				
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
					
		}
	}

// 	takes in input name and admin of the group and the room id
	public void bookRoomGroup(GroupBean groupBean, RoomBean roomBean) throws DatabaseException, RoomException, AccountException, ReservationException {
			 
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		ReservationDAOImpl reservationDao = ReservationDAOImpl.getInstance();
		RoomSpecDAOImpl roomSpecDao = RoomSpecDAOImpl.getInstance();
			
		List<PersonBean> participantsList;
		RoomBean rBean;
		RoomSpecBean rsBean;
		AccountBean aBean = new AccountBean();
		AccountBean a2Bean = new AccountBean();
		AccountBean a3Bean;
		AccountBean a4Bean = new AccountBean();
		ReservationBean resBean = new ReservationBean();
		PersonBean roomOwner;
			
		try {
//			get all group participants
			participantsList = personDao.getGroupParticipants(groupBean);

//			get the room and room spec info
			rBean = roomDao.getRoom(roomBean);
			rsBean = roomSpecDao.getRoomSpec(rBean);
			
//			get the room owner Person to fill the reservation				
			a2Bean.setCf(rBean.getOwner());
			roomOwner = personDao.getPersonFromAccount(a2Bean);
				
//			check if the room has enough available seats for all the groups
			if(participantsList.size() <= rBean.getNumAvailableSeats()) {

//				check if all group members have enough tokens
				for(PersonBean persBean : participantsList) {
					aBean.setCf(persBean.getAccount());
					if(accountDao.getNumberToken(aBean)-1 <= 0) {
						throw new AccountException("One or more accounts do not have enough tokens to reserve the room");
					}
				}
					
//	 			check if none of the participants are already booked for the room				
				for(PersonBean persBean : participantsList) {
					resBean.setReservingUser(persBean.getAccount());
					resBean.setLinkedRoom(roomBean.getId());
					resBean.setRoomOwner(roomOwner.getId());
					if(reservationDao.getReservationId(resBean) != 0) {
						throw new ReservationException("One or more user are already booked for this room");
					}
				}

//				creates the reservation for each member of the group
				for(PersonBean persBean : participantsList) {
					resBean.setReservingUser(persBean.getAccount());
					resBean.setLinkedRoom(rBean.getId());
					resBean.setRoomOwner(roomOwner.getId());
					resBean.setDate(rsBean.getDate());
					resBean.setStartTime(rsBean.getStartTime());
					resBean.setEndTime(rsBean.getEndTime());
						
					a4Bean.setCf(persBean.getAccount());
					a3Bean = accountDao.getAccount(a4Bean);
						
//					update the number of token of each member of group
					a4Bean.setNumberToken(a3Bean.getNumberToken()-1);
					accountDao.updateNumberToken(a4Bean);

					reservationDao.createReservation(resBean);
				}
				rBean.setNumParticipants(rBean.getNumParticipants());
				rBean.setNumAvailableSeats(rBean.getNumAvailableSeats()-participantsList.size());
				roomDao.updateRoom(rBean);
					
			}else {
				throw new RoomException("The room does not have enough available seats for your group");
			}
				 
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
					
		}
	}
	
	public List<Group> getParticipatingGroups(PersonBean personBean) throws DatabaseException {
		
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		GroupDAOImpl groupDao = GroupDAOImpl.getInstance();
		
		List<Group> groupParticipants = new ArrayList<>();
		List<GroupBean> groupBeanParticipants;
		Account admin;
		Person personAdmin;
		AccountBean accBean = new AccountBean();
		AccountBean tempAccBean = new AccountBean();
		Person participant;
		Account accParticipant;
		PersonBean partBean = new PersonBean();
		PersonBean tempPersBean;
		
		Group group;
		
		try {
			
			groupBeanParticipants = groupDao.getAllParticipatingGroups(personBean);
			
			if(!groupBeanParticipants.isEmpty()) {
				
				for(GroupBean g : groupBeanParticipants) {
					
					group = new Group(g);
					accBean.setCf(g.getAdmin());
					
					admin = new Account(accountDao.getAccount(accBean));
					personAdmin = new Person(personDao.getPersonFromAccount(accBean));
					admin.setPerson(personAdmin);
					
					partBean.setId(g.getParticipant());
					
					tempPersBean = personDao.getPerson(partBean);
					participant = new Person(tempPersBean);
					
					tempAccBean.setCf(tempPersBean.getAccount());
					accParticipant = new Account(accountDao.getAccount(tempAccBean));
					participant.setAccount(accParticipant);
					
					group.setAdmin(admin);
					group.setParticipant(participant);
					
					groupParticipants.add(group);
					
				}

			}
			
			return groupParticipants;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
		
	}
	
	public List<Group> getAdministeredGroups(AccountBean accountBean) throws DatabaseException {
		
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		GroupDAOImpl groupDao = GroupDAOImpl.getInstance();
		
		List<Group> groupParticipants = new ArrayList<>();
		List<GroupBean> groupBeanParticipants;
		Account admin;
		Person personAdmin;
		AccountBean accBean = new AccountBean();
		AccountBean tempAccBean = new AccountBean();
		Person participant;
		Account accParticipant;
		PersonBean partBean = new PersonBean();
		PersonBean tempPersBean;
		
		Group group;
		
		try {
			
			groupBeanParticipants = groupDao.getAllAdministeredGroups(accountBean);
			
			if(!groupBeanParticipants.isEmpty()) {
				
				for(GroupBean g : groupBeanParticipants) {
					
					group = new Group(g);
					accBean.setCf(g.getAdmin());
					
					admin = new Account(accountDao.getAccount(accBean));
					personAdmin = new Person(personDao.getPersonFromAccount(accBean));
					admin.setPerson(personAdmin);
					
					partBean.setId(g.getParticipant());
					
					tempPersBean = personDao.getPerson(partBean);
					participant = new Person(tempPersBean);
					
					tempAccBean.setCf(tempPersBean.getAccount());
					accParticipant = new Account(accountDao.getAccount(tempAccBean));
					participant.setAccount(accParticipant);
					
					group.setAdmin(admin);
					group.setParticipant(participant);
					
					groupParticipants.add(group);
					
				}

			}
			
			return groupParticipants;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}

	public List<Person> getGroupParticipants(GroupBean groupBean) throws DatabaseException {
		
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		List<Person> groupParticipants = new ArrayList<>();
		List<PersonBean> groupBeanParticipants;
		
		AccountBean tempAccBean = new AccountBean();
		PersonBean persBean;
		PersonBean tempPersBean = new PersonBean();
		
		Account account;
		Person person;
		
		try {
			groupBeanParticipants = personDao.getGroupParticipants(groupBean);
			
			if(!groupBeanParticipants.isEmpty()) {
				
				for(PersonBean p : groupBeanParticipants) {
					
					tempPersBean.setUsername(p.getUsername());
					persBean = personDao.getPersonByUsername(tempPersBean);
					person = new Person(persBean);
					
					tempAccBean.setCf(persBean.getAccount());
					account = new Account(tempAccBean);
					
					person.setAccount(account);
					
					groupParticipants.add(person);
				}
			}
			
			return groupParticipants;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());		
		}
	}

//	takes in input group's name and admin and get the db row of the group's admin
	public Group getSpecificAdministeredGroup(GroupBean groupBean) throws DatabaseException {
		
		GroupDAOImpl groupDao = GroupDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		Group specificGroup = null;
		GroupBean grBean;
		Account accountAdmin;
		AccountBean accBean = new AccountBean();
		Person personAdmin;
		
		try {
			grBean = groupDao.getAdministeredGroup(groupBean);
			
			if(grBean != null) {
				
				specificGroup = new Group(grBean);
				
				accBean.setCf(grBean.getAdmin());
				accountAdmin = new Account(accountDao.getAccount(accBean));
				personAdmin = new Person(personDao.getPersonFromAccount(accBean));
				
				accountAdmin.setPerson(personAdmin);
				
				specificGroup.setAdmin(accountAdmin);
				
			}
			
			return specificGroup;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());		
		}
	}
}
