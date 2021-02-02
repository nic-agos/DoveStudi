package logic.controller;

public class ReviewController {

//	implemented with singleton pattern
	private static ReviewController instance = null;
	
	private ReviewController() {
		
	}
	
	public static synchronized ReviewController getInstance() {
		if(ReviewController.instance == null) {
			ReviewController.instance = new ReviewController();
		}
		return instance;
	}
	
	/*
	 * public boolean makeReview(ReviewBean reviewBean) { }
	 * 
	 * public List<Review> getReceivedReview() {
	 * 
	 * }
	 * 
	 * public List<Review> getDoneReview() {
	 * 
	 * }
	 */
}
