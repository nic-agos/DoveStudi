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
	
	private static final String GETALL_QUERY = "SELECT * FROM room";
	
	public static boolean printRoomList(Connection conn) throws SQLException{
		
		Statement stmt = null;
		
		try {
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);	
		ResultSet res = stmt.executeQuery(GETALL_QUERY);
		
		while (res.next()) {
			System.out.printf("%s, %s, %s, %s, %s, %s, %s,  %s, %s\n", res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getString(7), res.getString(8), res.getString(9));
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
	
	public static int getRoomId(Connection conn, RoomBean roomBean) throws SQLException {
		
		PreparedStatement stmt = null;
		String query = "SELECT ID FROM room WHERE Name = ? AND Address = ? AND Date = ?";
		int id = 0;		
		
		stmt = conn.prepareStatement(query);
		stmt.setString(1, roomBean.getName());
		stmt.setString(2, roomBean.getAddress());
		stmt.setString(3, roomBean.getDate());

		ResultSet r = stmt.executeQuery();
		while (r.next()) {
			id = r.getInt(1);
		}
		return id;
	}
	
    
}
