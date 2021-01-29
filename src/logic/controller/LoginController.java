package logic.controller;

import java.sql.SQLException;

import logic.bean.AccountBean;
import logic.model.dao.AccountDAOImpl;

public class LoginController {
	
	private static LoginController instance = null;
	
	private LoginController() {
		
	}
	
	public static synchronized LoginController getInstance() {
		if(LoginController.instance == null) {
			LoginController.instance = new LoginController();
		}
		return instance;
	}
	
	public boolean login(AccountBean accountBean) throws SQLException {
		
		try {
			AccountDAOImpl dao = new AccountDAOImpl();
			AccountBean res = dao.login(accountBean);
			if (res == null) {
				return false;
			}else {
				return true;
			}
			
		}catch (SQLException se) {
			throw se;
		}	
	}
}
