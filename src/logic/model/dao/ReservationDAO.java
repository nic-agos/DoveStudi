package logic.model.dao;

import java.sql.SQLException;
import java.util.List;

import logic.bean.*;

public interface ReservationDAO {

	public int createReservation(ReservationBean reservationBean) throws SQLException;
	
	public int removeReservation(ReservationBean reservationBean) throws SQLException;
	
	public int getReservationId(ReservationBean reservationBean) throws SQLException;
	
	public List<ReservationBean> getAllAccountReservations(AccountBean accountBean) throws SQLException;
	
	public List<ReservationBean> getAllReservations() throws SQLException;
	
	public ReservationBean getReservation(int id) throws SQLException;
	
	public List<ReservationBean> getRoomReservations(RoomBean roomBean) throws SQLException;
	
}
