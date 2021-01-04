package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import logic.bean.*;
import logic.model.dao.*;

public class TestReservationDAOImpl {
	
	private int id = 12;
	private String reservingUser = "a";
	private int linkedRoom = 31;
	private int roomOwner = 18;
	private String date = "2020-12-27";
	private String startTime = "09:00";
	private String endTime = "10:00";
	
	@Test
	public void testCreateReservation() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean (reservingUser, linkedRoom, roomOwner, date, startTime, endTime);
		int res = r.createReservation(b);
		assertEquals(res, 5);
	}
	
	@Test
	public void testGetAllAccountReservation() throws SQLException {
		AccountBean a = new AccountBean("marco", "m", "a", "m", "2020-12-27", "m", 10);
		ReservationDAOImpl dao = new ReservationDAOImpl();
		List<ReservationBean> res = dao.getAllAccountReservations(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetReservationId() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean (reservingUser, linkedRoom, roomOwner, date, startTime, endTime);
		int res = r.getReservationId(b);
		assertEquals(res, 5);
	}
	
	@Test
	public void testGetAllReservations() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		List<ReservationBean> res = r.getAllReservations();
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveReservation() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean (id, reservingUser, linkedRoom, roomOwner, date, startTime, endTime);
		int res = r.removeReservation(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetReservation() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean res = r.getReservation(id);
		assertEquals(res,24);
	}
	
	@Test
	public void testPrintReservations() throws SQLException {
		Printers r = new Printers();
		r.printReservations();
	}
	
	@Test
	public void testGetRoomReservations() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		RoomBean ro = new RoomBean();
		ro.setId(32);
		List<ReservationBean> l = r.getRoomReservations(ro);
		assertEquals(l, 1);
	}
	
	@Test
	public void testGetAllAccountReservations() throws SQLException {
		ReservationDAOImpl r = new ReservationDAOImpl();
		AccountBean a = new AccountBean();
		a.setCf("b");
		List<ReservationBean> re = r.getAllAccountReservations(a);
		assertEquals(re, 1);
	}
	
}
