package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import logic.util.ViewSwitcher;

public class GroupBookRoomGC implements Initializable{
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*nothing to do here right now*/
	}
	@FXML
	public void backAction() {
		ViewSwitcher.back();
	}
}
