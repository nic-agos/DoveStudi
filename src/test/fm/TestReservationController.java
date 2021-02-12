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
		
		AccountBean a1 = new AccountBean("MMMFFF96IJOP2", "Mario", "Rossi", "mario.rossi@gmail.it", "asdfgh", "1997:08:27", 0);
		PersonBean p1 = new PersonBean("mario97", "High School", "AB", "MMMFFF96IJOP2", 0, 0);
		
		AccountBean a2 = new AccountBean("MMMVVV999OPLYUTY", "Marta", "Verdi", "marta.verdi@gmail.com", "abcdef", "1983:07:24", 0);
		PersonBean p2 = new PersonBean("marta83", "High School", "CD", "MMMVVV999OPLYUTY", 0, 0);
		
		
		RoomBean rBean = new RoomBean();
		rBean.setName("bella stanza");
		rBean.setAddress("via test 21");
		rBean.setNumParticipants(3);
		rBean.setOwner("MMMVVV999OPLYUTY");

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
		
		AccountBean accBean = new AccountBean("MMMFFF96IJOP2", "Mario", "Rossi", "mario.rossi@gmail.it", "asdfgh", "1997:08:27", 0);
		
		ReservationDAOImpl reservationDAO = ReservationDAOImpl.getInstance();
		
		RoomDAOImpl roomDAO = RoomDAOImpl.getInstance();
		RoomBean roomBean = new RoomBean();
		RoomBean roomBean2 = new RoomBean();
		int id;
		boolean res = false;
		try {
			roomBean.setName("bella stanza");
			id = roomDAO.getRoomId(roomBean);
			roomBean2.setId(id);
			res = resContr.makeReservation(roomBean2, accBean);	
			
			ReservationBean resBean = new ReservationBean();
			resBean.setReservingUser("MMMFFF96IJOP2");
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
		AccountBean accBean = new AccountBean("MMMFFF96IJOP2", "Mario", "Rossi", "mario.rossi@gmail.it", "asdfgh", "1997:08:27", 0);
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
		
		a1.setCf("MMMFFF96IJOP2");
		a2.setCf("MMMVVV999OPLYUTY");
		
		try {
			
			accountDao.removeAccount(a1);
			accountDao.removeAccount(a2);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
