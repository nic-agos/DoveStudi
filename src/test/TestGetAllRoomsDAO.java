package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import logic.model.dao.RoomDAOImpl;
import logic.bean.*;


public class TestGetAllRoomsDAO {
	
	@Test
	public void testGetAllRooms() throws Exception {
		List<RoomBean> ex = new ArrayList<>();
		RoomBean r = new RoomBean("n","n","n", 5, 5, "t", "t", "t", "marco");
		ex.add(r);
		RoomDAOImpl as = new RoomDAOImpl();
		List<RoomBean> fdb = as.getAllRooms();
		assertEquals(fdb, ex);
		
	}
}
