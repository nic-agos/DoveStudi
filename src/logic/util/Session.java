package logic.util;

import logic.model.Person;
import logic.util.enumeration.Views;

/**
 * Astrazione del concetto di Sessione applicativa (implementazione Singleton)
 */
public class Session {
	
	private static Session instance = null;

	private Person currUser;
	private Views currView;
	private Views prevView;
	private boolean logged;
	
	private Session() {
		currUser = null;
		currView = Views.HOME;
		logged = false;
	}
	
	public static Session getSession() {
		if (instance == null) 
			instance = new Session();
			
		return instance;
	}
	
	public Person getCurrUser() {
		return currUser;
	}

	public Views getCurrView() {
		return currView;
	}
	
	public void setCurrUser(Person currUser) {
		this.currUser = currUser;
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
		if(!value) {
		currUser=null;
		currView = Views.HOME;
		}
	}

}
