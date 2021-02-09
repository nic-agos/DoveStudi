package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import logic.bean.AccountBean;
import logic.controller.ReviewController;
import logic.exception.DatabaseException;
import logic.model.*;
import logic.util.Session;

public class MyReviewsGC implements Initializable{
	
	@FXML
	private BorderPane main;
	@FXML
	private ListView<Review> doneList;
	@FXML
	private ListView<Review> receivedList;
	
	private ObservableList<Review> doneReviews;
	private ObservableList<Review> receivedReviews;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*blabla*/
	}
	
	private void getDone() {
		
		ReviewController revContr = ReviewController.getInstance();
		AccountBean accBean = new AccountBean();
		
		accBean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
		
		try {
			this.receivedReviews = FXCollections.observableArrayList(revContr.getReceivedReviews(accBean));
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void getReceived() {
		ReviewController revContr = ReviewController.getInstance();
		AccountBean accBean = new AccountBean();
		
		accBean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
		
		try {
			this.receivedReviews = FXCollections.observableArrayList(revContr.getReceivedReviews(accBean));
		
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
