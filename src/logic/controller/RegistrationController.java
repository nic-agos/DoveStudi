package logic.controller;

import java.sql.SQLException;

import logic.bean.*;
import logic.model.dao.*;
import logic.exception.*;

public class RegistrationController {

//	implemented with singleton pattern
	private static RegistrationController instance = null;
	
	private RegistrationController() {
		
	}
	
	public static synchronized RegistrationController getInstance() {
		if(RegistrationController.instance == null) {
			RegistrationController.instance = new RegistrationController();
		}
		return instance;
	}

//	takes in input a complete AccountBean and complete PersonBean
	public boolean register(AccountBean accountBean, PersonBean personBean) throws DatabaseException {
		
		try {
			AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
			accountBean.setNumberToken(5);

//			create a new Account on db
			accountDao.createAccount(accountBean);
			
			personBean.setAccount(accountBean.getCf());
			PersonDAOImpl personDao = PersonDAOImpl.getInstance();
			personBean.setHostRating(0);
			personBean.setGuestRating(0);

//			create a new Person on db
			int res = personDao.createPerson(personBean);
			
			return (res != 0);
		
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}
}
