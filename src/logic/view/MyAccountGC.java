package logic.view;

import java.net.URL;
import java.util.ResourceBundle;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.model.Person;
import logic.util.Session;


public class MyAccountGC implements Initializable{
	
	@FXML
	private Button buyTokensBtn;
	@FXML
	private Label usernameLbl;
	@FXML
	private Label emailLbl;
	@FXML
	private Label dateLbl;
	@FXML
	private Label gradeLbl;
	@FXML
	private Label schoolLbl;
	@FXML
	private Label hRateLbl;
	@FXML
	private Label gRateLbl;
	@FXML
	private Label cfLbl;
	@FXML
	private Label nameLbl;
	@FXML
	private Label surnameLbl;
	@FXML
	private Label pswLbl;
	@FXML
	private Label tokensLbl;
	
	private Person p;
	
	public MyAccountGC() {
		p = Session.getSession().getCurrUser();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usernameLbl.setText(p.getUsername());
		emailLbl.setText(p.getAccount().getEmail());
		dateLbl.setText(p.getAccount().getDateBirth());
		gradeLbl.setText(p.getStudyGrade());
		schoolLbl.setText(p.getSchool());
		hRateLbl.setText(String.valueOf(p.getHostRating()));
		gRateLbl.setText(String.valueOf(p.getGuestRating()));
		cfLbl.setText(p.getAccount().getCf());
		nameLbl.setText(p.getAccount().getName());
		surnameLbl.setText(p.getAccount().getSurname());
		pswLbl.setText(p.getAccount().getPassword());
		tokensLbl.setText(String.valueOf(p.getAccount().getNumberToken()));	
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
}
