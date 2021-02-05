package logic.model;

import logic.bean.*;

public class Account {

	private String cf;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private String dateBirth;
	
	private int numberToken;
	
	private Person person;
	
	public Account(String cf, String name, String surname, String email, String password, String dateBirth, int numberToken) {
		
		this.cf = cf;
		
		this.name = name;
		
		this.surname = surname;
		
		this.email = email;
		
		this.password = password;
		
		this.dateBirth = dateBirth;
		
		this.numberToken = numberToken;
		
	}
	
	public Account(AccountBean accountBean) {
		
		this(accountBean.getCf(), accountBean.getName(), accountBean.getSurname(), accountBean.getEmail(), 
				accountBean.getPassword(), accountBean.getDateBirth(), accountBean.getNumberToken());
			
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
	
	public void setPerson(Person person) {
		this.person = person;
		
	}
	
	public Person getPerson() {
		return this.person;
		
	}

}
