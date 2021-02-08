package logic.model.dao;

import java.sql.SQLException;
import java.util.List;

import logic.bean.*;

public interface PersonDAO {
	
	public int createPerson(PersonBean personBean) throws SQLException;
	
	public int removePerson(PersonBean personBean) throws SQLException;
	
	public int getPersonId(PersonBean personBean) throws SQLException;
	
	public List<PersonBean> getAllPersons() throws SQLException;
	
	public int updateGuestRating(PersonBean personBean) throws SQLException;
	
	public int updateHostRating(PersonBean personBean) throws SQLException;
	
	public PersonBean getPerson(PersonBean personBean) throws SQLException;
	
	public PersonBean getPersonFromAccount(AccountBean accountBean) throws SQLException;
	
	public List<PersonBean> getGroupParticipants(GroupBean groupBean) throws SQLException;
	
	public PersonBean getPersonByUsername(PersonBean personBean) throws SQLException;
}
