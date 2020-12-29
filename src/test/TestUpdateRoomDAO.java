package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;

import logic.model.dao.RoomDAOImpl;
import logic.bean.*;

public class TestUpdateRoomDAO {
	
	@Test
	public void testUpdateRoom() throws SQLException, Exception {
		RoomBean r = new RoomBean("b", "b","b", 6, 4, "2020-01-12", "09:00", "13:00", "marco");
		r.setId(23);
		RoomDAOImpl d = new RoomDAOImpl();
		int res = d.updateRoom(r);
		assertEquals(res, 1);
	}

}
