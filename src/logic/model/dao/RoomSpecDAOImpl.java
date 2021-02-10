package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;
import logic.model.database.DBConnection;

public class RoomSpecDAOImpl implements RoomSpecDAO {
	
	private static final String CREATE_ROOMSPEC_QUERY = "INSERT INTO roomspec (Description, Date, Start_Time, End_Time, CAP) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ROOMSPEC_ID_QUERY = "SELECT ID FROM roomspec WHERE Description = ? AND Date = ? AND Start_Time = ? AND End_Time = ?";
	private static final String DELETE_ROOMSPEC_QUERY = "DELETE FROM roomspec WHERE ID = ?";
	private static final String GET_ROOMSPEC_QUERY = "SELECT * FROM roomspec WHERE ID = ?";
	private static final String GET_ROOMSPEC_BY_CAP = "SELECT * FROM roomspec WHERE CAP = ?";
	private static final String GET_ORDERED_ROOMSPEC = "SELECT * FROM roomspec ORDER BY Date, Start_Time";
	private static final String GET_ROOMSPEC_BY_DATE = "SELECT * FROM roomspec WHERE Date = ? ORDER BY Start_Time";
	private static final String GET_ALL_ROOMSPEC = "SELECT * FROM roomspec";
	
	
	private static RoomSpecDAOImpl instance = null;
	
	private RoomSpecDAOImpl() {
		
	}
	
	public static synchronized RoomSpecDAOImpl getInstance() {
		if(RoomSpecDAOImpl.instance == null) {
			RoomSpecDAOImpl.instance = new RoomSpecDAOImpl();
		}
		return instance;
	}
	
	@Override
	public int createRoomSpec(RoomSpecBean roomSpecBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(CREATE_ROOMSPEC_QUERY);
			stmt.setString(1, roomSpecBean.getDescription());
			stmt.setString(2, roomSpecBean.getDate());
			stmt.setString(3, roomSpecBean.getStartTime());
			stmt.setString(4, roomSpecBean.getEndTime());
			stmt.setString(5, roomSpecBean.getCap());
			stmt.executeUpdate();
			
			return getRoomSpecId(roomSpecBean);
			
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
	public int getRoomSpecId(RoomSpecBean roomSpecBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		int id = 0;		
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ROOMSPEC_ID_QUERY);
			stmt.setString(1, roomSpecBean.getDescription());
			stmt.setString(2, roomSpecBean.getDate());
			stmt.setString(3, roomSpecBean.getStartTime());
			stmt.setString(4, roomSpecBean.getEndTime());

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
	public int removeRoomSpec(RoomSpecBean roomSpecBean) throws SQLException {
			
		PreparedStatement stmt = null;
		Connection connection = null;
			
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
				
			stmt = connection.prepareStatement(DELETE_ROOMSPEC_QUERY);
			stmt.setInt(1, roomSpecBean.getId());
				
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
	public RoomSpecBean getRoomSpec(RoomBean roomBean) throws SQLException {
			
		PreparedStatement stmt = null;
		Connection connection = null;
		RoomSpecBean roomSpec = null;
			
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
				
			stmt = connection.prepareStatement(GET_ROOMSPEC_QUERY);
			stmt.setInt(1, roomBean.getSpecification());
				
			ResultSet res = stmt.executeQuery();
				
			while(res.next()) {
				roomSpec = new RoomSpecBean(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6));
			}
				
			return roomSpec;
				
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
	public List<RoomSpecBean> getRoomsSpecByCap(RoomSpecBean roomSpecBean) throws SQLException {
		
		List<RoomSpecBean> roomsList = new ArrayList<>();
		RoomSpecBean roomSpec = null;
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ROOMSPEC_BY_CAP);
			stmt.setString(1, roomSpecBean.getCap());
			
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				roomSpec = new RoomSpecBean(res.getInt(1), res.getString(2),res.getString(3), res.getString(4), res.getString(5), res.getString(6));
				roomsList.add(roomSpec);
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
	public List<RoomSpecBean> getOrderedRoomsSpec() throws SQLException {
		
		List<RoomSpecBean> orderedRoomsSpecList = new ArrayList<>();
		RoomSpecBean roomSpec = null;
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ORDERED_ROOMSPEC);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				roomSpec = new RoomSpecBean(res.getInt(1), res.getString(2),res.getString(3), res.getString(4), res.getString(5), res.getString(6));
				orderedRoomsSpecList.add(roomSpec);
			}
			
			res.close();
			
			return orderedRoomsSpecList;
		
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
	public List<RoomSpecBean> getRoomsSpecByDate(RoomSpecBean roomSpecBean) throws SQLException {
		
		List<RoomSpecBean> roomsSpecByDateList = new ArrayList<>();
		RoomSpecBean roomSpec = null;
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ROOMSPEC_BY_DATE);
			stmt.setString(1, roomSpecBean.getDate());
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				roomSpec = new RoomSpecBean(res.getInt(1), res.getString(2),res.getString(3), res.getString(4), res.getString(5), res.getString(6));
				roomsSpecByDateList.add(roomSpec);
			}
			
			res.close();
			
			return roomsSpecByDateList;
		
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
	public List<RoomSpecBean> getAllRoomsSpec() throws SQLException {
		
		List<RoomSpecBean> allRoomsSpecList = new ArrayList<>();
		RoomSpecBean roomSpec = null;
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ALL_ROOMSPEC);
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				roomSpec = new RoomSpecBean(res.getInt(1), res.getString(2),res.getString(3), res.getString(4), res.getString(5), res.getString(6));
				allRoomsSpecList.add(roomSpec);
			}
			
			res.close();
			
			return allRoomsSpecList;
		
		}finally {
			if(stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		
		}
	}	
}
