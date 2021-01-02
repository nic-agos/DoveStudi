package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import logic.bean.*;
import logic.model.dao.*;

public class TestRoomSpecDAO {

	@Test
	public void testCreateRoomSpec() throws SQLException {
		RoomSpecDAOImpl r = new RoomSpecDAOImpl();
		RoomSpecBean b = new RoomSpecBean("c", "2020:12:12", "09:00", "10:00");
		int res = r.createRoomSpec(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetRoomSpecId() throws SQLException {
		RoomSpecDAOImpl r = new RoomSpecDAOImpl();
		RoomSpecBean b = new RoomSpecBean(28, "c", "2020:12:12", "09:00", "10:00");
		int res = r.getRoomSpecId(b);
		assertEquals(res, 28);
	}
	
	
}
