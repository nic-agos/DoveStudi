package test;

import java.sql.SQLException;

import org.junit.Test;

import logic.model.database.DBCreation;

public class Testing {

	@Test
	public void testing() throws SQLException {
		DBCreation.createTables();
	}
}
