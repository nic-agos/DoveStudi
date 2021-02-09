package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.bean.AccountBean;
import logic.bean.PersonBean;
import logic.controller.AccountController;
import logic.controller.ReviewController;
import logic.exception.DatabaseException;
import logic.model.Person;
import logic.model.Review;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class OtherAccountGC implements Initializable {
	
	@FXML
	private Button backBtn;
	@FXML
	private Button makeReviewBtn;
	@FXML
	private BorderPane main;
	@FXML
	private Label usernameLbl;
	@FXML
	private Label emailLbl;
	@FXML
	private Label gradeLbl;
	@FXML
	private Label dateLbl;
	@FXML 
	private Label schoolLbl;
	@FXML
	private Label hRateLbl;
	@FXML
	private Label gRateLbl;
	@FXML
	private ListView<Review> reviewsList;
	
	private ObservableList<Review> reviews;
	private Person person;
	
	public OtherAccountGC(String username) {
		
		getPerson(username);
		
		if(this.person != null) {
			getReviews();
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(this.person != null) {
			usernameLbl.setText(person.getUsername());
			emailLbl.setText(person.getAccount().getEmail());
			gradeLbl.setText(person.getStudyGrade());
			dateLbl.setText(person.getAccount().getDateBirth());
			schoolLbl.setText(person.getSchool());
			hRateLbl.setText(String.valueOf(person.getHostRating()));
			gRateLbl.setText(String.valueOf(person.getGuestRating()));
		}
	}
	
	@FXML
	public void makeReview() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.REVIEWFORM, null));
	}
	
	@FXML
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
	
	private void getPerson(String username) {
		PersonBean pBean = new PersonBean();
		pBean.setUsername(username);
		AccountController aContr = AccountController.getInstance();
		
		try {
			
			this.person = aContr.getOtherAccountInfo(pBean);
		
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void getReviews() {
		AccountBean accBean = new AccountBean();
		ReviewController rContr = ReviewController.getInstance();
		
		accBean.setCf(this.person.getAccount().getCf());
		
		try {
			
			this.reviews = FXCollections.observableArrayList(rContr.getReceivedReviews(accBean));
		
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
