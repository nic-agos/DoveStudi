package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import logic.bean.ReviewBean;
import logic.model.dao.ReviewDAOImpl;

public class TestReviewDAOImpl {
	
	private int id = 10;
	
	private String title = "bel";
	
	private String reviewer = "marco";
	
	private int reviewed = 13;
	
	private int rating = 4;
	
	private String description = "bella";
	
	private String tag = "guest";
	
	@Test
	public void testCreateReview() throws SQLException {
		ReviewDAOImpl r = new ReviewDAOImpl();
		ReviewBean a = new ReviewBean(title, reviewer, reviewed, rating, description, tag);
		int res = r.createReview(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testRemoveReview() throws SQLException {
		ReviewDAOImpl r = new ReviewDAOImpl();
		ReviewBean a = new ReviewBean(id, title, reviewer, reviewed, rating, description, tag);
		int res = r.removeReview(a);
		assertEquals(res, 1);
	}
}
