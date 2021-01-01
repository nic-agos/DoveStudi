package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.bean.AccountBean;
import logic.bean.ReservationBean;

public class ReservationDAOImpl {
	
	private static final String GET_ACCOUNT_RESERVATIONS_QUERY = "SELECT * FROM reservation WHERE Reserving_User = ?";
	private static final String CREATE_QUERY = "INSERT INTO reservation (Reserving_User, Room, Room_Owner, isGroup, Date, Start_Time, End_Time) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ID_QUERY = "SELECT ID FROM reservation WHERE Reserving_User = ? AND Room = ? AND Room_Owner = ?";
	private static final String GETALL_RESERVATIONS_QUERY = "SELECT * FROM reservation";
	private static final String DELETE_QUERY = "DELETE FROM reservation WHERE ID = ?";
	private static final String GET_RESERVATION_QUERY = "SELECT * FROM reservation WHERE ID = ?";
	
	public int createReservation(ReservationBean reservationBean) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int id;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(CREATE_QUERY);
			stmt.setString(1, reservationBean.getReservingUser());
			stmt.setInt(2, reservationBean.getLinkedRoom());
			stmt.setInt(3, reservationBean.getRoomOwner());
			stmt.setBoolean(4, reservationBean.getIsGroup());
			stmt.setString(5, reservationBean.getDate());
			stmt.setString(6, reservationBean.getStartTime());
			stmt.setString(7, reservationBean.getEndTime());
			
			stmt.executeUpdate();
			
			id = getReservationId(reservationBean);
			
			return id;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
			
		}
	}
	
	public int removeReservation(ReservationBean reservationBean) throws Exception {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_QUERY);
			stmt.setInt(1, reservationBean.getId());
			
			int res = stmt.executeUpdate();
			
			return res;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
		
	}
	
	public int getReservationId(ReservationBean reservationBean) throws Exception {
		PreparedStatement stmt = null;
		Connection connection = null;
		int id = 0;	
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ID_QUERY);
			stmt.setString(1, reservationBean.getReservingUser());
			stmt.setInt(2, reservationBean.getLinkedRoom());
			stmt.setInt(3, reservationBean.getRoomOwner());

			ResultSet r = stmt.executeQuery();
			
			while (r.next()) {
				id = r.getInt(1);
			}
			
			return id;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	public List<ReservationBean> getAllAccountReservations(AccountBean accountBean) throws Exception {
		
		List<ReservationBean> accountReservations = new ArrayList<>();
		ReservationBean reservation = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ACCOUNT_RESERVATIONS_QUERY);
			stmt.setString(1, accountBean.getCF());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					reservation = new ReservationBean(res.getInt(1), res.getString(2), res.getInt(3), res.getInt(4), res.getBoolean(5), res.getString(6), res.getString(7), res.getString(8));
					accountReservations.add(reservation);
				}
				
			res.close();
			
			return accountReservations;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	public List<ReservationBean> getAllReservations() throws Exception {
		
		List<ReservationBean> reservationsList = new ArrayList<>();
		ReservationBean reservation = null;
	
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GETALL_RESERVATIONS_QUERY);
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				reservation = new ReservationBean(res.getInt(1), res.getString(2), res.getInt(3), res.getInt(4), res.getBoolean(5), res.getString(6), res.getString(7), res.getString(8));
				reservationsList.add(reservation);
			}
			
			res.close();
			
			return reservationsList;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	public ReservationBean getReservation(int id) throws Exception {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		ReservationBean reservation = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_RESERVATION_QUERY);
			stmt.setInt(1, id);
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				reservation = new ReservationBean(res.getInt(1), res.getString(2), res.getInt(3), res.getInt(4), res.getBoolean(5), res.getString(6), res.getString(7), res.getString(8));
			}
			
			return reservation;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
		
	}
	
	public void printReservations() throws Exception {
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	
			ResultSet res = stmt.executeQuery(GETALL_RESERVATIONS_QUERY);
			
			System.out.println("Reservation DB");
			System.out.println("ID  Reserving_User  Room  Room_Owner  isGroup  Date  Start_Time  End_Time\n");
			
			while (res.next()) {
				System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s\n", res.getInt(1), res.getString(2), res.getInt(3), res.getInt(4), res.getBoolean(5), res.getString(6), res.getString(7), res.getString(8));
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
