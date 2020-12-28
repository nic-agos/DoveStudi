package logic.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.bean.RoomBean;

public class RoomDAOImpl implements RoomDAO {

	public int createRoom(RoomBean roomBean) throws Exception{
		
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			
			ResultSet res = stmt.executeQuery("SELECT * FROM room");
			
			while (res.next()) {
				System.out.printf("%i, %s, %s, %s, %i, %i, %s, %s, %s", res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getString(7), res.getString(8));
			}
			
			
//			int i = DAOQueries.insertRoom(connection, roomBean);
			if (res != null) {
				return 1;
			}else {
				return 0;
			}
		
		}finally {
			if (connection != null) {
				connection.close();
			}
			
		}
	}
	
	public int readRooms() throws Exception {
		
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			boolean result = DAOQueries.printRoomList(connection);

// 			this lines are for the test case TestReadRoomsDao
			if (result) {
				return 1;
			}else {
				return 0;
			}
		
		}finally {
			if (connection != null) {
				connection.close();
			}
			
		}
		
	}
	
	public List<RoomBean> getAllRooms() throws Exception{
		Connection connection = null;
		List<RoomBean> roomsList = new ArrayList<>();
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			roomsList = DAOQueries.getRoomList(connection);
			
			return roomsList;	
		
		}finally {
			if (connection != null) {
				connection.close();
			}
		
		}
	}
		
}
