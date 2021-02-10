package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;
import logic.model.database.DBConnection;

public class ReviewDAOImpl implements ReviewDAO {
	
	private static final String CREATE_REVIEW_QUERY = "INSERT INTO review (Title, Reviewer, Reviewed, Rating, Description, Tag) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String DELETE_REVIEW_QUERY = "DELETE FROM review where ID = ?";
	private static final String GET_REVIEW_ID_QUERY = "SELECT ID FROM review WHERE Title = ? AND Reviewer = ? AND Reviewed = ?";
	private static final String GET_RECEIVED_REVIEWS_QUERY = "SELECT * FROM review WHERE Reviewed = ?";
	private static final String GET_WRITTEN_REVIEWS_QUERY = "SELECT * FROM review WHERE Reviewer = ?";
	private static final String GET_ALL_REVIEWS_QUERY = "SELECT * FROM review";
	private static final String GET_ALL_REVIEWS_TAG_QUERY = "SELECT * FROM review WHERE Reviewed = ? AND Tag = ?";
	
	
	private static ReviewDAOImpl instance = null;
	
	private ReviewDAOImpl() {
		
	}
	
	public static synchronized ReviewDAOImpl getInstance() {
		if(ReviewDAOImpl.instance == null) {
			ReviewDAOImpl.instance = new ReviewDAOImpl();
		}
		return instance;
	}
	
	@Override
	public int createReview(ReviewBean reviewBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(CREATE_REVIEW_QUERY);
			stmt.setString(1, reviewBean.getTitle());
			stmt.setString(2, reviewBean.getReviewer());
			stmt.setInt(3, reviewBean.getReviewed());
			stmt.setInt(4, reviewBean.getRating());
			stmt.setString(5, reviewBean.getDescription());
			stmt.setString(6, reviewBean.getTag());
			
			stmt.executeUpdate();
			
			return getReviewId(reviewBean);
				
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	@Override
	public int removeReview(ReviewBean reviewBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_REVIEW_QUERY);
			stmt.setInt(1, reviewBean.getId());
			
			return stmt.executeUpdate();
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public int getReviewId(ReviewBean reviewBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		int id = 0;		
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_REVIEW_ID_QUERY);
			stmt.setString(1, reviewBean.getTitle());
			stmt.setString(2, reviewBean.getReviewer());
			stmt.setInt(3, reviewBean.getReviewed());
			
			ResultSet r = stmt.executeQuery();
			
			while (r.next()) {
				id = r.getInt(1);
			}
			
			return id;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public List<ReviewBean> getAllReceivedReviews(PersonBean personBean) throws SQLException {
		
		List<ReviewBean> receivedReview = new ArrayList<>();
		ReviewBean review = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_RECEIVED_REVIEWS_QUERY);
			stmt.setInt(1, personBean.getId());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					review = new ReviewBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getString(7));
					receivedReview.add(review);
				}
				
			res.close();
			
			return receivedReview;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public List<ReviewBean> getAllWrittenReviews(AccountBean accountBean) throws SQLException {
		
		List<ReviewBean> writtenReview = new ArrayList<>();
		ReviewBean review = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_WRITTEN_REVIEWS_QUERY);
			stmt.setString(1, accountBean.getCf());
			
			ResultSet res = stmt.executeQuery();
				while (res.next()) {
					review = new ReviewBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getString(7));
					writtenReview.add(review);
				}
				
			res.close();
			
			return writtenReview;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public List<ReviewBean> getAllReviews() throws SQLException {
		
		List<ReviewBean> writtenReview = new ArrayList<>();
		ReviewBean review = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ALL_REVIEWS_QUERY);
			
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				review = new ReviewBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getString(7));
				writtenReview.add(review);
			}
				
			res.close();
			
			return writtenReview;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public List<ReviewBean> getAllPersonReviewsAsGuest(PersonBean personBean) throws SQLException {
		
		List<ReviewBean> reviewsGuest = new ArrayList<>();
		ReviewBean review = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ALL_REVIEWS_TAG_QUERY);
			stmt.setInt(1, personBean.getId());
			stmt.setString(2, "GUEST");
			
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				review = new ReviewBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getString(7));
				reviewsGuest.add(review);
			}
				
			res.close();
			
			return reviewsGuest;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
	
	@Override
	public List<ReviewBean> getAllPersonReviewsAsHost(PersonBean personBean) throws SQLException {
		
		List<ReviewBean> reviewsHost = new ArrayList<>();
		ReviewBean review = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ALL_REVIEWS_TAG_QUERY);
			stmt.setInt(1, personBean.getId());
			stmt.setString(2, "HOST");
			
			ResultSet res = stmt.executeQuery();
			
			while (res.next()) {
				review = new ReviewBean(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getInt(5), res.getString(6), res.getString(7));
				reviewsHost.add(review);
			}
				
			res.close();
			
			return reviewsHost;
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null ) {
				connection.close();
			}
		}
	}
}
