package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import logic.util.ViewSwitcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import logic.util.enumeration.Views;

public class HomeGC implements Initializable {
	
	@FXML
	Button postBtn;
	@FXML
	Button lookBtn;
	@FXML
	Button logInBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*probably nothing to do here*/
	}
	
	@FXML
	public void postAction() {
		ViewSwitcher.switchTo(Views.POSTROOM, null);
	}	
	@FXML
	public void lookAction() {
		ViewSwitcher.switchTo(Views.ROOMSEARCH, null);
	}
	@FXML
	public void logInAction() {
		ViewSwitcher.switchTo(Views.LOGIN, null);
	}
}
