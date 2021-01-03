package logic.model;

import java.sql.SQLException;

import logic.bean.*;
import logic.model.dao.*;

public class Review {
	private int id;
	
	private String title;
	
	private Account reviewer;
	
	private Person reviewed;
	
	private int rating;
	
	private String description;
	
	private String tag;
	
	public Review(String title, int rating, String description, String tag) {
		
		this.title = title;
		
		this.rating = rating;
		
		this.description = description;
		
		this.tag = tag;
	}
	
	public Review(String title, Account reviewer, Person reviewed, int rating, String description, String tag) {
		
		this(title, rating, description, tag);
		
		this.reviewer = reviewer;
		
		this.reviewed = reviewed;
		
	}
	
	public Review(ReviewBean reviewBean) throws SQLException {
		this(reviewBean.getTitle(), reviewBean.getRating(), reviewBean.getDescritpion(), reviewBean.getTag());
		
		AccountDAOImpl dao1 = new AccountDAOImpl();
		
		this.reviewer = new Account(dao1.getAccount(reviewBean.getReviewer()));
		
		PersonDAOImpl dao2 = new PersonDAOImpl();
		
		this.reviewed = new Person(dao2.getPerson(reviewBean.getId()));
		
		this.id = reviewBean.getId();
		
	}
	
	public void setId(int id) {
		this.id  = id;
		
	}
	
	public int getId() {
		return this.id;
		
	}
	
	public void setTitle(String title) {
		this.title = title;
		
	}
	
	public String getTitle() {
		return this.title;
		
	}
	
	public void setReviewer(Account reviewer) {
		this.reviewer = reviewer;
		
	}
	
	public Account getReviewer() {
		return this.reviewer;
		
	}
	
	public void setReviewed(Person reviewed) {
		this.reviewed = reviewed;
		
	}
	
	public Person getReviewed() {
		return this.reviewed;
		
	}
	
	public void setRating(int rating) {
		this.rating = rating;
		
	}
	
	public int getRating() {
		return this.rating;
		
	}
	
	public void setDescription(String description) {
		this.description = description;
		
	}
	
	public String getDescritpion() {
		return this.description;
		
	}
	
	public void setTag(String tag) {
		this.tag = tag;
		
	}
	
	public String getTag() {
		return this.tag;
		
	}
}
