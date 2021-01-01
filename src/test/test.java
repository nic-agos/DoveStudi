package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.model.Account;
import logic.model.Person;
import logic.model.dao.*;
import logic.bean.*;

public class test {
	
	private int id = 7;
	
	private String title = "bel";
	
	private String reviewer = "marco";
	
	private int reviewed = 13;
	
	private int rating = 4;
	
	private String description = "bella";
	
	private String tag = "guest";
	
	@Test
	public void testRemoveReview() throws SQLException {
		ReviewDAOImpl r = new ReviewDAOImpl();
		ReviewBean a = new ReviewBean(id, title, reviewer, reviewed, rating, description, tag);
		int res = r.removeReview(a);
		assertEquals(res, 1);
	}
	
	
}
