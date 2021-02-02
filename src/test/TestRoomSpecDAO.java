package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.bean.*;
import logic.model.dao.*;

public class TestRoomSpecDAO {
	
	private int id;
	private String description;
	private String date;
	private String startTime;
	private String endTime;
	private String cap;

	@Test
	public void testCreateRoomSpec() throws SQLException {
		RoomSpecDAOImpl r = RoomSpecDAOImpl.getInstance();
		RoomSpecBean b = new RoomSpecBean(description, date, startTime, endTime, cap);
		int res = r.createRoomSpec(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetRoomSpecId() throws SQLException {
		RoomSpecDAOImpl r = RoomSpecDAOImpl.getInstance();
		RoomSpecBean b = new RoomSpecBean(description, date, startTime, endTime, cap);
		int res = r.getRoomSpecId(b);
		assertEquals(res, 28);
	}
	
	@Test
	public void testGetRoomSpec() throws SQLException {
		RoomSpecDAOImpl r = RoomSpecDAOImpl.getInstance();
		RoomBean room = new RoomBean();
		room.setSpecification(36);
		RoomSpecBean res = r.getRoomSpec(room);
		assertEquals(res, 30);
	}
	
	@Test
	public void testRemoveRoomSpec() throws SQLException {
		RoomSpecDAOImpl r = RoomSpecDAOImpl.getInstance();
		RoomSpecBean b = new RoomSpecBean(id, description, date, startTime, endTime, cap);
		int res = r.removeRoomSpec(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetRoomsSpecByCap() throws SQLException {
		RoomSpecDAOImpl r = RoomSpecDAOImpl.getInstance();
		List <RoomSpecBean> rooms = new ArrayList();
		RoomSpecBean bean = new RoomSpecBean();
		bean.setCap("00133");
		rooms = r.getRoomsSpecByCap(bean);
		assertEquals(1, rooms);
	}
	
	@Test
	public void testGetOrderedRoomsSpec() throws SQLException {
		RoomSpecDAOImpl r = RoomSpecDAOImpl.getInstance();
		List <RoomSpecBean> rooms = new ArrayList();
		rooms = r.getOrderedRoomsSpec();
		assertEquals(1, rooms);
	}
	
	@Test
	public void testGetRoomsSpecByDate() throws SQLException {
		RoomSpecDAOImpl r = RoomSpecDAOImpl.getInstance();
		List <RoomSpecBean> rooms = new ArrayList();
		RoomSpecBean bean = new RoomSpecBean();
		bean.setDate("2020-12-10");
		rooms = r.getRoomsSpecByDate(bean);
		assertEquals(1, rooms);
	}
	
	@Test
	public void testGetAllRoomsSpec() throws SQLException {
		RoomSpecDAOImpl r = RoomSpecDAOImpl.getInstance();
		List <RoomSpecBean> rooms = new ArrayList();
		rooms = r.getAllRoomsSpec();
		assertEquals(1, rooms);
	}
	
}
