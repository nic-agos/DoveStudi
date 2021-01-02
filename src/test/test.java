package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.model.Account;
import logic.model.Person;
import logic.model.dao.*;
import logic.bean.*;

public class test {
	
	@Test
	public void testGetRoomSpecId() throws SQLException {
		RoomSpecDAOImpl r = new RoomSpecDAOImpl();
		RoomSpecBean b = new RoomSpecBean(28, "c", "2020:12:12", "09:00", "10:00");
		int res = r.getRoomSpecId(b);
		assertEquals(res, 28);
	}
	
	
	
	
}
