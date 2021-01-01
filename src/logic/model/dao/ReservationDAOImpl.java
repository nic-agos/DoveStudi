package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.AccountBean;
import logic.bean.ReservationBean;

public class ReservationDAOImpl implements ReservationDAO {
	
	private static final String GET_ACCOUNT_RESERVATIONS_QUERY = "SELECT * FROM reservation WHERE Reserving_User = ?";
	private static final String CREATE_QUERY = "INSERT INTO reservation (Reserving_User, Room, Room_Owner, isGroup, Date, Start_Time, End_Time) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ID_QUERY = "SELECT ID FROM reservation WHERE Reserving_User = ? AND Room = ? AND Room_Owner = ?";
	private static final String GETALL_RESERVATIONS_QUERY = "SELECT * FROM reservation";
	private static final String DELETE_QUERY = "DELETE FROM reservation WHERE ID = ?";
	private static final String GET_RESERVATION_QUERY = "SELECT * FROM reservation WHERE ID = ?";
	
	public int createReservation(ReservationBean reservationBean) throws SQLException {
		
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
	
	public int removeReservation(ReservationBean reservationBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_QUERY);
			stmt.setInt(1, reservationBean.getId());
			
			return stmt.executeUpdate();
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
		
	}
	
	public int getReservationId(ReservationBean reservationBean) throws SQLException {
		PreparedStatement stmt = null;
		Connection connection = null;
		int id = 0;	
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ID_QUERY);
			stmt.setString(1, reservationBean.getReservingUser());
			stmt.setInt(2, reservationBean.getLinkedRoom());
			stmt.setInt(3, reservationBean.getRoomOwner());

			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				id = res.getInt(1);
			}
			
			res.close();
			
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
	
	public List<ReservationBean> getAllAccountReservations(AccountBean accountBean) throws SQLException {
		
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
	
	public List<ReservationBean> getAllReservations() throws SQLException {
		
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
	
	public ReservationBean getReservation(int id) throws SQLException {
		
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
}
