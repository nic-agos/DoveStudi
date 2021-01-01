package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import logic.bean.AccountBean;
import logic.bean.ReservationBean;
import logic.model.dao.*;

public class TestReservationDAOImpl {
	
	@Test
	public void testGetAllAccountReservation() throws SQLException, Exception {
		AccountBean a = new AccountBean("marco", "m", "a", "m", "m", "2020-12-27", "m", 10);
		ReservationDAOImpl dao = new ReservationDAOImpl();
		List<ReservationBean> res = dao.getAllAccountReservations(a);
		System.out.println(res.get(0).getId());
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetReservationId() throws SQLException, Exception {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean ("marco", 24, 12, true,"2020-12-31", "09:00", "10:00");
		int res = r.getReservationId(b);
		assertEquals(res, 5);
	}
	
	@Test
	public void testCreateReservationId() throws SQLException, Exception {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean ("mrro", 24, 13, true ,"2020-12-31", "09:00", "10:00");
		int res = r.createReservation(b);
		assertEquals(res, 5);
	}
	
	@Test
	public void testGetAllReservations() throws SQLException, Exception {
		ReservationDAOImpl r = new ReservationDAOImpl();
		List<ReservationBean> res = r.getAllReservations();
		System.out.println(res.get(0).getId());
		System.out.println(res.get(1).getId());
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveReservation() throws SQLException, Exception {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean b = new ReservationBean (5, "marco", 24, 12, true,"2020-12-31", "09:00", "10:00");
		int res = r.removeReservation(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetReservation() throws SQLException, Exception {
		ReservationDAOImpl r = new ReservationDAOImpl();
		ReservationBean res = r.getReservation(8);
		System.out.println(res.getLinkedRoom());
		assertEquals(res,24);
	}
	
	@Test
	public void testPrintReservations() throws Exception {
		Printers r = new Printers();
		r.printReservations();
	}
}
