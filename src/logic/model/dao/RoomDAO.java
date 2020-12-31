package logic.model.dao;

import java.sql.SQLException;
import java.util.List;

import logic.bean.RoomBean;

public interface RoomDAO {
	
	public int createRoom(RoomBean roomBean) throws Exception, SQLException;
	
	public void printRooms() throws Exception;
	
	public List<RoomBean> getAllRooms() throws Exception, SQLException;
	
	public int updateRoom(RoomBean roomBean) throws Exception, SQLException;
	
	public int removeRoom(RoomBean roomBean) throws Exception, SQLException;

	public RoomBean getRoom(int id) throws Exception, SQLException;
	
	public int getRoomId(RoomBean roomBean) throws Exception, SQLException;
	
}
