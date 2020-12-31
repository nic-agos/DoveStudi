package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.model.dao.*;
import logic.bean.*;

public class TestAccountDAOImpl {
	
	@Test
	public void testCreateAccount() throws SQLException, Exception {
		AccountBean a = new AccountBean("mrro","mario","rossi", "m@", "m", "2020:2:7", "m", 10);
		AccountDAOImpl i = new AccountDAOImpl();
		int res = i.createAccount(a);
		assertEquals(res,1);
		
	}
	
	@Test
	public void testGetNumberToken() throws SQLException, Exception {
		AccountBean a = new AccountBean("mrro","mario","rossi", "m@", "m", "2020:2:7", "m", 10);
		AccountDAOImpl i = new AccountDAOImpl();
		int res = i.getNumberToken(a);
		assertEquals(res, 10);
	}
	
	@Test
	public void testUpdateNumberToken() throws SQLException, Exception {
		AccountBean a = new AccountBean("mrro","mario","rossi", "m@", "m", "2020:2:7", "m", 4);
		AccountDAOImpl i = new AccountDAOImpl();
		int res = i.updateNumberToken(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveAccount() throws SQLException, Exception{
		AccountBean a = new AccountBean("mrro","mario","rossi", "m@", "m", "2020:2:7", "m", 10);
		AccountDAOImpl i = new AccountDAOImpl();
		int res = i.removeAccount(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAllAccounts() throws SQLException, Exception {
		AccountDAOImpl dao = new AccountDAOImpl();
		List<AccountBean> res = dao.getAllAccounts();
		System.out.println(res.get(1).getDateBirth());
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAccount() throws SQLException, Exception {
		AccountDAOImpl a = new AccountDAOImpl();
		
		AccountBean res = a.getAccount("marco");
		System.out.println(res.getEmail());
		assertEquals(res, 1);
	}
	
	@Test
	public void testPrintAccounts() throws Exception {
		AccountDAOImpl r = new AccountDAOImpl();
		r.printAccounts();
	}

}
