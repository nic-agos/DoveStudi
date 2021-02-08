package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import logic.model.Review;

public class MyReviewsGC implements Initializable{
	
	@FXML
	private BorderPane main;
	@FXML
	private ListView<Review> doneList;
	@FXML
	private ListView<Review> receivedList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*blabla*/
	}
	
}
