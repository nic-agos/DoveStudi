package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class MyGroupsGC implements Initializable{
	
	@FXML
	Button createBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	/***nothing to do here for now*/	
	}
	
	@FXML
	public void createGroup() {
		ViewSwitcher.switchTo(Views.GROUPCREATION, null);
	}
	
}
