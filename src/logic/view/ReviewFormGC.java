package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.bean.PersonBean;
import logic.bean.ReviewBean;
import logic.controller.ReviewController;
import logic.exception.AccountException;
import logic.exception.DatabaseException;
import logic.exception.ReviewException;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

/*Linked FXML file: ReviewFrom.fxml*/
public class ReviewFormGC implements Initializable{
	
	@FXML
	private Button discardBtn;
	@FXML
	private Button submitBtn;
	@FXML
	private BorderPane main;
	@FXML
	private Label reviewdUserLbl;
	@FXML
	private TextField reviewTitle;
	@FXML
	private TextArea reviewArea;
	@FXML
	private ToggleGroup rating;
	@FXML
	private ToggleGroup userType;
	@FXML
	private RadioButton hostRB;
	@FXML
	private RadioButton guestRB;
	
	private static final String ERROR = "ERROR";
	
	String username; 
	// constructor get the reviewd user's username
	public ReviewFormGC(String username) {
		this.username = username;
	}
	
	@Override // Set the reviewd user's username label
	public void initialize(URL location, ResourceBundle resources) {
		reviewdUserLbl.setText(username);
		reviewArea.setWrapText(true);
	}
	
	@FXML /*Action associated to the submitBtn*/
	public void submit() {
		ReviewBean r = new ReviewBean();
		PersonBean p = new PersonBean();
		// Set the beans with the form informations
		p.setUsername(username);
		
		r.setTitle(reviewTitle.getText());
		r.setDescription(reviewArea.getText());
		r.setReviewer(Session.getSession().getCurrUser().getAccount().getCf());
		
		r.setRating(Integer.parseInt(((RadioButton)rating.getSelectedToggle()).getText()));
		
		if(userType.getSelectedToggle().equals(guestRB))
			r.setTag("GUEST");
		if(userType.getSelectedToggle().equals(hostRB))
			r.setTag("HOST");
		
		try {
			// validate the informations
			r.validate();
			ReviewController ctrl = ReviewController.getInstance();
			// send all and get the return value
			boolean value = ctrl.makeReview(r,p);
			// if all ok go to my reviews
			if(value) {
				Stage stage = (Stage) main.getScene().getWindow();
				stage.setScene(ViewSwitcher.switchTo(Views.MYREVIEWS, null));
			}
		}
		
		catch (ReviewException e){
			JOptionPane.showMessageDialog(null,e.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
		catch (DatabaseException ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
		catch (AccountException exc) {
			JOptionPane.showMessageDialog(null,exc.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	@FXML /*Action associated to the backBtn*/
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT,new OtherAccountGC(reviewdUserLbl.getText())));
	}
}
