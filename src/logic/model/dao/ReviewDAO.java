package logic.model.dao;

import java.sql.SQLException;
import java.util.List;

import logic.bean.*;

public interface ReviewDAO {
	
	public int createReview(ReviewBean reviewBean) throws SQLException;
	
	public int removeReview(ReviewBean reviewBean) throws SQLException;
	
	public int getReviewId(ReviewBean reviewBean) throws SQLException;
	
	public List<ReviewBean> getAllReceivedReviews(PersonBean personBean) throws SQLException;
	
	public List<ReviewBean> getAllWrittenReviews(AccountBean accountBean) throws SQLException;
	
	public List<ReviewBean> getAllReviews() throws SQLException;


}
