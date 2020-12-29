package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.model.dao.AccountDAOImpl;
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

}
