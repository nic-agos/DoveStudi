package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.model.Room;
import logic.util.ViewSwitcher;

public class SearchGC implements Initializable{
	@FXML
	private BorderPane main;
	@FXML
	private Button capBtn;
	@FXML
	private Button hostBtn;
	@FXML
	private Button dateBtn;
	@FXML
	private Button seatsBtn;
	@FXML
	private Button allBtn;
	@FXML
	private Button back;
	@FXML
	private ListView<Room> roomList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
	}
	
	@FXML
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
	
}
