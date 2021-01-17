package logic.bean;

import java.lang.Character.*;

import logic.exception.RegistrationException;
import logic.exception.TriggerExceptions;

public class AccountBean {

	private TriggerExceptions trigger = new TriggerExceptions();
	
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
	
	public void setCf(String cf) {
		
		this.cf = cf;
		
	}
	
	public String getCf() {
		return this.cf;
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
		
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
		
	}
	
	public String getSurname() {
		return this.surname;
		
	}
	
	public void setEmail(String email) {
		this.email = email;
		
	}
	
	public String getEmail() {
		return this.email;
		
	}
	
	public void setPassword(String password) {
		this.password = password;
		
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
	
	public boolean validate() throws RegistrationException {
		String errors = "";
		if(this.cf.length() != 16) {
			errors = errors + "Invalid Fiscal Code   ";
		}
		if(this.name.length() > 20 || this.name.isBlank()) {
			errors = errors + "Invalid Name   ";
		}
		if(this.surname.length() > 30 || this.surname.isBlank()) {
			errors = errors + "Invalid Surname   ";
		}
		if(this.email.length() > 100 || this.email.isBlank()) {
			errors = errors + "Invalid Email   ";
		}
		if(this.password.length() > 20 || this.password.isBlank() ) {
			errors = errors + "Invalid Password   ";
		}
		if (this.dateBirth.length() != 10 || this.password.isBlank()) {
			errors = errors + "Invalid Date Birth   ";
		}
		
		if(!errors.isEmpty()) {
			this.trigger.triggerRegistrationException(errors);
			return false;
		}
		return true;
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
