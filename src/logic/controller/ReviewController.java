package logic.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;
import logic.model.dao.*;
import logic.exception.*;
import logic.model.*;

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

//	takes in input the reviewer cf, the rating, the description and the tag from groupBean
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
				revBean.setDescription(reviewBean.getDescritpion());
				revBean.setTag(reviewBean.getTag());
				
//				add the review to the db
				res = reviewDao.createReview(revBean);
				
				if(res != 0) {
					
					if(revBean.getTag().equals("HOST")) {
						rating = calculateHostRating(personBean);
						persBean.setHostRating(rating);
						return (personDao.updateHostRating(persBean) != 0);
					}
					
					if(revBean.getTag().equals("GUEST")) {
						rating = calculateGuestRating(personBean);
						persBean.setGuestRating(rating);
						return (personDao.updateGuestRating(persBean) != 0);
					}
					
					return false;
					
				}else {
					throw new ReviewException("Unable to post review");
				}
				
			}else {
				throw new AccountException("User " + personBean.getUsername() + " not found");
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
		
	}
	  
	public List<Review> getDoneReviews(AccountBean accountBean) throws DatabaseException, ReviewException {
		
		ReviewDAOImpl reviewDao = ReviewDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		List<Review> receivedReviewList = new ArrayList<>();
		List<ReviewBean> writtenReviewBeansList;
		Review review;
		Person reviewedPerson;
		Account reviewedAccount;
		Account reviewerAccount;
		PersonBean personBean;
		AccountBean tempAccountBean;
		PersonBean tempPersonBean = new PersonBean();
		AccountBean temp2AccountBean = new AccountBean();
		
		try {

//			get the AccountBean of the reviewer user from the db
			tempAccountBean = accountDao.getAccount(accountBean);
			

//			create the Account entity for the reviewer
			reviewerAccount = new Account(tempAccountBean);

			
//			get a list of all ReviewBean written by an Account
			writtenReviewBeansList = reviewDao.getAllWrittenReviews(accountBean);

//			check if the list is empty
			if(!writtenReviewBeansList.isEmpty()) {
				
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
			
			}else {
				throw new ReviewException("No reviews maked by the account: " + accountBean.getCf() + " found");
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	  
	}
	
	public List<Review> getReceivedReviews(AccountBean accountBean) throws DatabaseException, ReviewException {
		
		ReviewDAOImpl reviewDao = ReviewDAOImpl.getInstance();
		PersonDAOImpl personDao = PersonDAOImpl.getInstance();
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		List<Review> receivedReviewList = new ArrayList<>();
		List<ReviewBean> receivedReviewBeansList;
		Review review;
		Person reviewedPerson;
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

//			check if the list is empty
			if(!receivedReviewBeansList.isEmpty()) {
				
				for(ReviewBean revBean : receivedReviewBeansList) {
					review = new Review(revBean);

//					add the reviewed Person entity to the Review entity
					review.setReviewed(reviewedPerson);
					
//					create the reviewer Account entity and linking to the review
					temp2AccountBean.setCf(revBean.getReviewer());
					reviewerAccount = new Account(accountDao.getAccount(temp2AccountBean));
					review.setReviewer(reviewerAccount);

//					add the review to the returning list
					receivedReviewList.add(review);
				}
				return receivedReviewList;
			
			}else {
				throw new ReviewException("No reviews for the user: " + personBean.getUsername() + " found");
			}
			
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
		List<ReviewBean> reviewsHost;
		PersonBean persBean;
		
		try {
			persBean = personDao.getPersonByUsername(personBean);
			
			if(persBean != null) {
				
				reviewsHost = reviewDao.getAllPersonReviewsAsHost(persBean);
				size = reviewsHost.size();
				
				if(!reviewsHost.isEmpty()) {
					for(ReviewBean revBean : reviewsHost) {
						ratingSum = ratingSum + revBean.getRating();
					}
					
					return ratingSum/size;
				
				}else {
					throw new ReviewException("No review as Host received by user "+ personBean.getUsername());
				}
			
			}else {
				throw new AccountException("User with username " + personBean.getUsername() + " not found");
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
			persBean = personDao.getPersonByUsername(personBean);
			
			if(persBean != null) {
				
				reviewsGuest = reviewDao.getAllPersonReviewsAsGuest(persBean);
				size = reviewsGuest.size();
				
				if(!reviewsGuest.isEmpty()) {
					for(ReviewBean revBean : reviewsGuest) {
						ratingSum = ratingSum + revBean.getRating();
					}
					
					return ratingSum/size;
				
				}else {
					throw new ReviewException("No review as Host received by user "+ personBean.getUsername());
				}
			
			}else {
				throw new AccountException("User with username " + personBean.getUsername() + " not found");
			}
			
		}catch (SQLException se) {
			throw new DatabaseException(se.getMessage());
		}
	}
}
