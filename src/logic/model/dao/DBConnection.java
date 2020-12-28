package logic.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * realized with singleton pattern
 */

public class DBConnection {
	
	private static String USER = "root";
	private static String PASS = "Pomello99";
	private static String DB_URL = "jdbc:mysql://localhost:3306/dovestudi?useSSL=false";
	private static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static DBConnection instance = null;
	
	private static Connection conn = null;

	private DBConnection() throws SQLException {
		
		openDBConnection();
	}
	
	private static void openDBConnection() throws SQLException {
		try {
			Class.forName(DRIVER_CLASS_NAME);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
		}catch (ClassNotFoundException driverEx) {
			driverEx.printStackTrace();
		}
	}
		
	public static synchronized DBConnection getInstanceConnection() throws SQLException {

		if (instance == null) {
			instance = new DBConnection();

		} else if (instance.getConnection().isClosed()) {
			openDBConnection();
		}
		
		return instance;
	}	
	
	public Connection getConnection() {

		return conn;
	}
	
}
	
	
	
	
	
	
	

