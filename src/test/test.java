package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.model.dao.*;
import logic.bean.*;

public class test {
	
	@Test
	public void testPrintRooms() throws Exception {
		RoomDAOImpl r = new RoomDAOImpl();
		r.printRooms();
	}
}