package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import logic.bean.AccountBean;
import logic.bean.RoomBean;

public class RoomDAOImpl implements RoomDAO {
	
	private static final String CREATE_QUERY = "INSERT INTO room (Name, Description, Address, Num_Partecipants, Num_Available_Seats, Date, Start_Time, End_Time, Owner)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_PARTECIPANTS_QUERY = "UPDATE room SET Num_Partecipants = ?, Num_Available_Seats = ? WHERE ID = ?";
	private static final String GETALL_QUERY = "SELECT * FROM room" ;
	private static final String DELETE_QUERY = "DELETE FROM room WHERE ID = ?";
	private static final String GET_ROOM_QUERY = "SELECT * FROM room WHERE ID = ?";
	private static final String GET_ID_QUERY = "SELECT ID FROM room WHERE Name = ?";
	private static final String GET_ACCOUNT_ROOMS_QUERY  = "SELECT * FROM room WHERE Owner = ?";
	
	@Override
	public int createRoom(RoomBean roomBean) throws Exception {
		
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
			
			id = getRoomId(roomBean);
			
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
	
	@Override
	public void printRooms() throws Exception {
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	
			ResultSet res = stmt.executeQuery(GETALL_QUERY);
			
			System.err.println("Room DB");
			System.err.println("ID  Name  Descrption  Address  Num_Partecipants  Num_Available_Seats  Date  Start_Time  End_Time  Owner\n");
			
			while (res.next()) {
				System.err.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s\n", res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9));
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
	
	@Override
	public List<RoomBean> getAllRooms() throws Exception {

		List<RoomBean> roomsList = new ArrayList<>();
		RoomBean room = null;
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GETALL_QUERY);
			
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				room = new RoomBean(res.getInt(1), res.getString(2),res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10));
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
	public int updateRoom(RoomBean roomBean) throws Exception {
		
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
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public int removeRoom(RoomBean roomBean) throws Exception {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_QUERY);
			stmt.setInt(1, roomBean.getId());
			
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
	
	@Override
	public RoomBean getRoom(int id) throws Exception {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		RoomBean room = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ROOM_QUERY);
			stmt.setInt(1, id);
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				room = new RoomBean(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10));
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
	public int getRoomId(RoomBean roomBean) throws Exception {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		int id = 0;		
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ID_QUERY);
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
	public List<RoomBean> getAllAccountRooms(AccountBean accountBean) throws Exception {
		
		List<RoomBean> accountRooms = new ArrayList<>();
		RoomBean room = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ACCOUNT_ROOMS_QUERY);
			stmt.setString(1, accountBean.getCF());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					room = new RoomBean(res.getString(2),res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10));
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
}
