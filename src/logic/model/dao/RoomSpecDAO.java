package logic.model.dao;

import java.sql.SQLException;

import logic.bean.*;

public interface RoomSpecDAO {
	
	public int createRoomSpec(RoomSpecBean roomSpecBean) throws SQLException;
	
	public int getRoomSpecId(RoomSpecBean roomSpecBean) throws SQLException;
	
	public int removeRoomSpec(RoomSpecBean roomSpecBean) throws SQLException;
	
	public RoomSpecBean getRoomSpec(RoomBean roomBean) throws SQLException;
	
}
