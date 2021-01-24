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
	
	private List<Review> reviews;
	
	private List<Group> groups;
	
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
	
	public Person (PersonBean personBean) throws SQLException {
		this(personBean.getUsername(), personBean.getStudyGrade(), personBean.getSchool(), personBean.getHostRating(), personBean.getGuestRating());
/*		
		AccountDAOImpl dao1 = new AccountDAOImpl();
		
		this.account = new Account(dao1.getAccount(personBean.getAccount()));
		
		GroupDAOImpl dao2 = new GroupDAOImpl();
		
		List<GroupBean> groupBeans = dao2.getAllParticipatingGroups(personBean);
		if (!groupBeans.isEmpty()) {
			for(GroupBean groupBean : groupBeans) {
				this.groups.add(new Group(groupBean));
			}
		}
*	
		
		ReviewDAOImpl dao3 = new ReviewDAOImpl();
		
		List<ReviewBean> reviewBeans = dao3.getAllReceivedReviews(personBean);
		if(!reviewBeans.isEmpty()) {
			for(ReviewBean reviewBean : reviewBeans) {
				this.reviews.add(new Review(reviewBean));
			}
		}
*/		
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
	
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
		
	}
	
	public List<Review> getReviews(){
		return this.reviews;
		
	}
	
	public void setGroups(List<Group> groups) {
		this.groups = groups;
		
	}
	
	public List<Group> getGroups(){
		return this.groups;
		
	}
}
