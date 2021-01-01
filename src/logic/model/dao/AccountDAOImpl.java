package logic.model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;

public class AccountDAOImpl implements AccountDAO {
	
	private static final String CREATE_QUERY = "INSERT INTO account (CF, Name, Surname, Email, Password, Date_Birth, City_Birth, Number_Token) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_QUERY = "DELETE FROM account WHERE CF = ?";
	private static final String GET_TOKEN_QUERY = "SELECT Number_Token FROM account WHERE CF = ?";
	private static final String UPDATE_TOKEN_QUERY = "UPDATE account SET Number_Token = ? WHERE CF = ?";
	private static final String GETALL_ACCOUNTS_QUERY = "SELECT * FROM account";
	private static final String GET_ACCOUNT_QUERY = "SELECT * FROM account WHERE CF = ?";
	
	public int createAccount(AccountBean accountBean) throws SQLException {
		
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
			
			return stmt.executeUpdate();
			
				
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public int removeAccount(AccountBean accountBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_QUERY);
			stmt.setString(1, accountBean.getCF());
			
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
	
	public int getNumberToken(AccountBean accountBean) throws SQLException {
	
		PreparedStatement stmt = null;
		Connection connection = null;
		int tokens = 0;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_TOKEN_QUERY);
			stmt.setString(1, accountBean.getCF());
			
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				tokens = res.getInt(1);
			}
			
			res.close();
			
			return tokens;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
		
	}
	
	public int updateNumberToken(AccountBean accountBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(UPDATE_TOKEN_QUERY);
			stmt.setInt(1, accountBean.getNumberToken());
			stmt.setString(2, accountBean.getCF());
			
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

	
	public List<AccountBean> getAllAccounts() throws SQLException {
		
		List<AccountBean> accountsList = new ArrayList<>();
		AccountBean account = null;
	
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GETALL_ACCOUNTS_QUERY);
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				account = new AccountBean(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getInt(8));
				accountsList.add(account);
			}
			
			res.close();
			
			return accountsList;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	public AccountBean getAccount(String cf) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		AccountBean account = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ACCOUNT_QUERY);
			stmt.setString(1, cf);
			
			ResultSet res = stmt.executeQuery();
			
			while(res.next()) {
				account = new AccountBean(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getInt(8));
			}
			
			res.close();
			
			return account;
			
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
