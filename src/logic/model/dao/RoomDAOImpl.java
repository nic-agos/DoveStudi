package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import logic.bean.AccountBean;
import logic.bean.RoomBean;
import logic.bean.RoomSpecBean;

public class RoomDAOImpl implements RoomDAO {
	
	private static final String CREATE_ROOM_QUERY = "INSERT INTO room (Name, Address, Num_Partecipants, Num_Available_Seats, Owner, Specification) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_ROOM_PARTECIPANTS_QUERY = "UPDATE room SET Num_Partecipants = ?, Num_Available_Seats = ? WHERE ID = ?";
	private static final String GETALL_ROOMS_QUERY = "SELECT * FROM room" ;
	private static final String DELETE_ROOM_QUERY = "DELETE FROM room WHERE ID = ?";
	private static final String GET_ROOM_QUERY = "SELECT * FROM room WHERE ID = ?";
	private static final String GET_ROOM_ID_QUERY = "SELECT ID FROM room WHERE Name = ?";
	private static final String GET_ACCOUNT_ROOMS_QUERY  = "SELECT * FROM room WHERE Owner = ?";
	private static final String GET_ROOM_FROM_SPEC_QUERY = "SELECT * FROM room WHERE Specification = ?";
	private static final String GET_ROOM_FILTERED_AVAILABLE_SEATS_QUERY = "SELECT * FROM room WHERE Num_Available_Seats >= ?";
	
	
	private static RoomDAOImpl instance = null;
	
	private RoomDAOImpl() {
		
	}
	
	public static synchronized RoomDAOImpl getInstance() {
		if(RoomDAOImpl.instance == null) {
			RoomDAOImpl.instance = new RoomDAOImpl();
		}
		return instance;
	}
	
	@Override
	public int createRoom(RoomBean roomBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(CREATE_ROOM_QUERY);
			stmt.setString(1, roomBean.getName());
			stmt.setString(2, roomBean.getAddress());
			stmt.setInt(3, roomBean.getNumPartecipants());
			stmt.setInt(4, roomBean.getNumAvailableSeats());
			stmt.setString(5, roomBean.getOwner());
			stmt.setInt(6, roomBean.getSpecification());
			
			stmt.executeUpdate();
			
			return getRoomId(roomBean);
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	@Override
	public List<RoomBean> getAllRooms() throws SQLException {

		List<RoomBean> roomsList = new ArrayList<>();
		RoomBean room = null;
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GETALL_ROOMS_QUERY);
			
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				room = new RoomBean(res.getInt(1), res.getString(2),res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getInt(7));
				roomsList.add(room);
			}
			
			res.close();
			
			return roomsList;
		
		}finally {
			if(stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		
		}
	}
	
	@Override
	public int updateRoom(RoomBean roomBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(UPDATE_ROOM_PARTECIPANTS_QUERY);
			stmt.setInt(1, roomBean.getNumPartecipants());
			stmt.setInt(2, roomBean.getNumAvailableSeats());
			stmt.setInt(3, roomBean.getId());
			
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
	
	@Override
	public int removeRoom(RoomBean roomBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_ROOM_QUERY);
			stmt.setInt(1, roomBean.getId());
			
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
	
	@Override
	public RoomBean getRoom(RoomBean roomBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		RoomBean room = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ROOM_QUERY);
			stmt.setInt(1, roomBean.getId());
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				room = new RoomBean(res.getInt(1), res.getString(2),res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getInt(7));
			}
			
			return room;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public int getRoomId(RoomBean roomBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		int id = 0;		
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ROOM_ID_QUERY);
			stmt.setString(1, roomBean.getName());

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
	
	@Override
	public List<RoomBean> getAllAccountRooms(AccountBean accountBean) throws SQLException {
		
		List<RoomBean> accountRooms = new ArrayList<>();
		RoomBean room = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ACCOUNT_ROOMS_QUERY);
			stmt.setString(1, accountBean.getCf());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					room = new RoomBean(res.getInt(1), res.getString(2),res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getInt(7));
					accountRooms.add(room);
				}
				
			res.close();
			
			return accountRooms;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	public RoomBean getRoomFromSpec(RoomSpecBean roomSpecBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		RoomBean room = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ROOM_FROM_SPEC_QUERY);
			stmt.setInt(1, roomSpecBean.getId());
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				room = new RoomBean(res.getInt(1), res.getString(2),res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getInt(7));
			}
			
			return room;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public List<RoomBean> getRoomFilteredByAvailableSeats(RoomBean roomBean) throws SQLException{
		
		List<RoomBean> filteredRooms = new ArrayList<>();
		RoomBean room = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ROOM_FILTERED_AVAILABLE_SEATS_QUERY);
			stmt.setInt(1, roomBean.getNumAvailableSeats());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					room = new RoomBean(res.getInt(1), res.getString(2),res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getInt(7));
					filteredRooms.add(room);
				}
				
			res.close();
			
			return filteredRooms;
			
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
