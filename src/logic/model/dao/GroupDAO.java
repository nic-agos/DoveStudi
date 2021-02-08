package logic.model.dao;

import java.sql.SQLException;
import java.util.List;

import logic.bean.*;

public interface GroupDAO {
	
	public int createGroup(GroupBean groupBean) throws SQLException;
	
	public int removeGroup(GroupBean groupBean) throws SQLException;
	
	public int getGroupId(GroupBean groupBean) throws SQLException;
	
	public List<GroupBean> getAllGroups() throws SQLException;
	
	public List<GroupBean> getAllAdministeredGroups(AccountBean accountBean) throws SQLException;
	
	public List<GroupBean> getAllParticipatingGroups(PersonBean personBean) throws SQLException;
	
	public int addGroupParticipant(GroupBean groupBean) throws SQLException;
	
	public int updateNumParticipantsGroup(GroupBean groupBean) throws SQLException;
	
	public GroupBean getAdministeredGroup(GroupBean groupBean) throws SQLException;
	
	public int leaveGroup(GroupBean groupBean) throws SQLException;

}
