package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import logic.bean.RoomBean;

public class RoomDAOImpl implements RoomDAO {
	
	private static final String CREATE_QUERY = "INSERT INTO room (Name, Description, Address, Num_Partecipants, Num_Available_Seats, Date, Start_Time, End_Time, Owner)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_PARTECIPANTS_QUERY = "UPDATE room SET Num_Partecipants = ?, Num_Available_Seats = ? WHERE ID = ?";
	private static final String GETALL_QUERY = "SELECT * FROM room" ;
	private static final String DELETE_QUERY = "DELETE FROM room WHERE ID = ?";
	
	@Override
	public int createRoom(RoomBean roomBean) throws Exception, SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		int id;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(CREATE_QUERY);
			stmt.setString(1, roomBean.getName());
			stmt.setString(2, roomBean.getDescription());
			stmt.setString(3, roomBean.getAddress());
			stmt.setInt(4, roomBean.getNumPartecipants());
			stmt.setInt(5, roomBean.getNumAvailableSeats());
			stmt.setString(6, roomBean.getDate());
			stmt.setString(7, roomBean.getStartTime());
			stmt.setString(8, roomBean.getEndTime());
			stmt.setString(9, roomBean.getOwner());
			
			stmt.executeUpdate();
			
			id = DAOQueries.getRoomId(connection, roomBean);
			
			return id;
			
		
		}finally {
			if (connection != null) {
				connection.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			
		}
	}
	@Override
	public void readRooms() throws Exception {
		
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			DAOQueries.printRoomList(connection);

		
		}finally {
			if (connection != null) {
				connection.close();
			}
			
		}
		
	}
	
	@Override
	public List<RoomBean> getAllRooms() throws Exception, SQLException {

		List<RoomBean> roomsList = new ArrayList<>();
		RoomBean room = null;
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet res = stmt.executeQuery(GETALL_QUERY);
			while (res.next()) {
				room = new RoomBean(res.getString(2),res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10));
				roomsList.add(room);
			}
			
			res.close();
			
			return roomsList;
		
		}finally {
			if (connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		
		}
	}
	
	@Override
	public int updateRoom(RoomBean roomBean) throws Exception, SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(UPDATE_PARTECIPANTS_QUERY);
			stmt.setInt(1, roomBean.getNumPartecipants());
			stmt.setInt(2, roomBean.getNumAvailableSeats());
			stmt.setInt(3, roomBean.getId());
			
			int res = stmt.executeUpdate();
			
			return res;
			
		}finally {
			if (connection != null ) {
				connection.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			
		}
	}
	
	@Override
	public int removeRoom(RoomBean roomBean) throws Exception, SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_QUERY);
			stmt.setInt(1, roomBean.getId());
			
			int res = stmt.executeUpdate();
			
			return res;
			
			
		}finally {
			if (connection != null ) {
				connection.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
	}
	
	
		
}
