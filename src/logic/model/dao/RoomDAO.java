package logic.model.dao;

import java.sql.SQLException;
import java.util.List;

import logic.bean.AccountBean;
import logic.bean.RoomBean;
import logic.bean.RoomSpecBean;

public interface RoomDAO {
	
	public int createRoom(RoomBean roomBean) throws SQLException;
	
	public List<RoomBean> getAllRooms() throws SQLException;
	
	public int updateRoom(RoomBean roomBean) throws SQLException;
	
	public int removeRoom(RoomBean roomBean) throws SQLException;

	public RoomBean getRoom(RoomBean roomBean) throws SQLException;
	
	public int getRoomId(RoomBean roomBean) throws SQLException;
	
	public List<RoomBean> getAllAccountRooms(AccountBean accountBean) throws SQLException;
	
	public List<RoomBean> getRoomFilteredByAvailableSeats(RoomBean roomBean) throws SQLException;
	
	public RoomBean getRoomFromSpec(RoomSpecBean roomSpecBean) throws SQLException;

}
