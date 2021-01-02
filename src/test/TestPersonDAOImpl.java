package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import logic.bean.AccountBean;
import logic.bean.PersonBean;
import logic.model.dao.PersonDAOImpl;

public class TestPersonDAOImpl {
	
	private int id = 15;
	
	private String username = "bello";
	
	private String studyGrade = "a";
	
	private String school = "a";
	
	private String account = "marco" ;
	
	private float hostRating = 0;
	
	private float guestRating = 0;
	
	@Test
	public void testCreatePerson() throws SQLException {
		PersonBean p = new PersonBean(username, studyGrade, school, account, hostRating, guestRating);
		PersonDAOImpl d = new PersonDAOImpl();
		int res = d.createPerson(p);
		assertEquals(res, 0);
	}
	
	@Test
	public void testUpdatePerson() throws SQLException {
		PersonBean p = new PersonBean(id, username, studyGrade, school, account, hostRating, guestRating);
		PersonDAOImpl d = new PersonDAOImpl();
		int res = d.updatePerson(p);
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemovePerson() throws SQLException {
		PersonBean p = new PersonBean(id, username, studyGrade, school, account, hostRating, guestRating);
		PersonDAOImpl d = new PersonDAOImpl();
		int res = d.removePerson(p);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetPerson() throws SQLException {
		PersonDAOImpl d = new PersonDAOImpl();
		PersonBean res = d.getPerson(13);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetPersonFromAccount() throws SQLException {
		PersonDAOImpl d = new PersonDAOImpl();
		AccountBean a = new AccountBean("marco", "m", "a", "m", "2020-12-27", "m", 10);
		PersonBean res = d.getPersonFromAccount(a);
		assertEquals(res, 1);
	}
}
