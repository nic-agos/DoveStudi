package logic.bean;

import java.lang.Character.*;

public class AccountBean {

	private String cf;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private String dateBirth;
	
	private int numberToken;
	
	public AccountBean() {
		
	}
	
	public AccountBean(String cf, String name, String surname, String email, String password, String dateBirth, int numberToken) {
		
		this.cf = cf;
		
		this.name = name;
		
		this.surname = surname;
		
		this.email = email;
		
		this.password = password;
		
		this.dateBirth = dateBirth;
		
		this.numberToken = numberToken;
		
	}
	
	public boolean setCf(String cf) {
		
		if (cf.length() == 16) {
			this.cf = cf;
			return true;
		}else {
			return false;
		}
		
	}
	
	public String getCf() {
		return this.cf;
		
	}
	
	public boolean setName(String name) {
		
		if (name.length() <= 20) {
			this.name = name;
			return true;
		}else {
			return false;
		}
		
	}
	
	public String getName() {
		return this.name;
		
	}
	
	public boolean setSurname(String surname) {
		
		if (surname.length() <= 30) {
			this.surname = surname;
			return true;
		}else {
			return false;
		}
		
	}
	
	public String getSurname() {
		return this.surname;
		
	}
	
	public boolean setEmail(String email) {
		
		if (email.length() <= 100) {
			this.email = email;
			return true;
		}else {
			return false;
		}
		
	}
	
	public String getEmail() {
		return this.email;
		
	}
	
	public boolean setPassword(String password) {
		if (password.length() <= 20) {
			this.password = password;
			return true;
		}else {
			return false;
		}
		
	}
	
	public String getPassword() {
		return this.password;
		
	}
	
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
		
	}
	
	public String getDateBirth() {
		return this.dateBirth;
		
	}
	
	public void setNumberToken(int numberToken) {
		this.numberToken = numberToken;
		
	}
	
	public int getNumberToken() {
		return this.numberToken;
		
	}
	
/*	private boolean checkDate(String date) {
		int i;
		char c;
		if (date.length() == 10) {
			for(i = 0; i < 4; i++) {
				c = date.charAt(i);
				if(!Character.isDigit(c)) {
					return false;
				}
			}
			if (date.charAt(4) != ':' && date.charAt(4) != '-') {
				return false;
			}
			for (i = 5; i < 7; i++) {
				c = date.charAt(i);
				if(!Character.isDigit(c)) {
					return false;
				}
			}
			if (date.charAt(7) != ':' && date.charAt(7) != '-') {
				return false;
			}
			for(i = 8; i < 10; i++) {
				c = date.charAt(i);
				if(!Character.isDigit(c)) {
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
	}
*/
}
