package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MyAccountGC implements Initializable{
	
	@FXML
	 Button makeReviewBtn;
	@FXML
	 Button buyTokensBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void buyTokens() {
		Stage pop = new Stage();
		pop.setTitle("Buy Your Tokens");
		
		WebView webView = new WebView();
        webView.getEngine().load("www.paypal.com");
        
        VBox box = new VBox(webView);
        Scene scene = new Scene(box, 960, 600);
        
        pop.setScene(scene);
        pop.show();
	}
	
	@FXML
	public void makeReview() {
		/*Dove lo mando?*/
	}
	
	
}
