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
		ReservationDAOImpl dao = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean (reservingUser, linkedRoom, roomOwner, date, startTime, endTime);
		int res = dao.createReservation(b);
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
		ReservationDAOImpl dao = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean (reservingUser, linkedRoom, roomOwner, date, startTime, endTime);
		int res = dao.getReservationId(b);
		assertEquals(res, 5);
	}
	
	@Test
	public void testGetAllReservations() throws SQLException {
		ReservationDAOImpl dao = new ReservationDAOImpl();
		List<ReservationBean> res = dao.getAllReservations();
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveReservation() throws SQLException {
		ReservationDAOImpl dao = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean (id, reservingUser, linkedRoom, roomOwner, date, startTime, endTime);
		int res = dao.removeReservation(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetReservation() throws SQLException {
		ReservationDAOImpl dao = new ReservationDAOImpl();
		ReservationBean r = new ReservationBean();
		r.setId(id);
		ReservationBean res = dao.getReservation(r);
		assertEquals(res,24);
	}
	
	@Test
	public void testGetRoomReservations() throws SQLException {
		ReservationDAOImpl dao = new ReservationDAOImpl();
		RoomBean ro = new RoomBean();
		ro.setId(32);
		List<ReservationBean> l = dao.getRoomReservations(ro);
		assertEquals(l, 1);
	}
	
	@Test
	public void testGetAllAccountReservations() throws SQLException {
		ReservationDAOImpl dao = new ReservationDAOImpl();
		AccountBean a = new AccountBean();
		a.setCf("b");
		List<ReservationBean> re = dao.getAllAccountReservations(a);
		assertEquals(re, 1);
	}
	
}
