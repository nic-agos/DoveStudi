package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class ReviewFormGC implements Initializable{
	
	@FXML
	Button discardBtn;
	@FXML
	Button submitBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
	}
	
	@FXML
	public void back() {
		ViewSwitcher.back();
	}
	
	@FXML
	public void submit() {
		/* Dove vado?
		 * ViewSwitcher.switchTo(Views., controller)*/
		
	}
}
