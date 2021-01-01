package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import logic.model.dao.*;
import logic.bean.*;

public class TestAccountDAOImpl {
	
	private String cf = "mrro";
	private String name = "marco";
	private String surname = "rossi";
	private String email = "m@";
	private String password = "m";
	private String dateBirth = "2020:02:07";
	private String cityBirth = "m";
	private int numToken = 10;
	
	@Test
	public void testCreateAccount() throws SQLException {
		AccountBean a = new AccountBean(cf, name, surname, email, password, dateBirth, cityBirth, numToken);
		AccountDAOImpl i = new AccountDAOImpl();
		int res = i.createAccount(a);
		assertEquals(res,1);
		
	}
	
	@Test
	public void testGetNumberToken() throws SQLException {
		AccountBean a = new AccountBean(cf, name, surname, email, password, dateBirth, cityBirth, numToken);
		AccountDAOImpl i = new AccountDAOImpl();
		int res = i.getNumberToken(a);
		assertEquals(res, 10);
	}
	
	@Test
	public void testUpdateNumberToken() throws SQLException{
		AccountBean a = new AccountBean(cf, name, surname, email, password, dateBirth, cityBirth, numToken);
		AccountDAOImpl i = new AccountDAOImpl();
		int res = i.updateNumberToken(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveAccount() throws SQLException{
		AccountBean a = new AccountBean(cf, name, surname, email, password, dateBirth, cityBirth, numToken);
		AccountDAOImpl i = new AccountDAOImpl();
		int res = i.removeAccount(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAllAccounts() throws SQLException {
		AccountDAOImpl dao = new AccountDAOImpl();
		List<AccountBean> res = dao.getAllAccounts();
		System.out.println(res.get(1).getDateBirth());
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAccount() throws SQLException {
		AccountDAOImpl a = new AccountDAOImpl();
		
		AccountBean res = a.getAccount("marco");
		System.out.println(res.getEmail());
		assertEquals(res, 1);
	}
	
	@Test
	public void testPrintAccounts() throws SQLException {
		Printers r = new Printers();
		r.printAccounts();
	}

}
