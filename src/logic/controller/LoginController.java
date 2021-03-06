package logic.controller;

import java.sql.SQLException;

import logic.bean.*;
import logic.model.dao.*;
import logic.exception.*;
import logic.model.*;


public class LoginController {

//	implemented with singleton pattern
	private static LoginController instance = null;
	
	private LoginController() {
		
	}
	
	public static synchronized LoginController getInstance() {
		if(LoginController.instance == null) {
			LoginController.instance = new LoginController();
		}
		return instance;
	}
	
//  takes in input email and password and return the Person entity (with Account entity linked)
//	if the login is successful	
	public Person login(AccountBean accountBean) throws DatabaseException, LoginException {
		
		try {
			AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
			AccountBean res = accountDao.login(accountBean);
			
			if(res != null) {
				
				Account account = new Account(res);
				PersonDAOImpl personDao = PersonDAOImpl.getInstance();
				AccountBean temp1 = new AccountBean();
				temp1.setCf(account.getCf());
				
//				getting Person's info from db
				PersonBean temp2 = personDao.getPersonFromAccount(temp1);
				Person person = new Person(temp2);
				
				person.setAccount(account);
				return person;
				
			}else {
				throw new LoginException("Wrong Email and/or Password");
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}	
	}
}
