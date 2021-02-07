package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import logic.util.ViewSwitcher;

public class CreateGroupGC implements Initializable{
	
	 @FXML
	 Button backBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*nothing to do here for now*/
	}
	
	@FXML
	public void backAction() {
		ViewSwitcher.back();
	}

}
