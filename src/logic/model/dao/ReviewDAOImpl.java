package logic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.bean.*;

public class ReviewDAOImpl implements ReviewDAO {
	
	private static final String CREATE_QUERY = "INSERT INTO review (Title, Reviewer, Reviewed, Rating, Description, Tag) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String DELETE_QUERY = "DELETE FROM review where ID = ?";
	private static final String GET_ID_QUERY = "SELECT ID FROM review WHERE Title = ? AND Reviewer = ? AND Reviewed = ?";
	private static final String GET_RECEIVED_REVIEWS_QUERY = "SELECT * FROM review WHERE Reviewed = ?";
	private static final String GET_WRITTEN_REVIEWS_QUERY = "SELECT * FROM review WHERE Reviewer = ?";
	private static final String GET_ALL_REVIEW_QUERY = "SELECT * FROM review";
	
		public int createReview(ReviewBean reviewBean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(CREATE_QUERY);
			stmt.setString(1, reviewBean.getTitle());
			stmt.setString(2, reviewBean.getReviewer());
			stmt.setInt(3, reviewBean.getReviewed());
			stmt.setInt(4, reviewBean.getRating());
			stmt.setString(5, reviewBean.getDescritpion());
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
	
	public int removeReview(ReviewBean reviewBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(DELETE_QUERY);
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
	
	public int getReviewId(ReviewBean reviewBean) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection connection = null;
		int id = 0;		
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ID_QUERY);
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
	
	public List<ReviewBean> getAllWrittenReviews(AccountBean accountBean) throws SQLException {
		
		List<ReviewBean> writtenReview = new ArrayList<>();
		ReviewBean review = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_WRITTEN_REVIEWS_QUERY);
			stmt.setString(1, accountBean.getCF());
			
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
	
	public List<ReviewBean> getAllReviews() throws SQLException {
		
		List<ReviewBean> writtenReview = new ArrayList<>();
		ReviewBean review = null;
		
		PreparedStatement stmt = null;
		Connection connection = null;
		
		try {
			connection = DBConnection.getInstanceConnection().getConnection();
			
			stmt = connection.prepareStatement(GET_ALL_REVIEW_QUERY);
			
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
}
