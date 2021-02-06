package logic.util;

import logic.util.enumeration.Views;

/**
 * Astrazione del concetto di Sessione applicativa (implementazione Singleton)
 */
public class Session {
	
	private static Session instance = null;

	private String currUsername;
	private Views currView;
	private Views prevView;
	private boolean logged;
	
	private Session() {
		currUsername = "";
		//currView = Views.LOGIN;
		logged = false;
	}
	
	public static Session getSession() {
		if (instance == null) 
			instance = new Session();
			
		return instance;
	}
	
	public String getCurrUser() {
		return currUsername;
	}

	public Views getCurrView() {
		return currView;
	}
	
	public void setCurrUser(String currUser) {
		this.currUsername = currUser;
	}

	public void setCurrView(Views nextView) {
		this.prevView = this.currView;
		this.currView = nextView;
	}
	
	public Views getPrevView() {
		return prevView;
	}
	
	public boolean isLogged() {
		return logged;
	}
	
	public void setLogged(boolean value) {
		logged=value;
		if(value==false) {
		currUsername = "";
		//currView = Views.LOGIN;
		}
	}

}
