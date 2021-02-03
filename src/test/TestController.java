package test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import logic.exception.*;
import logic.model.Account;
import logic.model.Person;
import logic.model.Reservation;
import logic.model.Room;
import logic.controller.*;
import logic.bean.*;

public class TestController {
	
	@Test
	public void testPostRoom() throws DatabaseException {
		
		RoomController r = RoomController.getInstance();
		RoomBean roomBean = new RoomBean();
		RoomSpecBean roomSpecBean = new RoomSpecBean();
		roomBean.setName("bello");
		roomBean.setAddress("via");
		roomBean.setNumPartecipants(4);
		roomBean.setNumAvailableSeats(4);
		roomBean.setOwner("1234567890123456");
		
		roomSpecBean.setDescription("bel postoo");
		roomSpecBean.setDate("2021-01-30");
		roomSpecBean.setStartTime("09:00");
		roomSpecBean.setEndTime("11:00");
		roomSpecBean.setCap("00133");
		
		r.postRoom(roomBean, roomSpecBean);
	}
	
	@Test
	public void testSearchRoomByHost() throws DatabaseException, NotFoundException {
		RoomController r = RoomController.getInstance();
		PersonBean person = new PersonBean();
		person.setUsername("h");
		List<Room> rooms = r.searchByHost(person);
		System.out.println(rooms.get(0).getName());
		System.out.println(rooms.get(0).getAddress());
		System.out.println(rooms.get(0).getNumPartecipants());
		System.out.println(rooms.get(0).getNumAvailableSeats());
		System.out.println(rooms.get(0).getOwner().getCf() + "  " + rooms.get(0).getOwner().getName() + "  " + rooms.get(0).getOwner().getSurname() + "  " + rooms.get(0).getOwner().getEmail() + "  " + rooms.get(0).getOwner().getPassword() + "  " + rooms.get(0).getOwner().getDateBirth() + "  " + rooms.get(0).getOwner().getNumberToken() + "  ");
		System.out.println(rooms.get(0).getSpecification().getId() + "  " + rooms.get(0).getSpecification().getDescription() + "  " + rooms.get(0).getSpecification().getDate() + "  " + rooms.get(0).getSpecification().getStartTime() + "  " + rooms.get(0).getSpecification().getEndTime() + "  " + rooms.get(0).getSpecification().getCap() );
	}
	
	@Test
	public void testSearchByCap() throws DatabaseException, NotFoundException {
		RoomController r = RoomController.getInstance();
		RoomSpecBean b = new RoomSpecBean();
		b.setCap("00130");
		List<Room> rooms = r.searchByCap(b);
	}
	
	@Test
	public void testSearchByDate() throws DatabaseException, NotFoundException {
		RoomController r = RoomController.getInstance();
		RoomSpecBean b = new RoomSpecBean();
		b.setDate("2019:01:30");
		List<Room> rooms = r.searchByDate(b);
		System.out.println(rooms.get(0).getName());
		System.out.println(rooms.get(0).getAddress());
		System.out.println(rooms.get(0).getNumPartecipants());
	
	}
	
	@Test
	public void testSearchByAvailableSeats() throws DatabaseException, NotFoundException {
		RoomController r = RoomController.getInstance();
		RoomBean b = new RoomBean();
		b.setNumAvailableSeats(5);
		try {
		List<Room> rooms = r.searchByAvailableSeats(b);
		
		  System.out.println(rooms.get(0).getName());
		  System.out.println(rooms.get(0).getAddress());
		  System.out.println(rooms.get(0).getNumPartecipants());
		  System.out.println(rooms.get(0).getNumAvailableSeats());
		  System.out.println(rooms.get(0).getOwner().getCf() + "  " +
		  rooms.get(0).getOwner().getName() + "  " +
		  rooms.get(0).getOwner().getSurname() + "  " +
		  rooms.get(0).getOwner().getEmail() + "  " +
		  rooms.get(0).getOwner().getPassword() + "  " +
		  rooms.get(0).getOwner().getDateBirth() + "  " +
		  rooms.get(0).getOwner().getNumberToken() + "  ");
		  
		  System.out.println(rooms.get(0).getSpecification().getId() + "  " +
		  rooms.get(0).getSpecification().getDescription() + "  " +
		  rooms.get(0).getSpecification().getDate() + "  " +
		  rooms.get(0).getSpecification().getStartTime() + "  " +
		  rooms.get(0).getSpecification().getEndTime() + "  " +
		  rooms.get(0).getSpecification().getCap() );
		 
		}catch(NotFoundException ne) {
			ne.printStackTrace();
		}
	}
	
	@Test
	public void testLogin() throws DatabaseException, NotFoundException {
		LoginController c = LoginController.getInstance();
		AccountBean temp = new AccountBean();
		temp.setEmail("n@gmail.com");
		temp.setPassword("p");
		Person pers = c.login(temp);
		System.out.println(pers.getId() + "  " + pers.getUsername()+ "  " + pers.getStudyGrade() + "  " + pers.getSchool() + "  " + pers.getHostRating() + "  " + pers.getGuestRating());
		System.out.println(pers.getAccount().getCf() + "  " + pers.getAccount().getName() + "  " + pers.getAccount().getSurname() + "  " + pers.getAccount().getEmail() + "  " + pers.getAccount().getPassword() + "  " + pers.getAccount().getDateBirth() + "  " + pers.getAccount().getNumberToken() + "  ");
	}
	@Test
	public void testGetMyRooms() throws DatabaseException, NotFoundException {
		RoomController r = RoomController.getInstance();
		AccountBean temp = new AccountBean();
		temp.setCf("qqqqqqqqqqqqqqqq");
		List<Room> rooms = r.getMyRooms(temp);
		
	}
	
	@Test
	public void testGetRooms() throws DatabaseException, NotFoundException {
		RoomController r = RoomController.getInstance();
		List<Room> rooms = r.searchRooms();

	}
	
	@Test
	public void testBookRoom() throws DatabaseException, RoomException, AccountException, ReservationException {
		RoomController r = RoomController.getInstance();
		AccountBean temp = new AccountBean();
		temp.setCf("qqqqqqqqqqqqqqqq");
		RoomBean temp2 = new RoomBean();
		temp2.setId(17);
		boolean res = r.bookRoom(temp2, temp);
		assertEquals(true, res);
		
	}
	
	@Test
	public void testDeleteRoom() throws DatabaseException {
		RoomController r = RoomController.getInstance();
		RoomBean temp = new RoomBean();
		temp.setId(17);
		boolean res = r.deleteRoom(temp);
		assertEquals(true, res);
	}
	
	@Test
	public void testBookRoomGroup() throws DatabaseException, RoomException, AccountException, ReservationException {
		GroupController r = GroupController.getInstance();
		RoomBean temp = new RoomBean();
		temp.setId(18);
		GroupBean temp2 = new GroupBean();
		temp2.setName("bel gruppo");
		temp2.setAdmin("qqqqqqqqqqqqqqqq");
		r.bookRoomGroup(temp2, temp);
	}
	
	@Test
	public void testLeaveGroup() throws DatabaseException, AccountException {
		GroupController r = GroupController.getInstance();
		GroupBean temp = new GroupBean();
		temp.setAdmin("qqqqqqqqqqqqqqqq");
		temp.setName("bel gruppo");
		temp.setPartecipant(13);
		temp.setNumPartecipants(3);
		r.leaveGroup(temp);
	}
	
	@Test
	public void testDeleteGroup() throws DatabaseException, AccountException {
		GroupController r = GroupController.getInstance();
		GroupBean temp = new GroupBean();
		temp.setAdmin("qqqqqqqqqqqqqqqq");
		temp.setName("bel gruppo");
		r.deleteGroup(temp);
	}
	
	@Test
	public void testGetMyFutureReservations() throws DatabaseException, ParseException {
		ReservationController r = ReservationController.getInstance();
		AccountBean acc = new AccountBean();
		acc.setCf("GSTNCL99C23H501K");
		List<Reservation> list = r.getMyFutureReservations(acc);
		System.out.println("Reservation: "+list.get(0).getId()+" "+list.get(0).getReservingUser()+" "+list.get(0).getLinkedRoom()+" "+list.get(0).getRoomOwner()+" "+list.get(0).getDate()+" "+list.get(0).getStartTime()+" "+list.get(0).getEndTime());
		System.out.println("Reserving user: "+list.get(0).getReservingUser().getCf()+" "+list.get(0).getReservingUser().getName()+" "+list.get(0).getReservingUser().getSurname()+" "+list.get(0).getReservingUser().getEmail()+" "+list.get(0).getReservingUser().getPassword()+" "+list.get(0).getReservingUser().getDateBirth()+" "+list.get(0).getReservingUser().getNumberToken());
		System.out.println("Room: "+list.get(0).getLinkedRoom().getId()+" "+list.get(0).getLinkedRoom().getName()+" "+list.get(0).getLinkedRoom().getAddress()+" "+list.get(0).getLinkedRoom().getNumPartecipants()+" "+list.get(0).getLinkedRoom().getNumAvailableSeats()+" "+list.get(0).getLinkedRoom().getOwner()+" "+list.get(0).getLinkedRoom().getSpecification());
		System.out.println("Room Owner Account: "+list.get(0).getLinkedRoom().getOwner().getCf()+" "+list.get(0).getLinkedRoom().getOwner().getName()+" "+list.get(0).getLinkedRoom().getOwner().getSurname()+" "+list.get(0).getLinkedRoom().getOwner().getEmail()+" "+list.get(0).getLinkedRoom().getOwner().getPassword()+" "+list.get(0).getLinkedRoom().getOwner().getDateBirth()+" "+list.get(0).getLinkedRoom().getOwner().getNumberToken());
		System.out.println("Room Specification: "+list.get(0).getLinkedRoom().getSpecification().getId()+" "+list.get(0).getLinkedRoom().getSpecification().getDescription()+" "+list.get(0).getLinkedRoom().getSpecification().getDate()+" "+list.get(0).getLinkedRoom().getSpecification().getStartTime()+" "+list.get(0).getLinkedRoom().getSpecification().getEndTime()+" "+list.get(0).getLinkedRoom().getSpecification().getCap());
		System.out.println("Room Owner Person: " +list.get(0).getRoomOwner().getId()+" "+list.get(0).getRoomOwner().getUsername()+" "+list.get(0).getRoomOwner().getStudyGrade()+" "+list.get(0).getRoomOwner().getSchool()+" "+list.get(0).getRoomOwner().getAccount()+" "+list.get(0).getRoomOwner().getHostRating()+" "+ list.get(0).getRoomOwner().getGuestRating());
		System.out.println("Room Owner Person Account: "+list.get(0).getRoomOwner().getAccount().getCf()+" "+list.get(0).getRoomOwner().getAccount().getName()+" "+list.get(0).getRoomOwner().getAccount().getSurname()+" "+list.get(0).getRoomOwner().getAccount().getEmail()+" "+list.get(0).getRoomOwner().getAccount().getPassword()+" "+list.get(0).getRoomOwner().getAccount().getDateBirth()+" "+list.get(0).getRoomOwner().getAccount().getNumberToken());
	}
	
	@Test
	public void testGetMyPastReservations() throws DatabaseException, ParseException {
		ReservationController r = ReservationController.getInstance();
		AccountBean acc = new AccountBean();
		acc.setCf("GSTNCL99C23H501K");
		List<Reservation> list = r.getMyPastReservations(acc);
		System.out.println("Reservation: "+list.get(0).getId()+" "+list.get(0).getReservingUser()+" "+list.get(0).getLinkedRoom()+" "+list.get(0).getRoomOwner()+" "+list.get(0).getDate()+" "+list.get(0).getStartTime()+" "+list.get(0).getEndTime());
		System.out.println("Reserving user: "+list.get(0).getReservingUser().getCf()+" "+list.get(0).getReservingUser().getName()+" "+list.get(0).getReservingUser().getSurname()+" "+list.get(0).getReservingUser().getEmail()+" "+list.get(0).getReservingUser().getPassword()+" "+list.get(0).getReservingUser().getDateBirth()+" "+list.get(0).getReservingUser().getNumberToken());
		System.out.println("Room: "+list.get(0).getLinkedRoom().getId()+" "+list.get(0).getLinkedRoom().getName()+" "+list.get(0).getLinkedRoom().getAddress()+" "+list.get(0).getLinkedRoom().getNumPartecipants()+" "+list.get(0).getLinkedRoom().getNumAvailableSeats()+" "+list.get(0).getLinkedRoom().getOwner()+" "+list.get(0).getLinkedRoom().getSpecification());
		System.out.println("Room Owner Account: "+list.get(0).getLinkedRoom().getOwner().getCf()+" "+list.get(0).getLinkedRoom().getOwner().getName()+" "+list.get(0).getLinkedRoom().getOwner().getSurname()+" "+list.get(0).getLinkedRoom().getOwner().getEmail()+" "+list.get(0).getLinkedRoom().getOwner().getPassword()+" "+list.get(0).getLinkedRoom().getOwner().getDateBirth()+" "+list.get(0).getLinkedRoom().getOwner().getNumberToken());
		System.out.println("Room Specification: "+list.get(0).getLinkedRoom().getSpecification().getId()+" "+list.get(0).getLinkedRoom().getSpecification().getDescription()+" "+list.get(0).getLinkedRoom().getSpecification().getDate()+" "+list.get(0).getLinkedRoom().getSpecification().getStartTime()+" "+list.get(0).getLinkedRoom().getSpecification().getEndTime()+" "+list.get(0).getLinkedRoom().getSpecification().getCap());
		System.out.println("Room Owner Person: " +list.get(0).getRoomOwner().getId()+" "+list.get(0).getRoomOwner().getUsername()+" "+list.get(0).getRoomOwner().getStudyGrade()+" "+list.get(0).getRoomOwner().getSchool()+" "+list.get(0).getRoomOwner().getAccount()+" "+list.get(0).getRoomOwner().getHostRating()+" "+ list.get(0).getRoomOwner().getGuestRating());
		System.out.println("Room Owner Person Account: "+list.get(0).getRoomOwner().getAccount().getCf()+" "+list.get(0).getRoomOwner().getAccount().getName()+" "+list.get(0).getRoomOwner().getAccount().getSurname()+" "+list.get(0).getRoomOwner().getAccount().getEmail()+" "+list.get(0).getRoomOwner().getAccount().getPassword()+" "+list.get(0).getRoomOwner().getAccount().getDateBirth()+" "+list.get(0).getRoomOwner().getAccount().getNumberToken());
	}
}
