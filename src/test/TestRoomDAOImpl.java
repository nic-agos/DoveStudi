package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.model.dao.RoomDAOImpl;
import logic.bean.*;

public class TestRoomDAOImpl {
	
	@Test
	public void testCreateRoom() throws Exception {
		RoomDAOImpl r = new RoomDAOImpl();
		RoomBean room = new RoomBean("b", "b", "b", 5, 5, "2020-1-12", "12:00", "13:00", "marco");
		int res = r.createRoom(room);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAllRooms() throws Exception {
		List<RoomBean> ex = new ArrayList<>();
		RoomBean r = new RoomBean("n","n","n", 5, 5, "t", "t", "t", "marco");
		ex.add(r);
		RoomDAOImpl as = new RoomDAOImpl();
		List<RoomBean> fdb = as.getAllRooms();
		assertEquals(fdb, ex);
		
	}
	
	@Test
	public void testUpdateRoom() throws SQLException, Exception {
		RoomBean r = new RoomBean("b", "b","b", 6, 4, "2020-01-12", "09:00", "13:00", "marco");
		r.setId(23);
		RoomDAOImpl d = new RoomDAOImpl();
		int res = d.updateRoom(r);
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveRoom() throws SQLException, Exception {
		RoomBean r = new RoomBean();
		r.setId(23);
		RoomDAOImpl d = new RoomDAOImpl();
		int res = d.removeRoom(r);
		assertEquals(res, 1);
	}
}
