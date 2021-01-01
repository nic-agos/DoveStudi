package logic.bean;

public class PersonBean {
	
	private int id;
	
	private String username;
	
	private String studyGrade;
	
	private String school;
	
	private String account;
	
	private float hostRating;
	
	private float guestRating;
	
	public PersonBean() {
		
	}
	
	public PersonBean(String username, String studyGrade, String school, String account, float hostRating, float guestRating) {
		
		this.username = username;
		
		this.studyGrade = studyGrade;
		
		this.school = school;
		
		this.account = account;
		
		this.hostRating = hostRating;
		
		this.guestRating = guestRating;
		
	}
	
	public PersonBean(int id, String username, String studyGrade, String school, String account, float hostRating, float guestRating) {
		
		this.id = id;
		
		this.username = username;
		
		this.studyGrade = studyGrade;
		
		this.school = school;
		
		this.account = account;
		
		this.hostRating = hostRating;
		
		this.guestRating = guestRating;
		
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
	
	public void setHostRating(float hostRating) {
		this.hostRating = hostRating;
		
	}
	
	public float getHostRating() {
		return this.hostRating;
		
	}
	
	public void setGuestRating(float guestRating) {
		this.guestRating = guestRating;
		
	}
	
	public float getGuestRating() {
		return this.guestRating;
		
	}
}
