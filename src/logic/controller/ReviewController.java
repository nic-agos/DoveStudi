package logic.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;
import logic.model.dao.*;
import logic.exception.*;
import logic.model.*;

public class ReviewController {

	private static final String NOT_FOUND = " not found";
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

//	takes in input the reviewer cf, the title, the rating, the description and the tag from reviewBean
//	and the username of the reviewed from personBean
//	this method also update the Account host and guest rating 
	public boolean makeReview(ReviewBean reviewBean, PersonBean personBean) throws DatabaseException, ReviewException, AccountException { 
		
		ReviewDAOImpl reviewDao = ReviewDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		PersonBean persBean;
		ReviewBean revBean = new ReviewBean();
		
		int res;
		double rating;
		
		try {

//			get the Person entity of the reviewed user
			persBean = personDao.getPersonByUsername(personBean);
			
			if(persBean != null) {
				
				revBean.setTitle(reviewBean.getTitle());
				revBean.setReviewer(reviewBean.getReviewer());
				revBean.setReviewed(persBean.getId());
				revBean.setRating(reviewBean.getRating());
				revBean.setDescription(reviewBean.getDescription());
				revBean.setTag(reviewBean.getTag());
				
//				add the review to the db
				res = reviewDao.createReview(revBean);
				
				if(res != 0) {
					
					if(revBean.getTag().equals("HOST")) {

//						update user's host rating
						rating = calculateHostRating(personBean);
						persBean.setHostRating(rating);
						return (personDao.updateHostRating(persBean) != 0);
					}
					
					if(revBean.getTag().equals("GUEST")) {
						
//						update user's guest rating
						rating = calculateGuestRating(personBean);
						persBean.setGuestRating(rating);
						return (personDao.updateGuestRating(persBean) != 0);
					}
					
					return false;
					
				}else {
					throw new ReviewException("Unable to post review");
				}
				
			}else {
				throw new AccountException("User " + personBean.getUsername() + NOT_FOUND);
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
		
	}
	  
	public List<Review> getDoneReviews(AccountBean accountBean) throws DatabaseException {
		
		ReviewDAOImpl reviewDao = ReviewDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		List<Review> receivedReviewList = new ArrayList<>();
		List<ReviewBean> writtenReviewBeansList;
		Review review;
		Person reviewedPerson;
		Account reviewedAccount;
		Account reviewerAccount;
		Person reviewerPerson;
		PersonBean personBean;
		AccountBean tempAccountBean;
		PersonBean tempPersonBean = new PersonBean();
		AccountBean temp2AccountBean = new AccountBean();
		
		try {

//			get the AccountBean of the reviewer user from the db
			tempAccountBean = accountDao.getAccount(accountBean);
			

//			create the Account and Person entities for the reviewer
			reviewerAccount = new Account(tempAccountBean);
			reviewerPerson = new Person(personDao.getPersonFromAccount(tempAccountBean));

//			linking the Person entity to the Account Entity
			reviewerAccount.setPerson(reviewerPerson);

			
//			get a list of all ReviewBean written by an Account
			writtenReviewBeansList = reviewDao.getAllWrittenReviews(accountBean);
				
				for(ReviewBean revBean : writtenReviewBeansList) {
					review = new Review(revBean);

//					add the reviewer Account entity to the Review entity
					review.setReviewer(reviewerAccount);

//					create the reviewed Person entity
					tempPersonBean.setId(revBean.getReviewed());
					personBean = personDao.getPerson(tempPersonBean);
					reviewedPerson = new Person(personBean);
					
//					create the reviewed Account entity and linking to the reviewed Person entity
					temp2AccountBean.setCf(personBean.getAccount());
					reviewedAccount = new Account(accountDao.getAccount(temp2AccountBean));
					reviewedPerson.setAccount(reviewedAccount);
					
					review.setReviewed(reviewedPerson);

//					add the review to the returning list
					receivedReviewList.add(review);
				}
				return receivedReviewList;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	  
	}

//	takes in input the cf of user
	public List<Review> getReceivedReviews(AccountBean accountBean) throws DatabaseException {
		
		ReviewDAOImpl reviewDao = ReviewDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		List<Review> receivedReviewList = new ArrayList<>();
		List<ReviewBean> receivedReviewBeansList;
		Review review;
		Person reviewedPerson;
		Person reviewerPerson;
		Account reviewedAccount;
		Account reviewerAccount;
		PersonBean personBean;
		AccountBean tempAccountBean;
		AccountBean temp2AccountBean = new AccountBean();
		
		try {

//			get the PersonBean associated to the account from db
			personBean = personDao.getPersonFromAccount(accountBean);

//			get the AccountBean of the user from the db
			tempAccountBean = accountDao.getAccount(accountBean);
			

//			create the reviewed Person entity and linking his Account entity
			reviewedPerson = new Person(personBean);
			reviewedAccount = new Account(tempAccountBean);
			reviewedPerson.setAccount(reviewedAccount);
			
//			get a list of all ReviewBean received by a user
			receivedReviewBeansList = reviewDao.getAllReceivedReviews(personBean);

				for(ReviewBean revBean : receivedReviewBeansList) {
					review = new Review(revBean);

//					add the reviewed Person entity to the Review entity
					review.setReviewed(reviewedPerson);
					
//					create the reviewer Account entity and linking to the review
					temp2AccountBean.setCf(revBean.getReviewer());
					reviewerAccount = new Account(accountDao.getAccount(temp2AccountBean));
					reviewerPerson = new Person(personDao.getPersonFromAccount(temp2AccountBean));
					reviewerAccount.setPerson(reviewerPerson);
					review.setReviewer(reviewerAccount);

//					add the review to the returning list
					receivedReviewList.add(review);
				}
				return receivedReviewList;
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	  
	}
 
//	takes in input the username of the user and return the host rating
	public double calculateHostRating(PersonBean personBean) throws DatabaseException, ReviewException, AccountException {
		
		ReviewDAOImpl reviewDao = ReviewDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		double ratingSum = 0;
		int size = 0;
		List<ReviewBean> reviewsHost = new ArrayList<>();
		PersonBean persBean;
		
		try {

//			getting Person's info from db
			persBean = personDao.getPersonByUsername(personBean);
			
			if(persBean != null) {

//				getting all user's review as host from db
				reviewsHost = reviewDao.getAllPersonReviewsAsHost(persBean);
				size = reviewsHost.size();
				
				if(!reviewsHost.isEmpty()) {
					
					for(ReviewBean revBean : reviewsHost) {
						
						ratingSum = ratingSum + revBean.getRating();
					}
					
					return ratingSum/size;
				
				}else {
					return 0;
				}
			
			}else {
				throw new AccountException("User with username " + personBean.getUsername() + NOT_FOUND);
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}

//	takes in input the username of the user and return the guest rating
	public double calculateGuestRating(PersonBean personBean) throws ReviewException, AccountException, DatabaseException {
		
		ReviewDAOImpl reviewDao = ReviewDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		
		double ratingSum = 0;
		int size = 0;
		List<ReviewBean> reviewsGuest;
		PersonBean persBean;
		
		try {

//			getting Person's info from db
			persBean = personDao.getPersonByUsername(personBean);
			
			if(persBean != null) {
				
//				getting all user's review as guest from db				
				reviewsGuest = reviewDao.getAllPersonReviewsAsGuest(persBean);
				size = reviewsGuest.size();
				
				if(!reviewsGuest.isEmpty()) {
					
					for(ReviewBean revBean : reviewsGuest) {
						
						ratingSum = ratingSum + revBean.getRating();
					}
					
					return ratingSum/size;
				
				}else {
					return 0;
				}
			
			}else {
				throw new AccountException("User with username " + personBean.getUsername() + NOT_FOUND);
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}
}
