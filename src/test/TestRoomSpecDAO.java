package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import logic.bean.*;
import logic.model.dao.*;

public class TestRoomSpecDAO {
	
	private int id;
	private String description;
	private String date;
	private String startTime;
	private String endTime;

	@Test
	public void testCreateRoomSpec() throws SQLException {
		RoomSpecDAOImpl r = new RoomSpecDAOImpl();
		RoomSpecBean b = new RoomSpecBean(description, date, startTime, endTime);
		int res = r.createRoomSpec(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetRoomSpecId() throws SQLException {
		RoomSpecDAOImpl r = new RoomSpecDAOImpl();
		RoomSpecBean b = new RoomSpecBean(description, date, startTime, endTime);
		int res = r.getRoomSpecId(b);
		assertEquals(res, 28);
	}
	
	@Test
	public void testGetRoomSpec() throws SQLException {
		RoomSpecDAOImpl r = new RoomSpecDAOImpl();
		RoomSpecBean res = r.getRoomSpec(id);
		assertEquals(res, 30);
	}
	
	@Test
	public void testGRemoveRoomSpec() throws SQLException {
		RoomSpecDAOImpl r = new RoomSpecDAOImpl();
		RoomSpecBean b = new RoomSpecBean(id, description, date, startTime, endTime);
		int res = r.removeRoomSpec(b);
		assertEquals(res, 1);
	}
	
	
	
	
}
