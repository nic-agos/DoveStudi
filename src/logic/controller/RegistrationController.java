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
	
	public boolean register(AccountBean accountBean) {
		
		try {
			AccountDAOImpl dao = new AccountDAOImpl();
			dao.createAccount(accountBean);
			return true;
		
		}catch (SQLException se) {
			se.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
