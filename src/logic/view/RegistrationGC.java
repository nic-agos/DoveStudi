package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

/* ADD OBSERVABLE LIST AND INJECT IN THE CHOICE BOX WITH THE SCHOOL GRADES*/

public class RegistrationGC implements Initializable{
	
	@FXML
	private Button regBtn;
	@FXML
	private Button backBtn;
	@FXML 
	private BorderPane main;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
	}
	
	@FXML
	public void register() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.MYACCOUNT,null));
	}
	
	@FXML
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
}
