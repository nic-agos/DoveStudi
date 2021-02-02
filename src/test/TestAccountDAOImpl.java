package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import logic.model.dao.*;
import logic.bean.*;

public class TestAccountDAOImpl {
	
	private String cf;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String dateBirth;
	private int numToken;
	
	@Test
	public void testCreateAccount() throws SQLException {
		AccountBean a = new AccountBean(cf, name, surname, email, password, dateBirth, numToken);
		AccountDAOImpl i = AccountDAOImpl.getInstance();
		int res = i.createAccount(a);
		assertEquals(res,1);
		
	}
	
	@Test
	public void testGetNumberToken() throws SQLException {
		AccountBean a = new AccountBean(cf, name, surname, email, password, dateBirth, numToken);
		AccountDAOImpl i = AccountDAOImpl.getInstance();
		int res = i.getNumberToken(a);
		assertEquals(res, 10);
	}
	
	@Test
	public void testUpdateNumberToken() throws SQLException{
		AccountBean a = new AccountBean(cf, name, surname, email, password, dateBirth, numToken);
		AccountDAOImpl i = AccountDAOImpl.getInstance();
		int res = i.updateNumberToken(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveAccount() throws SQLException{
		AccountBean a = new AccountBean(cf, name, surname, email, password, dateBirth, numToken);
		AccountDAOImpl i = AccountDAOImpl.getInstance();
		int res = i.removeAccount(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAllAccounts() throws SQLException {
		AccountDAOImpl i = AccountDAOImpl.getInstance();
		List<AccountBean> res = i.getAllAccounts();
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAccount() throws SQLException {
		AccountDAOImpl i = AccountDAOImpl.getInstance();
		AccountBean a = new AccountBean();
		a.setCf(cf);
		AccountBean res = i.getAccount(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testLogin() throws SQLException {
		AccountDAOImpl i = AccountDAOImpl.getInstance();
		AccountBean a = new AccountBean();
		a.setEmail(email);
		a.setPassword(password);
		AccountBean res = i.login(a);
		assertEquals(res, 1);
	}
}
