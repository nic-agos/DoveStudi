package logic.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import logic.bean.*;

public class DAOQueries {
	
	public static boolean insertRoom(Statement stmt, RoomBean roomBean) throws SQLException{
		
		return true;
	}
	
	public static boolean printRoomList(Connection conn) throws SQLException{
		
		String query = "SELECT * FROM room";
		Statement stmt = null;
		
		try {
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	
		ResultSet res = stmt.executeQuery(query);
		
		while (res.next()) {
			System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s, %s\n", res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9));
		}
		
		res.close();
		return true;
		
		}finally {
            if (conn != null) {
            	conn.close();
                
            }
            if (stmt != null) {
            	stmt.close();
            }
                
		}   
	}
	
	public static List<RoomBean> getRoomList(Connection conn) throws SQLException{
		
		String query = "SELECT * FROM room";
		Statement stmt = null;
		List<RoomBean> rooms = new ArrayList<>();
		RoomBean room = null;
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet res = stmt.executeQuery(query);
				while (res.next()) {
					room = new RoomBean(res.getString(2),res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9));
					rooms.add(room);
				}
		res.close();
		return rooms;
			
		}finally {
            if (conn != null) {
            	conn.close();
            	
            }
            if (stmt != null ) {
            	stmt.close();
            	
            }
		}
            
		
	}
}
