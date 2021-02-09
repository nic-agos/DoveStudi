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
	
	
	PersonBean p; //Questa Bean � popolata solo con l'username della persona che voglio recensire
	public ReviewFormGC(PersonBean p) {
		this.p=p;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reviewdUserLbl.setText(p.getUsername());
	}
	
	@FXML
	public void submit() {
		ReviewBean r = new ReviewBean();
		
		r.setTitle(reviewTitle.getText());
		r.setDescription(reviewArea.getText());
		r.setReviewer(Session.getSession().getCurrUser().getAccount().getCf());
		
		r.setRating(Integer.parseInt(((RadioButton)rating.getSelectedToggle()).getText()));
		
		if(((RadioButton)userType.getSelectedToggle()).getText()=="Guest")
			r.setTag("GUEST");
		if(((RadioButton)userType.getSelectedToggle()).getText()=="Host")
			r.setTag("HOST");
		try {
			r.validate();
			ReviewController ctrl = ReviewController.getInstance();
			
			boolean value = ctrl.makeReview(r,p);
			if(value) {
				Stage stage = (Stage) main.getScene().getWindow();
				stage.setScene(ViewSwitcher.switchTo(Views.MYREVIEWS, null));
			}
			
		}
		catch (ReviewException e){
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (DatabaseException ex) {
			JOptionPane.showMessageDialog(null,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (AccountException exc) {
			JOptionPane.showMessageDialog(null,exc.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	@FXML
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
}