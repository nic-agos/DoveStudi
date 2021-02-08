package logic.model.dao;

import java.sql.SQLException;
import java.util.List;

import logic.bean.*;

public interface RoomSpecDAO {
	
	public int createRoomSpec(RoomSpecBean roomSpecBean) throws SQLException;
	
	public int getRoomSpecId(RoomSpecBean roomSpecBean) throws SQLException;
	
	public int removeRoomSpec(RoomSpecBean roomSpecBean) throws SQLException;
	
	public RoomSpecBean getRoomSpec(RoomBean roomBean) throws SQLException;
	
	public List<RoomSpecBean> getRoomsSpecByCap(RoomSpecBean roomSpecBean) throws SQLException;
	
	public List<RoomSpecBean> getOrderedRoomsSpec() throws SQLException;
	
	public List<RoomSpecBean> getRoomsSpecByDate(RoomSpecBean roomSpecBean) throws SQLException;
	
	public List<RoomSpecBean> getAllRoomsSpec() throws SQLException;
	
}
