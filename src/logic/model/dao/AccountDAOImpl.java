package logic.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;

public class AccountDAOImpl {
	
	private static final String CREATE_QUERY = "INSERT INTO account (CF, Name, Surname, Email, Password, Date_Birth, City_Birth, Number_Token) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_QUERY = "DELETE FROM account WHERE CF = ?";
	private static final String GET_TOKEN_QUERY = "SELECT Number_Token FROM account WHERE CF = ?";
	private static final String UPDATE_TOKEN_QUERY = "UPDATE account SET Number_Token = ? WHERE CF = ?";
	
	
	
	public int createAccount(AccountBean accountBean) throws Exception, SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(CREATE_QUERY);
			stmt.setString(1, accountBean.getCF());
			stmt.setString(2, accountBean.getName());
			stmt.setString(3, accountBean.getSurname());
			stmt.setString(4, accountBean.getEmail());
			stmt.setString(5, accountBean.getPassword());
			stmt.setString(6, accountBean.getDateBirth());
			stmt.setString(7, accountBean.getCityBirth());
			stmt.setInt(8, accountBean.getNumberToken());
			
			int res = stmt.executeUpdate();
			
			return res;
				
		}finally {
			if (connection != null) {
				connection.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public int removeAccount(AccountBean accountBean) throws Exception, SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_QUERY);
			stmt.setString(1, accountBean.getCF());
			
			int res = stmt.executeUpdate();
			
			return res;
			
		}finally {
			if (connection != null ) {
				connection.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
	}
	
	public int getNumberToken(AccountBean accountBean) throws Exception, SQLException {
	
		PreparedStatement stmt = null;
		Connection connection = null;
		int tokens = 0;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_TOKEN_QUERY);
			stmt.setString(1, accountBean.getCF());
			
			ResultSet r = stmt.executeQuery();
			while (r.next()) {
				tokens = r.getInt(1);
			}
			return tokens;
			
		}finally {
			if (connection != null ) {
				connection.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
	}
	
	public int updateNumberToken(AccountBean accountBean) throws Exception, SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(UPDATE_TOKEN_QUERY);
			stmt.setInt(1, accountBean.getNumberToken());
			stmt.setString(2, accountBean.getCF());
			
			int res = stmt.executeUpdate();
			return res;
			
		}finally {
			if (connection != null ) {
				connection.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public List<RoomBean> getAllAccountRooms(AccountBean Account) throws Exception, SQLException {
		
		List<RoomBean> accountRooms = new ArrayList<>();
		
		return accountRooms;
	}
	
	public List<ReservationBean> getAllAccountReservations(AccountBean Account) throws Exception, SQLException {
		
		List<ReservationBean> accountReservations = new ArrayList<>();
		
		return accountReservations;
	}
	
	public List<AccountBean> getAllAccounts() throws Exception, SQLException {
		
		List<AccountBean> accountsList = new ArrayList<>();
		
		return accountsList;
	}

}
