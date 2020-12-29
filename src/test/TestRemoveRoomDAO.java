package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;

import logic.model.dao.RoomDAOImpl;
import logic.bean.*;

public class TestRemoveRoomDAO {
	
	@Test
	public void testRemoveRoom() throws SQLException, Exception {
		RoomBean r = new RoomBean();
		r.setId(23);
		RoomDAOImpl d = new RoomDAOImpl();
		int res = d.removeRoom(r);
		assertEquals(res, 1);
	}

}
