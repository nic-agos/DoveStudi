package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.model.dao.RoomDAOImpl;

public class TestReadRoomsDAO {
	
	@Test
	public void testReadRooms() throws Exception {
		RoomDAOImpl r = new RoomDAOImpl();
		int res = r.readRooms();
		assertEquals((int)res, 1, 0);

	}
}
