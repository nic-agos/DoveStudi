package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class LogInGC implements Initializable{
	
	@FXML
	 Button backBtn;
	@FXML
	 Button logInBtn;
	@FXML
	 Button regBtn;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*nothing to do here for now*/
	}
	
	@FXML
	public void logIn() {
		ViewSwitcher.switchTo(Views.MYACCOUNT,null);
	}
	
	@FXML
	public void back() {
		ViewSwitcher.back();
	}
	
	@FXML
	public void registration() {
		ViewSwitcher.switchTo(Views.REGISTRATION, null);
	}

		
}
