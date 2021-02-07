package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class OtherAccountGC implements Initializable {
	
	@FXML
	 Button backBtn;
	@FXML
	 Button makeReviewBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
	}
	
	@FXML
	public void makeReview() {
		ViewSwitcher.switchTo(Views.REVIEWFORM, null);
	}
	
	@FXML
	public void back() {
		ViewSwitcher.back();
	}
}
