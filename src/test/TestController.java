package test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import logic.exception.*;
import logic.model.Account;
import logic.model.Person;
import logic.model.Reservation;
import logic.model.Review;
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
		person.setUsername("s");
		List<Room> rooms = r.searchRoomByHost(person);
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
		b.setCap("00133");
		List<Room> rooms = r.searchRoomByCap(b);
		System.out.println(rooms.get(0).getName());
		System.out.println(rooms.get(1).getName());
	}
	
	@Test
	public void testSearchByDate() throws DatabaseException, NotFoundException {
		RoomController r = RoomController.getInstance();
		RoomSpecBean b = new RoomSpecBean();
		b.setDate("2021:02:03");
		List<Room> rooms = r.searchRoomByDate(b);
		System.out.println(rooms.get(0).getName());
		System.out.println(rooms.get(0).getAddress());
		System.out.println(rooms.get(0).getNumPartecipants());
	
	}
	
	@Test
	public void testSearchByAvailableSeats() throws DatabaseException, NotFoundException {
		RoomController r = RoomController.getInstance();
		RoomBean b = new RoomBean();
		b.setNumAvailableSeats(3);
		try {
		List<Room> rooms = r.searchRoomByAvailableSeats(b);
		
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
		temp.setCf("1234567890123456");
		List<Room> rooms = r.getMyRooms(temp);
		System.out.println(rooms.get(0).getName());
		
	}
	
	@Test
	public void testGetRooms() throws DatabaseException, NotFoundException {
		RoomController r = RoomController.getInstance();
		List<Room> rooms = r.searchRooms();
		System.out.println(rooms.get(0).getName());
		System.out.println(rooms.get(1).getName());

	}
	
	@Test
	public void testMakeReservation() throws DatabaseException, RoomException, AccountException, ReservationException {
		ReservationController r = ReservationController.getInstance();
		AccountBean temp = new AccountBean();
		temp.setCf("qqqqqqqqqqqqqqqq");
		RoomBean temp2 = new RoomBean();
		temp2.setId(18);
		boolean res = r.makeReservation(temp2, temp);
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
	
	@Test
	public void testGetAccountInfo() throws DatabaseException {
		AccountController c = AccountController.getInstance();
		AccountBean acc = new AccountBean();
		acc.setCf("GSTNCL99C23H501K");
		Person p = c.getAccountInfo(acc);
		System.out.println("Person: "+p.getId()+p.getUsername()+" "+p.getStudyGrade()+" "+p.getSchool()+" "+p.getAccount()+" "+" "+p.getHostRating()+" "+p.getGuestRating());
		System.out.println("Account: "+ p.getAccount().getCf()+" "+p.getAccount().getName()+" "+p.getAccount().getSurname()+" "+p.getAccount().getEmail()+" "+p.getAccount().getPassword()+" "+p.getAccount().getDateBirth()+" "+p.getAccount().getNumberToken());
	}
	
	@Test
	public void testGetOtherAccountInfo() throws DatabaseException {
		AccountController c = AccountController.getInstance();
		PersonBean person = new PersonBean();
		person.setUsername("fff");
		Person p = c.getOtherAccountInfo(person);
		System.out.println("Person: "+p.getId()+" "+p.getUsername()+" "+p.getStudyGrade()+" "+p.getSchool()+" "+p.getAccount()+" "+" "+p.getHostRating()+" "+p.getGuestRating());
		System.out.println("Account: "+ p.getAccount().getCf()+" "+p.getAccount().getName()+" "+p.getAccount().getSurname()+" "+p.getAccount().getEmail()+" "+p.getAccount().getPassword()+" "+p.getAccount().getDateBirth()+" "+p.getAccount().getNumberToken());
	}
	
	@Test
	public void testCreateGroup() throws DatabaseException {
		GroupController g = GroupController.getInstance();
		GroupBean gr = new GroupBean();
		gr.setAdmin("ffffffffffffffff");
		gr.setName("bel gruppo");
		g.createGroup(gr);
	}
	
	@Test
	public void testAddGroupPartecipant() throws DatabaseException, AccountException {
		GroupController g = GroupController.getInstance();
		GroupBean gr = new GroupBean();
		
		gr.setAdmin("ffffffffffffffff");
		gr.setName("bel gruppo");
		
		PersonBean person = new PersonBean();
		person.setUsername("q");
		g.addGroupPartecipant(gr, person);
	}
	
	@Test
	public void testGetReceivedReview() throws DatabaseException, ReviewException {
		ReviewController r = ReviewController.getInstance();
		AccountBean accountBean = new AccountBean();
		accountBean.setCf("qqqqqqqqqqqqqqqq");
		List<Review> list = r.getReceivedReviews(accountBean);
		System.out.println("Review: "+list.get(0).getId()+" "+list.get(0).getTitle()+" "+list.get(0).getReviewer()+" "+list.get(0).getReviewed()+" "+list.get(0).getRating()+" "+list.get(0).getDescritpion()+" "+list.get(0).getTag());
		System.out.println("Reviewer: "+list.get(0).getReviewer().getCf()+" "+list.get(0).getReviewer().getName()+" "+list.get(0).getReviewer().getSurname()+" "+list.get(0).getReviewer().getEmail()+" "+list.get(0).getReviewer().getPassword()+" "+list.get(0).getReviewer().getDateBirth()+" "+list.get(0).getReviewer().getNumberToken());
		System.out.println("Reviewed: "+list.get(0).getReviewed().getId()+" "+list.get(0).getReviewed().getUsername()+" "+list.get(0).getReviewed().getStudyGrade()+" "+list.get(0).getReviewed().getStudyGrade()+" "+list.get(0).getReviewed().getSchool()+" "+list.get(0).getReviewed().getAccount()+" "+list.get(0).getReviewed().getHostRating()+" "+list.get(0).getReviewed().getGuestRating());
		System.out.println("Reviewed Account: "+list.get(0).getReviewed().getAccount().getCf()+" "+list.get(0).getReviewed().getAccount().getName()+" "+list.get(0).getReviewed().getAccount().getSurname()+" "+list.get(0).getReviewed().getAccount().getEmail()+" "+list.get(0).getReviewed().getAccount().getPassword()+" "+list.get(0).getReviewed().getAccount().getDateBirth()+" "+list.get(0).getReviewed().getAccount().getNumberToken());
	}
	
	@Test
	public void testGetDoneReview() throws DatabaseException, ReviewException {
		ReviewController r = ReviewController.getInstance();
		AccountBean accountBean = new AccountBean();
		accountBean.setCf("qqqqqqqqqqqqqqqq");
		List<Review> list = r.getDoneReviews(accountBean);
		System.out.println("Review: "+list.get(0).getId()+" "+list.get(0).getTitle()+" "+list.get(0).getReviewer()+" "+list.get(0).getReviewed()+" "+list.get(0).getRating()+" "+list.get(0).getDescritpion()+" "+list.get(0).getTag());
		System.out.println("Reviewer: "+list.get(0).getReviewer().getCf()+" "+list.get(0).getReviewer().getName()+" "+list.get(0).getReviewer().getSurname()+" "+list.get(0).getReviewer().getEmail()+" "+list.get(0).getReviewer().getPassword()+" "+list.get(0).getReviewer().getDateBirth()+" "+list.get(0).getReviewer().getNumberToken());
		System.out.println("Reviewed: "+list.get(0).getReviewed().getId()+" "+list.get(0).getReviewed().getUsername()+" "+list.get(0).getReviewed().getStudyGrade()+" "+list.get(0).getReviewed().getSchool()+" "+list.get(0).getReviewed().getAccount()+" "+list.get(0).getReviewed().getHostRating()+" "+list.get(0).getReviewed().getGuestRating());
		System.out.println("Reviewed Account: "+list.get(0).getReviewed().getAccount().getCf()+" "+list.get(0).getReviewed().getAccount().getName()+" "+list.get(0).getReviewed().getAccount().getSurname()+" "+list.get(0).getReviewed().getAccount().getEmail()+" "+list.get(0).getReviewed().getAccount().getPassword()+" "+list.get(0).getReviewed().getAccount().getDateBirth()+" "+list.get(0).getReviewed().getAccount().getNumberToken());
	} 
	
	@Test
	public void testCalculateHostRating() throws DatabaseException, ReviewException, AccountException {
		ReviewController r = ReviewController.getInstance();
		PersonBean pers = new PersonBean();
		pers.setUsername("q");
		System.out.println(r.calculateHostRating(pers));
	}
	
	@Test
	public void testCalculateGuestRating() throws DatabaseException, ReviewException, AccountException {
		ReviewController r = ReviewController.getInstance();
		PersonBean pers = new PersonBean();
		pers.setUsername("q");
		System.out.println(r.calculateGuestRating(pers));
	}
	
	@Test
	public void testMakeReview() throws DatabaseException, ReviewException, AccountException {
		ReviewController r = ReviewController.getInstance();
		ReviewBean revBean = new ReviewBean();
		revBean.setTitle("male");
		revBean.setReviewer("GSTNCL99C23H501K");
		revBean.setRating(5);
		revBean.setTag("GUEST");
		revBean.setDescription("male male");
		PersonBean persBean = new PersonBean();
		persBean.setUsername("nnn");
		
		r.makeReview(revBean, persBean);	
	}
	
	@Test
	public void testGetAllRoomPartecipants() throws DatabaseException, ReservationException {
		ReservationController r = ReservationController.getInstance();
		RoomBean room = new RoomBean();
		room.setId(18);
		List<Person> list = r.getAllRoomPartecipants(room);
	}
	
	@Test
	public void testDeleteReservation() throws DatabaseException {
		ReservationController r = ReservationController.getInstance();
		ReservationBean resBean = new ReservationBean();
		resBean.setId(25);
		r.deleteReservation(resBean);
	}
}
