package test;

import java.sql.SQLException;

import org.junit.Test;

import logic.model.dao.DBCreation;

public class Testing {

	@Test
	public void testing() throws SQLException {
		DBCreation.createTables();
	}
}
