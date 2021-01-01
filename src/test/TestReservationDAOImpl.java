package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import logic.bean.AccountBean;
import logic.bean.ReservationBean;
import logic.model.dao.*;

public class TestReservationDAOImpl {
	
	private String reservingUser = "marco";
	private int linkedRoom = 24;
	private int roomOwner = 12;
	private boolean isGroup = true;
	private String date = "2020-12-27";
	private String startTime = "09:00";
	private String endTime = "10:00";
	
	@Test
	public void testGetAllAccountReservation() throws SQLException {
		AccountBean a = new AccountBean("marco", "m", "a", "m", "m", "2020-12-27", "m", 10);
		ReservationDAOImpl dao = new ReservationDAOImpl();
		List<ReservationBean> res = dao.getAllAccountReservations(a);
		System.out.println(res.get(0).getId());
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetReservationId() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean (reservingUser, linkedRoom, roomOwner, isGroup, date, startTime, endTime);
		int res = r.getReservationId(b);
		assertEquals(res, 5);
	}
	
	@Test
	public void testCreateReservationId() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean (reservingUser, linkedRoom, roomOwner, isGroup, date, startTime, endTime);
		int res = r.createReservation(b);
		assertEquals(res, 5);
	}
	
	@Test
	public void testGetAllReservations() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		List<ReservationBean> res = r.getAllReservations();
		System.out.println(res.get(0).getId());
		System.out.println(res.get(1).getId());
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveReservation() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean (reservingUser, linkedRoom, roomOwner, isGroup, date, startTime, endTime);
		int res = r.removeReservation(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetReservation() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean res = r.getReservation(8);
		System.out.println(res.getLinkedRoom());
		assertEquals(res,24);
	}
	
	@Test
	public void testPrintReservations() throws SQLException {
		Printers r = new Printers();
		r.printReservations();
	}
}
