package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Printers {
	
	private static final String GETALL_RESERVATIONS_QUERY = "SELECT * FROM reservation";
	private static final String GETALL_ROOMS_QUERY = "SELECT * FROM room" ;
	private static final String GETALL_ACCOUNTS_QUERY = "SELECT * FROM account";
	
	public void printReservations() throws SQLException {
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	
			ResultSet res = stmt.executeQuery(GETALL_RESERVATIONS_QUERY);
			
			System.out.println("Reservation DB");
			System.out.println("ID  Reserving_User  Room  Room_Owner  isGroup  Date  Start_Time  End_Time\n");
			
			while (res.next()) {
				System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s %n", res.getInt(1), res.getString(2), res.getInt(3), res.getInt(4), res.getBoolean(5), res.getString(6), res.getString(7), res.getString(8));
			}
			
			res.close();

		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public void printRooms() throws SQLException {
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	
			ResultSet res = stmt.executeQuery(GETALL_ROOMS_QUERY);
			
			System.err.println("Room DB");
			System.err.println("ID  Name  Descrption  Address  Num_Partecipants  Num_Available_Seats  Date  Start_Time  End_Time  Owner\n");
			
			while (res.next()) {
				System.err.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s %n", res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9));
			}
			
			res.close();

		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public void printAccounts() throws SQLException {
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	
			ResultSet res = stmt.executeQuery(GETALL_ACCOUNTS_QUERY);
			
			System.out.println("Account DB");
			System.out.println("CF  Name  Surname  Email  Password  Date_Birth  City_Birth  Number_Token\n");
			
			while (res.next()) {
				System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s %n", res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getInt(8));
			}
			
			res.close();

		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
}
