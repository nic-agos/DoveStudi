package logic.bean;

public class ReviewBean {
	
	private int id;
	
	private String title;
	
	private String reviewer;
	
	private int reviewed;
	
	private int rating;
	
	private String description;
	
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
