package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import java.util.List;

import logic.bean.AccountBean;
import logic.bean.GroupBean;
import logic.bean.PersonBean;
import logic.model.dao.PersonDAOImpl;

public class TestPersonDAOImpl {
	
	private int id = 15;
	private String username = "bello";
	private String studyGrade = "a";
	private String school = "a";
	private String account = "marco" ;
	private double hostRating = 0;
	private double guestRating = 0;
	
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
		PersonBean p = new PersonBean();
		p.setId(13);
		PersonBean res = d.getPerson(p);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetPersonFromAccount() throws SQLException {
		PersonDAOImpl d = new PersonDAOImpl();
		AccountBean a = new AccountBean("marco", "m", "a", "m", "2020-12-27", "m", 10);
		PersonBean res = d.getPersonFromAccount(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetGroupPartecipants() throws SQLException {
		PersonDAOImpl d = new PersonDAOImpl();
		GroupBean g = new GroupBean();
		g.setAdmin("bbbb");
		g.setName("bello");
		List<PersonBean> l = d.getGroupPartecipants(g);
		assertEquals(l, 1);
	}
	
	@Test
	public void testGetAllPersons() throws SQLException {
		PersonDAOImpl d = new PersonDAOImpl();
		List<PersonBean> p = d.getAllPersons();
		assertEquals(p, 1);
	}
}
