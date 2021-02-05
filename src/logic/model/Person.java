package logic.model;

import java.sql.SQLException;
import java.util.List;

import logic.bean.*;

public class Person {

	private int id;
	
	private String username;
	
	private String studyGrade;
	
	private String school;
	
	private Account account;
	
	private double hostRating;
	
	private double guestRating;
	
	public Person(String username, String studyGrade, String school, double hostRating, double guestRating) {
		
		this.username = username;
		
		this.studyGrade = studyGrade;
		
		this.school = school;
		
		this.hostRating = hostRating;
		
		this.guestRating = guestRating;
		
	}
	
	public Person(String username, String studyGrade, String school, Account account, double hostRating, double guestRating) {
		
		this(username, studyGrade, school, hostRating, guestRating);
		
		this.account = account;
		
	}
	
	public Person (PersonBean personBean) {
		this(personBean.getUsername(), personBean.getStudyGrade(), personBean.getSchool(), personBean.getHostRating(), personBean.getGuestRating());

		this.id = personBean.getId();
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
	
	public void setAccount(Account account) {
		this.account = account;
		
	}
	
	public Account getAccount() {
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
}
