package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;

public class GroupDAOImpl {

	private static final String CREATE_GROUP_QUERY = "INSERT INTO group (Name, Admin, Num_Partecipants, Partecipant) VALUES (?, ?, ?, ?)";
	private static final String DELETE_GROUP_QUERY = "DELETE FROM group WHERE Name = AND Admin = ?";
	private static final String GET_GROUP_ID_QUERY = "SELECT ID FROM group WHERE Name = ? AND Admin = ?";
	private static final String GET_ALL_GROUPS_QUERY = "SELECT * FROM group";
	private static final String GET_ADMIN_GROUPS_QUERY = "SELECT * FROM group where Admin = ?";
	private static final String GET_PARTECPANT_GROUPS_QUERY = "SELECT * FROM group where Partecipant = ?";
	private static final String ADD_GROUP_PARTECIPANT_QUERY = "INSERT INTO group (Name, Admin, Num_Partecipants, Partecipant) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_NUM_PARTECIPANTS_GROUP = "UPDATE group SET Num_Partecipant = ? WHERE Name = ? AND Admin = ?";

// 	the idea is that Admin creates a group with only himself as partecipant and after he adds partecipants
//	with the function addGroupPartecipant() that takes in input a PersonBean
	
	public int createGroup(GroupBean groupBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(CREATE_GROUP_QUERY);
			stmt.setString(1, groupBean.getName());
			stmt.setString(2, groupBean.getAdmin());
			stmt.setInt(3, groupBean.getNumPartecipants());
			stmt.setInt(4, groupBean.getPartecipant());
			
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
	
	public int removeGroup(GroupBean groupBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_GROUP_QUERY);
			stmt.setInt(1, groupBean.getId());
			
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
	
	public int getGroupId(GroupBean groupBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		int id = 0;		
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_GROUP_ID_QUERY);
			stmt.setString(1, groupBean.getName());
			stmt.setString(2, groupBean.getAdmin());
			
			ResultSet r = stmt.executeQuery();
			
			while (r.next()) {
				id = r.getInt(1);
			}
			
			return id;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	public List<GroupBean> getAllGroups() throws SQLException {
		
		List<GroupBean> groupsList = new ArrayList<>();
		GroupBean group = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ALL_GROUPS_QUERY);
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					group = new GroupBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));
					groupsList.add(group);
				}
				
			res.close();
			
			return groupsList;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	public List<GroupBean> getAllAdminGroups(GroupBean groupBean) throws SQLException {
		
		List<GroupBean> adminGroups = new ArrayList<>();
		GroupBean group = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ADMIN_GROUPS_QUERY);
			stmt.setString(1, groupBean.getAdmin());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					group = new GroupBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));
					adminGroups.add(group);
				}
				
			res.close();
			
			return adminGroups;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	public List<GroupBean> getAllPartecipantGroups(GroupBean groupBean) throws SQLException {
		
		List<GroupBean> partecipantGroups = new ArrayList<>();
		GroupBean group = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_PARTECPANT_GROUPS_QUERY);
			stmt.setInt(1, groupBean.getPartecipant());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					group = new GroupBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));
					partecipantGroups.add(group);
				}
				
			res.close();
			
			return partecipantGroups;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	public int addGroupPartecipant(GroupBean groupBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(ADD_GROUP_PARTECIPANT_QUERY);
			stmt.setString(1, groupBean.getName());
			stmt.setString(2, groupBean.getAdmin());
			stmt.setInt(3, groupBean.getNumPartecipants());
			stmt.setInt(4, groupBean.getPartecipant());
			
			updateNumPartecipantsGroup(groupBean);
			
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
	
	public int updateNumPartecipantsGroup(GroupBean groupBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(UPDATE_NUM_PARTECIPANTS_GROUP);
			stmt.setInt(1, groupBean.getNumPartecipants());
			stmt.setString(2, groupBean.getName());
			stmt.setString(3, groupBean.getAdmin());
			
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
}
