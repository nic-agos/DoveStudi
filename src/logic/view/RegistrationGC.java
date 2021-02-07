package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

/* ADD OBSERVABLE LIST AND INJECT IN THE CHOICE BOX WITH THE SCHOOL GRADES*/

public class RegistrationGC implements Initializable{
	
	@FXML
	Button regBtn;
	@FXML
	Button backBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
	}
	
	@FXML
	public void register() {
		ViewSwitcher.switchTo(Views.MYACCOUNT,null);
	}
	
	@FXML
	public void back() {
		ViewSwitcher.back();
	}
}
