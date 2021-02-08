package logic.bean;

import logic.exception.AccountException;

public class PersonBean {
	
	private int id;
	
	private String username;
	
	private String studyGrade;
	
	private String school;
	
	private String account;
	
	private double hostRating;
	
	private double guestRating;
	
	public PersonBean() {
		
	}
	
	public PersonBean(String username, String studyGrade, String school, String account, double hostRating, double guestRating) {
		
		this.username = username;
		
		this.studyGrade = studyGrade;
		
		this.school = school;
		
		this.account = account;
		
		this.hostRating = hostRating;
		
		this.guestRating = guestRating;
		
	}
	
	public PersonBean(int id, String username, String studyGrade, String school, String account, double hostRating, double guestRating) {
		
		this(username, studyGrade, school, account, hostRating, guestRating);
		
		this.id = id;
		
	}
	
	public void setId(int id) {
		this.id = id;
		
	}
	
	public int getId() {
		return this.id;
		
	}
	
	public void setUsername(String username) {
		this.username = username;	
		
	}
	
	public String getUsername() {
		return this.username;
		
	}
	
	public void setStudyGrade(String studyGrade) {
		this.studyGrade = studyGrade;
		
	}
	
	public String getStudyGrade() {
		return this.studyGrade;
		
	}
	
	public void setSchool (String school) {
		this.school = school;
		
	}
	
	public String getSchool () {
		return this.school;
		
	}
	
	public void setAccount(String account) {
		this.account = account;
		
	}
	
	public String getAccount() {
		return this.account;
		
	}
	
	public void setHostRating(double hostRating) {
		this.hostRating = hostRating;
		
	}
	
	public double getHostRating() {
		return this.hostRating;
		
	}
	
	public void setGuestRating(double guestRating) {
		this.guestRating = guestRating;
		
	}
	
	public double getGuestRating() {
		return this.guestRating;
		
	}
	
	public void validate() throws AccountException {
		
		String errors = "";
		
		if(this.username.length() > 15) {
			errors = errors + "Invalid Username   ";
		}
		if(this.studyGrade.length() > 30 || this.studyGrade.isBlank()) {
			errors = errors + "Invalid Study Grade   ";
		}
		if(this.school.length() > 50 || this.school.isBlank()) {
			errors = errors + "Invalid School   ";
		}
		
		if(!errors.isEmpty()) {
			
			throw new AccountException(errors);
		}
	}
}
