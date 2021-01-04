package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import logic.bean.*;
import logic.model.dao.*;

public class TestGroupDAOImpl {
	
	private int id = 10;
	private String name = "bel gruppo";
	private String admin = "aaaa";
	private int numPartecipants = 4;
	private int partecipant = 21;
	
	@Test
	public void testCreateGroup() throws SQLException {
		GroupDAOImpl g = new GroupDAOImpl();
		GroupBean b = new GroupBean(name, admin, numPartecipants, partecipant);
		int res = g.createGroup(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveGroup() throws SQLException {
		GroupDAOImpl g = new GroupDAOImpl();
		GroupBean b = new GroupBean(name, admin, numPartecipants, partecipant);
		int res = g.removeGroup(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetGroupId() throws SQLException {
		GroupDAOImpl g = new GroupDAOImpl();
		GroupBean b = new GroupBean(id, name, admin, numPartecipants, partecipant);
		int res = g.getGroupId(b);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAllGroups() throws SQLException {
		GroupDAOImpl g = new GroupDAOImpl();
		List<GroupBean> l = g.getAllGroups();
		assertEquals(l, 1);
	}
	
	@Test
	public void testGetAllAdministeredGroups() throws SQLException {
		GroupDAOImpl g = new GroupDAOImpl();
		AccountBean a = new AccountBean();
		a.setCf("bbbb");
		List<GroupBean> l = g.getAllAdministeredGroups(a);
		assertEquals(l, 1);
	}
	
	@Test
	public void testGetAllParticipatingGroups() throws SQLException {
		GroupDAOImpl g = new GroupDAOImpl();
		PersonBean p = new PersonBean();
		p.setId(21);
		List<GroupBean> l = g.getAllParticipatingGroups(p);
		assertEquals(l, 1);
	}
	
	@Test
	public void testAddGroupPartecipant() throws SQLException {
		GroupDAOImpl g = new GroupDAOImpl();
		GroupBean gr = new GroupBean("bello", "bbbb", 6, 23);
		g.addGroupPartecipant(gr);
	}
	
	
	
}
