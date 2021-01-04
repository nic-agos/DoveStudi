package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.bean.*;

public class RoomSpecDAOImpl implements RoomSpecDAO {
	
	private static final String CREATE_ROOMSPEC_QUERY = "INSERT INTO roomspec (Description, Date, Start_Time, End_Time) VALUES (?, ?, ?, ?)";
	private static final String GET_ROOMSPEC_ID_QUERY = "SELECT ID FROM roomspec WHERE Description = ? AND Date = ? AND Start_Time = ? AND End_Time = ?";
	private static final String DELETE_ROOMSPEC_QUERY = "DELETE FROM roomspec WHERE ID = ?";
	private static final String GET_ROOMSPEC_QUERY = "SELECT * FROM roomspec WHERE ID = ?";
	
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
	public RoomSpecBean getRoomSpec(int id) throws SQLException {
			
		PreparedStatement stmt = null;
		Connection connection = null;
		RoomSpecBean roomSpec = null;
			
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
				
			stmt = connection.prepareStatement(GET_ROOMSPEC_QUERY);
			stmt.setInt(1, id);
				
			ResultSet res = stmt.executeQuery();
				
			while(res.next()) {
				roomSpec = new RoomSpecBean(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
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
}
