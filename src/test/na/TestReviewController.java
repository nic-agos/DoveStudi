package test.na;

import org.junit.Test;

import logic.bean.*;
import logic.controller.*;
import logic.exception.*;
import logic.model.*;
import logic.model.dao.AccountDAOImpl;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/*
 * JUnit test class Agostinelli Niccolo' matr. 0269776
 */

public class TestReviewController {
	
	@BeforeClass
	public static void initialize() {
		RegistrationController regContr = RegistrationController.getInstance();
		
		AccountBean a1 = new AccountBean("TTTFNC76P27H501J", "Francesco", "Totti", "francesco.totti@gmail.com", "magicaroma", "1976:09:27", 0);
		PersonBean p1 = new PersonBean("pupone76", "High School", "Trigoria", "TTTFNC76P27H501J", 0, 0);
		
		AccountBean a2 = new AccountBean("DRSDNL83L24H501X", "Daniele", "De Rossi", "daniele.derossi@gmail.com", "magicaroma", "1983:07:24", 0);
		PersonBean p2 = new PersonBean("danielino83", "High School", "Trigoria", "DRSDNL83L24H501X", 0, 0);
		
		try {
			
			regContr.register(a1, p1);
			regContr.register(a2, p2);
			
		}catch(DatabaseException e) {
			e.printStackTrace();
		}
	}

//	test that check the correct creation of a review on the db
	@Test
	public void testMakeReview() {
		
		ReviewController revContr = ReviewController.getInstance();
		
		ReviewBean reviewBean = new ReviewBean();
		PersonBean reviewed = new PersonBean();
		
		reviewBean.setTitle("Bel Giocatore");
		reviewBean.setDescription("Grande giocatore e degno capitan futuro");
		reviewBean.setRating(4);
		reviewBean.setReviewer("TTTFNC76P27H501J");
		reviewBean.setTag("HOST");
		
		reviewed.setUsername("danielino83");
		
		boolean result = false;
		
		try {
			result = revContr.makeReview(reviewBean, reviewed);
		}catch(DatabaseException | ReviewException | AccountException e1) {
			e1.printStackTrace();
		}
		
		assertEquals(true, result);
	}

//	test that check the correct computation of HostRating after the making of a Host review
	@Test
	public void testCalculateHostRating() {
		
		ReviewController revContr = ReviewController.getInstance();
		PersonBean personBean = new PersonBean();
		personBean.setUsername("danielino83");
		
		double hostRating = 0;
		
		try {
			hostRating = revContr.calculateHostRating(personBean);
			
		}catch(DatabaseException | ReviewException | AccountException e1) {
			e1.printStackTrace();
		}
		
		assertEquals(4, hostRating, 0);
	}

//	test that check the correct computation of GuestRating (no guest reviews are done before)
	@Test
	public void testCalculateGuestRating() {
		
		ReviewController revContr = ReviewController.getInstance();
		PersonBean personBean = new PersonBean();
		personBean.setUsername("danielino83");
		
		double guestRating = 10;
		
		try {
			guestRating = revContr.calculateGuestRating(personBean);
			
		}catch(DatabaseException | ReviewException | AccountException e1) {
			e1.printStackTrace();
		}
		
		assertEquals(0, guestRating, 0);
	}

//	test that checks if the method correctly get the list of done reviews
	@Test
	public void testGetDoneReviews() {
		
		ReviewController revContr = ReviewController.getInstance();
		
		List<Review> doneReviews = new ArrayList<>();
		AccountBean accountBean = new AccountBean();
		
		accountBean.setCf("DRSDNL83L24H501X");
		
		try {
			doneReviews = revContr.getDoneReviews(accountBean);
			
		}catch(DatabaseException e ) {
			e.printStackTrace();
		}
		int size = doneReviews.size();
		
		assertEquals(0,size);
	}

//	test that checks if the method correctly get the list of received reviews
	@Test
	public void testGetReceivedReviews() {
		
		ReviewController revContr = ReviewController.getInstance();
		
		List<Review> receivedReviews = new ArrayList<>();
		AccountBean accountBean = new AccountBean();
		
		accountBean.setCf("DRSDNL83L24H501X");
		
		try {
			receivedReviews = revContr.getReceivedReviews(accountBean);
			
		}catch(DatabaseException e ) {
			e.printStackTrace();
		}
		int size = receivedReviews.size();
		
		assertEquals(1,size);
	}
	
	@AfterClass
	public static void destroy() {
		
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		AccountBean a1 = new AccountBean();
		AccountBean a2 = new AccountBean();
		
		a1.setCf("TTTFNC76P27H501J");
		a2.setCf("DRSDNL83L24H501X");
		
		try {
			
			accountDao.removeAccount(a1);
			accountDao.removeAccount(a2);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
