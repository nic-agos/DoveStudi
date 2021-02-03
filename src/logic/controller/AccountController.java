package logic.controller;

import java.sql.SQLException;

import logic.bean.*;
import logic.model.dao.*;
import logic.exception.*;
import logic.model.*;

public class AccountController {

//	implemented with singleton pattern
	private static AccountController instance = null;
	
	private AccountController() {
		
	}
	
	public static synchronized AccountController getInstance() {
		if(AccountController.instance == null) {
			AccountController.instance = new AccountController();
		}
		return instance;
	}

//	takes in input the cf of the user and returns the Person entity (with Account entity linked)
	public Person getAccountInfo(AccountBean accountBean) throws DatabaseException{
		
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		Person person;
		PersonBean personBean = new PersonBean();
		Account account;
		AccountBean accBean = new AccountBean();
		
		try {
			personBean = personDao.getPersonFromAccount(accountBean);
			person = new Person(personBean);
			accBean = accountDao.getAccount(accountBean);
			account = new Account(accBean);
			person.setAccount(account);
			return person;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
		
	}

//	takes in input the username of the user and returns the Person entity (with Account entity linked)
	public Person getOtherAccountInfo(PersonBean personBean) throws DatabaseException {
		
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		Person person;
		PersonBean persBean = new PersonBean();
		Account account;
		AccountBean accountBean;
		AccountBean tempAccountBean = new AccountBean();
		
		try {
			persBean = personDao.getPersonByUsername(personBean);
			person = new Person(persBean);
			tempAccountBean.setCf(persBean.getAccount());
			accountBean = accountDao.getAccount(tempAccountBean);
			account = new Account(accountBean);
			person.setAccount(account);
			return person;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}
	 
}
