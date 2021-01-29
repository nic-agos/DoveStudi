package logic.controller;

import java.sql.SQLException;

import logic.bean.*;
import logic.model.dao.*;

public class RegistrationController {

	private static RegistrationController instance = null;
	
	private RegistrationController() {
		
	}
	
	public static synchronized RegistrationController getInstance() {
		if(RegistrationController.instance == null) {
			RegistrationController.instance = new RegistrationController();
		}
		return instance;
	}
	
	public boolean registerAccount(AccountBean accountBean) throws SQLException {
		
		try {
			AccountDAOImpl dao = new AccountDAOImpl();
			accountBean.setNumberToken(5);
			int res = dao.createAccount(accountBean);
			if(res != 0) {
				return true;
			}else {
				return false;
			}
		
		}catch (SQLException se) {
			throw se;
		}
	}
	
	public int registerPerson(PersonBean personBean) throws SQLException {
		int res = 0;
		try {
			PersonDAOImpl dao = new PersonDAOImpl();
			personBean.setHostRating(0);
			personBean.setGuestRating(0);
			res = dao.createPerson(personBean);
		
		}catch (SQLException se) {
			throw se;
		}
		
		return res;
	}
}
