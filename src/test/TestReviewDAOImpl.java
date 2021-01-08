package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import logic.bean.AccountBean;
import logic.bean.PersonBean;
import logic.bean.ReviewBean;
import logic.model.dao.ReviewDAOImpl;

public class TestReviewDAOImpl {
	
	private int id = 29;
	private String title = "bel";
	private String reviewer = "a";
	private int reviewed = 19;
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
	
	@Test
	public void testGetAllWrittenReviews() throws SQLException {
		ReviewDAOImpl r = new ReviewDAOImpl();
		AccountBean a = new AccountBean("a", "m", "a", "m", "2020-12-27", "m", 10);
		List<ReviewBean> res = r.getAllWrittenReviews(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAllReceivedReviews() throws SQLException {
		ReviewDAOImpl r = new ReviewDAOImpl();
		PersonBean a = new PersonBean(19, "f", "f", "f", "marco", 0, 0);
		List<ReviewBean> res = r.getAllReceivedReviews(a);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAllReviews() throws SQLException {
		ReviewDAOImpl r = new ReviewDAOImpl();
		List<ReviewBean> res = r.getAllReviews();
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAllPersonReviewsAsHost() throws SQLException {
		ReviewDAOImpl r = new ReviewDAOImpl();
		PersonBean p = new PersonBean();
		p.setId(29);
		List<ReviewBean> res = r.getAllPersonReviewsAsHost(p);
		assertEquals(res, 1);
	}
	
	@Test
	public void testGetAllPersonReviewsAsGuest() throws SQLException {
		ReviewDAOImpl r = new ReviewDAOImpl();
		PersonBean p = new PersonBean();
		p.setId(30);
		List<ReviewBean> res = r.getAllPersonReviewsAsGuest(p);
		assertEquals(res,1);
	}
}
