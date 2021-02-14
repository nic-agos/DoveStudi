package test.fm;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import logic.bean.AccountBean;
import logic.bean.PersonBean;
import logic.bean.ReservationBean;
import logic.bean.RoomBean;
import logic.bean.RoomSpecBean;
import logic.model.Reservation;
import logic.controller.RegistrationController;
import logic.controller.ReservationController;
import logic.controller.RoomController;
import logic.exception.AccountException;
import logic.exception.DatabaseException;
import logic.exception.ReservationException;
import logic.exception.RoomException;
import logic.model.dao.AccountDAOImpl;
import logic.model.dao.PersonDAOImpl;
import logic.model.dao.ReservationDAOImpl;
import logic.model.dao.RoomDAOImpl;

import java.util.ArrayList;
import java.util.List;

public class TestReservationController {
	
	static int resId;
	static int persId;

	@BeforeClass
	public static void initialize() {
		RegistrationController regContr = RegistrationController.getInstance();
		
		PersonDAOImpl personDAO = PersonDAOImpl.getInstance();
		
		RoomController roomContr = RoomController.getInstance();
		
		AccountBean a1 = new AccountBean("RSSMTN02T18F205M", "Mario", "Rossi", "mario.rossi@gmail.it", "asdfgh", "1997:08:27", 0);
		PersonBean p1 = new PersonBean("mario97", "High School", "AB", "RSSMTN02T18F205M", 0, 0);
		
		AccountBean a2 = new AccountBean("MRUFBL08R47A662B", "Marta", "Verdi", "marta.verdi@gmail.com", "abcdef", "1983:07:24", 0);
		PersonBean p2 = new PersonBean("marta83", "High School", "CD", "MRUFBL08R47A662B", 0, 0);
		
		
		RoomBean rBean = new RoomBean();
		rBean.setName("Beautiful room");
		rBean.setAddress("Via Nomentana");
		rBean.setNumParticipants(3);
		rBean.setOwner("MRUFBL08R47A662B");

		RoomSpecBean rs = new RoomSpecBean();
		rs.setCap("00100");
		rs.setDescription("Quiet and big room for engeenering students with Wi-Fi and parking!");
		rs.setDate("2021-03-03");
		rs.setStartTime("16:00");
		rs.setEndTime("20:00");
		
		try {
			
			regContr.register(a1, p1);
			regContr.register(a2, p2);
			
			persId = (personDAO.getPersonId(p2));
			
			roomContr.postRoom(rBean, rs);
			
		}catch (RoomException e) {
			e.printStackTrace();
		}
		catch (DatabaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//bookRoom
	@Test
	public void testMakeReservation() {
		ReservationController resContr = ReservationController.getInstance();
		
		AccountBean accBean = new AccountBean("RSSMTN02T18F205M", "Mario", "Rossi", "mario.rossi@gmail.it", "asdfgh", "1997:08:27", 0);
		
		ReservationDAOImpl reservationDAO = ReservationDAOImpl.getInstance();
		
		RoomDAOImpl roomDAO = RoomDAOImpl.getInstance();
		RoomBean roomBean = new RoomBean();
		RoomBean roomBean2 = new RoomBean();
		int id;
		boolean res = false;
		try {
			roomBean.setName("Beautiful room");
			id = roomDAO.getRoomId(roomBean);
			roomBean2.setId(id);
			res = resContr.makeReservation(roomBean2, accBean);	
			
			ReservationBean resBean = new ReservationBean();
			resBean.setReservingUser("RSSMTN02T18F205M");
			resBean.setLinkedRoom(id);
			resBean.setRoomOwner(persId);
			resId = reservationDAO.getReservationId(resBean);
			
		}catch(DatabaseException e) {
			e.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(ReservationException re) {
			re.printStackTrace();
		} catch (RoomException e) {
			e.printStackTrace();
		} catch (AccountException e) {
			e.printStackTrace();
		}
		assertEquals(true, res);
		
	}
	
	//future res
	@Test
	public void testGetMyFutureReservations() {
		ReservationController resContr = ReservationController.getInstance();
		AccountBean accBean = new AccountBean("RSSMTN02T18F205M", "Mario", "Rossi", "mario.rossi@gmail.it", "asdfgh", "1997:08:27", 0);
		List<Reservation> listRes = new ArrayList<>();
		int size = 0;
		try {
			listRes = resContr.getMyFutureReservations(accBean);
			size = listRes.size();
			
		}catch(DatabaseException e) {
			e.printStackTrace();
		}
		assertEquals(1, size);
	}
	
	//delete res
	@Test
	public void testDeleteReservation() {
		
		ReservationController resContr = ReservationController.getInstance();
		
		ReservationBean resBean = new ReservationBean();
		
		boolean res = false;
		
		try {
			resBean.setId(resId);
			res = resContr.deleteReservation(resBean);
		}catch(DatabaseException e) {
			e.printStackTrace();
			
		} catch (ReservationException e) {
			e.printStackTrace();
		}
		assertEquals(true, res);
	}
	
	@AfterClass
	public static void destroy() {
		
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		AccountBean a1 = new AccountBean();
		AccountBean a2 = new AccountBean();
		
		a1.setCf("RSSMTN02T18F205M");
		a2.setCf("MRUFBL08R47A662B");
		
		RoomDAOImpl rDAO = RoomDAOImpl.getInstance();
		RoomBean roomBean = new RoomBean();
		roomBean.setName("Beautiful room");
		try {
			roomBean.setId(rDAO.getRoomId(roomBean));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		RoomController rContr = RoomController.getInstance();
		try {
			rContr.deleteRoom(roomBean);
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}
		
		try {
			accountDao.removeAccount(a1);
			accountDao.removeAccount(a2);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
	}
}
