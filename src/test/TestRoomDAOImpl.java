package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.model.dao.RoomDAOImpl;
import logic.bean.*;

public class TestRoomDAOImpl {
	
	private int id = 31;
	private String name = "b";
	private String address = "b";
	private int numPartecipants = 4;
	private int numAvailableSeats = 3;
	private String owner = "a";
	private int specification = 31;
	
	
	@Test
	public void testCreateRoom() throws SQLException {
		RoomDAOImpl d = RoomDAOImpl.getInstance();
		RoomBean r = new RoomBean(name, address, numPartecipants, numAvailableSeats, owner, specification);
		int res = d.createRoom(r);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAllRooms() throws SQLException {
		List<RoomBean> ex = new ArrayList<>();
		RoomBean r = new RoomBean(name, address, numPartecipants, numAvailableSeats, owner, specification);
		ex.add(r);
		RoomDAOImpl as = RoomDAOImpl.getInstance();
		List<RoomBean> fdb = as.getAllRooms();
		assertEquals(fdb, ex);
		
	}
	
	@Test
	public void testUpdateRoom() throws SQLException {
		RoomBean r = new RoomBean(id, name, address, numPartecipants, numAvailableSeats, owner, specification);
		RoomDAOImpl d = RoomDAOImpl.getInstance();
		int res = d.updateRoom(r);
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveRoom() throws SQLException {
		RoomBean r = new RoomBean();
		r.setId(33);
		RoomDAOImpl d = RoomDAOImpl.getInstance();
		int res = d.removeRoom(r);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetRoom() throws SQLException {
		RoomDAOImpl d = RoomDAOImpl.getInstance();
		RoomBean r = new RoomBean();
		r.setId(2);
		RoomBean res = d.getRoom(r);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetRoomId() throws SQLException {
		RoomDAOImpl d = RoomDAOImpl.getInstance();
		RoomBean r = new RoomBean(name, address, numPartecipants, numAvailableSeats, owner, specification);
		int res = d.getRoomId(r);
		assertEquals(res, 31);
	}
	
	@Test
	public void testGetAllAccountRooms() throws SQLException {
		AccountBean a = new AccountBean("a", "m", "a", "m", "2020-12-27", "m", 10);
		RoomDAOImpl d = RoomDAOImpl.getInstance();
		List<RoomBean> n = d.getAllAccountRooms(a);
		assertEquals(n, 1);
	}
	
	@Test
	public void testGetRoomFromSpec() throws SQLException {
		RoomDAOImpl d = RoomDAOImpl.getInstance();
		RoomSpecBean r = new RoomSpecBean();
		r.setId(2);
		RoomBean res = d.getRoomFromSpec(r);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetRoomFilteredByAvailableSeats() throws SQLException {
		RoomDAOImpl d = RoomDAOImpl.getInstance();
		List<RoomBean> ex = new ArrayList<>();
		RoomBean room = new RoomBean();
		room.setNumAvailableSeats(6);
		ex = d.getRoomFilteredByAvailableSeats(room);
	}
}
