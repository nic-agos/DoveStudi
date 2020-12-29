package test;

import static org.junit.Assert.*;
import org.junit.Test;

import logic.bean.*;
import logic.model.dao.*;


public class TestInsertRoomDAO  {
	
	@Test
	public void testInsertRoom() throws Exception {
		RoomDAOImpl r = new RoomDAOImpl();
		RoomBean room = new RoomBean("b", "b", "b", 5, 5, "2020-1-12", "12:00", "13:00", "marco");
		int res = r.createRoom(room);
		assertEquals(res, 1);
	}
	
}
