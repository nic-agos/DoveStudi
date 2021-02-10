package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;
import logic.model.database.DBConnection;

public class GroupDAOImpl implements GroupDAO{

	private static final String CREATE_GROUP_QUERY = "INSERT INTO group_a (Name, Admin, Num_Partecipants, Partecipant) VALUES (?, ?, ?, ?)";
	private static final String DELETE_GROUP_QUERY = "DELETE FROM group_a WHERE Name = ? AND Admin = ?";
	private static final String GET_GROUP_ID_QUERY = "SELECT ID FROM group_a WHERE Name = ? AND Admin = ?";
	private static final String GET_ALL_GROUPS_QUERY = "SELECT * FROM group_a";
	private static final String GET_ALL_ADMINISTERED_GROUPS_QUERY = "SELECT * FROM group_a WHERE Admin = ? AND Partecipant = ?";
	private static final String GET_ADMINISTERED_GROUP_QUERY = "SELECT * FROM group_a WHERE Name = ? AND Admin = ? AND Partecipant = ?";
	private static final String GET_PARTICIPATING_GROUPS_QUERY = "SELECT * FROM group_a WHERE Partecipant = ?";
	private static final String ADD_GROUP_PARTICIPANT_QUERY = "INSERT INTO group_a (Name, Admin, Num_Partecipants, Partecipant) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_NUM_PARTICIPANTS_GROUP = "UPDATE group_a SET Num_Partecipants = ? WHERE Name = ? AND Admin = ?";
	private static final String LEAVE_GROUP_QUERY = "DELETE FROM group_a WHERE Name = ? AND Admin = ? AND Partecipant = ?";
	
	private static GroupDAOImpl instance = null;
	
	private GroupDAOImpl() {
		
	}
	
	public static synchronized GroupDAOImpl getInstance() {
		if(GroupDAOImpl.instance == null) {
			GroupDAOImpl.instance = new GroupDAOImpl();
		}
		return instance;
	}
	
	@Override
	public int createGroup(GroupBean groupBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(CREATE_GROUP_QUERY);
			stmt.setString(1, groupBean.getName());
			stmt.setString(2, groupBean.getAdmin());
			stmt.setInt(3, groupBean.getNumParticipants());
			stmt.setInt(4, groupBean.getParticipant());
			
			stmt.executeUpdate();
			
			return getGroupId(groupBean);
				
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	@Override
	public int removeGroup(GroupBean groupBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_GROUP_QUERY);
			stmt.setString(1, groupBean.getName());
			stmt.setString(2, groupBean.getAdmin());
			
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
	
	@Override
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
	
	@Override
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
	
	@Override
	public List<GroupBean> getAllAdministeredGroups(AccountBean accountBean) throws SQLException {
		
		List<GroupBean> adminGroupsList = new ArrayList<>();
		GroupBean group = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		PersonBean person = null;
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		try {
			person = personDao.getPersonFromAccount(accountBean);
			
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ALL_ADMINISTERED_GROUPS_QUERY);
			stmt.setString(1, accountBean.getCf());
			stmt.setInt(2, person.getId());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					group = new GroupBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));
					adminGroupsList.add(group);
				}
				
			res.close();
			
			return adminGroupsList;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}

//	takes in input group's name and admin and get the db row of the group's admin
	@Override
	public GroupBean getAdministeredGroup(GroupBean groupBean) throws SQLException {
		
		GroupBean adminGroup = null;
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		PersonBean personBean;
		AccountBean tempAccountBean = new AccountBean();
		
		try {
			tempAccountBean.setCf(groupBean.getAdmin());
			personBean = personDao.getPersonFromAccount(tempAccountBean);
			
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ADMINISTERED_GROUP_QUERY);
			stmt.setString(1, groupBean.getName());
			stmt.setString(2, groupBean.getAdmin());
			stmt.setInt(3, personBean.getId());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					adminGroup = new GroupBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));
				}
				
			res.close();
			
			return adminGroup;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	
//  it return a list with all groups a PersonBean participates in
//	in the returned list is included also the PersonBean linked to the admin of the group

	@Override
	public List<GroupBean> getAllParticipatingGroups(PersonBean personBean) throws SQLException {
		
		List<GroupBean> participantGroups = new ArrayList<>();
		GroupBean group = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_PARTICIPATING_GROUPS_QUERY);
			stmt.setInt(1, personBean.getId());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					group = new GroupBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5));
					participantGroups.add(group);
				}
				
			res.close();
			
			return participantGroups;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public int addGroupParticipant(GroupBean groupBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(ADD_GROUP_PARTICIPANT_QUERY);
			stmt.setString(1, groupBean.getName());
			stmt.setString(2, groupBean.getAdmin());
			stmt.setInt(3, groupBean.getNumParticipants());
			stmt.setInt(4, groupBean.getParticipant());
			
			stmt.executeUpdate();
			
			return updateNumParticipantsGroup(groupBean);
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	@Override
	public int updateNumParticipantsGroup(GroupBean groupBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(UPDATE_NUM_PARTICIPANTS_GROUP);
			stmt.setInt(1, groupBean.getNumParticipants());
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
	
	@Override
	public int leaveGroup(GroupBean groupBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(LEAVE_GROUP_QUERY);
			stmt.setString(1, groupBean.getName());
			stmt.setString(2, groupBean.getAdmin());
			stmt.setInt(3, groupBean.getParticipant());
			
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
