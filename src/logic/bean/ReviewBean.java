package logic.bean;

import logic.exception.*;

public class ReviewBean {
	
	private int id;
	
	private String title;
	
	private String reviewer;
	
	private int reviewed;
	
	private int rating;
	
	private String description;

//	tag is used to to distinguish if the review is for the host or the guest
	private String tag;
	
	public ReviewBean() {
		
	}
	
	public ReviewBean(String title, String reviewer, int reviewed, int rating, String description, String tag) {
		
		this.title = title;
		
		this.reviewer = reviewer;
		
		this.reviewed = reviewed;
		
		this.rating = rating;
		
		this.description = description;
		
		this.tag = tag;
	}
	
	public ReviewBean(int id, String title, String reviewer, int reviewed, int rating, String description, String tag) {
		
		this(title, reviewer, reviewed, rating, description, tag);
		
		this.id = id;
		
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
	
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
		
	}
	
	public String getReviewer() {
		return this.reviewer;
		
	}
	
	public void setReviewed(int reviewed) {
		this.reviewed = reviewed;
		
	}
	
	public int getReviewed() {
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
	
	public String getDescription() {
		return this.description;
		
	}
	
	public void setTag(String tag) {
		this.tag = tag;
		
	}
	
	public String getTag() {
		return this.tag;
		
	}
	
	public void validate() throws ReviewException {
		
		String errors = "";
		
		if(this.title.length() > 45 || this.title.isBlank()) {
			errors = errors + "Invalid review title   ";
		}
		if(this.description.length() > 200 || this.description.isBlank()) {
			errors = errors + "Invalid review description   ";
		}
		if(this.tag.isBlank()) {
			errors = errors + "Invalid review tag   ";
		}
		if(this.rating != 0 && this.rating != 1 && this.rating != 2 && this.rating != 3 && this.rating != 4 && this.rating != 5) {
			errors = errors + "Invalid review rating   ";
		}
		
		if(!errors.isEmpty()) {
			
			throw new ReviewException(errors);
		}
	}
}
