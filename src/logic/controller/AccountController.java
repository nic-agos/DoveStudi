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
	/*
	 * public Person getAccountInfo(AccountBean accountBean){
	 * 
	 * }
	 */
}
