package logic.controller;

import logic.bean.AccountBean;
import logic.model.Person;

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

//	takes in input the username of the user and returns the Person entity (with Account entity linked)
	/*
	 * public Person getAccountInfo(AccountBean accountBean){
	 * 
	 * }
	 */
}
