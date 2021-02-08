package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
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
}
