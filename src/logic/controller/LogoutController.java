package logic.controller;

import logic.bean.*;
import logic.model.*;

public class LogoutController {

//	implemented with singleton pattern
	private static LogoutController instance = null;
	
	private LogoutController() {
		
	}
	
	public static synchronized LogoutController getInstance() {
		if(LogoutController.instance == null) {
			LogoutController.instance = new LogoutController();
		}
		return instance;
	}
	
	/*
	 * public boolean logout() {
	 * 
	 * }
	 */
}
