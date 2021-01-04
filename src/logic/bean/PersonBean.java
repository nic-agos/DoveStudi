package logic.bean;

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
	
	public boolean setUsername(String username) {
		if (username.length() <= 15) {
			this.username = username;
			return true;
		}else {
			return false;
		}	
		
	}
	
	public String getUsername() {
		return this.username;
		
	}
	
	public boolean setStudyGrade(String studyGrade) {
		if (studyGrade.length() <= 30) {
			this.studyGrade = studyGrade;
			return true;
		}else {
			return false;
		}
		
	}
	
	public String getStudyGrade() {
		return this.studyGrade;
		
	}
	
	public boolean setSchool (String school) {
		if (school.length() <= 50) {
			this.school = school;
			return true;
		}else {
			return false;
		}
		
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
}
