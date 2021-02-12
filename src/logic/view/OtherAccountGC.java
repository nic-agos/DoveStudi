package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.AccountBean;
import logic.bean.PersonBean;
import logic.controller.AccountController;
import logic.controller.ReviewController;
import logic.exception.DatabaseException;
import logic.model.Person;
import logic.model.Review;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Search;
import logic.util.enumeration.Views;

/*Linked FXML file: OtherAccount.fxml*/
public class OtherAccountGC implements Initializable {
	
	@FXML
	private Button backBtn;
	@FXML
	private Button makeReviewBtn;
	@FXML
	private BorderPane main;
	@FXML
	private Label usernameLbl;
	@FXML
	private Label emailLbl;
	@FXML
	private Label gradeLbl;
	@FXML
	private Label dateLbl;
	@FXML 
	private Label schoolLbl;
	@FXML
	private Label hRateLbl;
	@FXML
	private Label gRateLbl;
	@FXML
	private ListView<Review> reviewsList;
	
	private static final String ERROR = "ERROR";
	
	private ObservableList<Review> reviews;
	private Person person;
	
	public OtherAccountGC(String username) {
		// Constructor for getting user's info only based on username
		getPerson(username);
		
		if(this.person != null) {
			getReviews();
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(!Session.getSession().isLogged()) {
			makeReviewBtn.setDisable(true);
		}
		if(this.person != null) {
			usernameLbl.setText(person.getUsername());
			emailLbl.setText(person.getAccount().getEmail());
			gradeLbl.setText(person.getStudyGrade());
			dateLbl.setText(person.getAccount().getDateBirth());
			schoolLbl.setText(person.getSchool());
			hRateLbl.setText(String.valueOf(person.getHostRating()));
			gRateLbl.setText(String.valueOf(person.getGuestRating()));
		}
		if(!reviews.isEmpty()) {
			reviewsList.setFocusTraversable(false);
			reviewsList.setItems(reviews);
			reviewsList.setCellFactory(list -> new RecRevCell());
		}
	}
	
	class RecRevCell extends ListCell<Review>{
		@Override
		public void updateItem(Review item, boolean empty) {
			super.updateItem(item,empty);
			if(!empty) {
				VBox v = new VBox();
				Label rate = new Label("Rate: " + String.valueOf(item.getRating()));
				Label description = new Label("Description " + item.getDescription());
				Label title = new Label(item.getTitle());
				Label tag = new Label(item.getTag());
				
				description.setWrapText(true);
				
				Hyperlink reviewingUser = new Hyperlink();
				reviewingUser.setText(item.getReviewer().getPerson().getUsername());
				reviewingUser.setOnAction(e ->{
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(reviewingUser.getText())));
				});
			
				v.getChildren().addAll(reviewingUser,title,description,tag,rate);
				v.setSpacing(5);
				v.setAlignment(Pos.CENTER);
				v.setMaxWidth(535);
				setGraphic(v);
			}
			
		}
		
	}
	
	@FXML
	public void makeReview() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.REVIEWFORM, new ReviewFormGC(person.getUsername())));
	}
	
	@FXML
	public void back() {
		if(Session.getSession().getPrevView().equals(Views.ROOMSEARCH))
		{
			Stage stage = (Stage) main.getScene().getWindow();
			stage.setScene(ViewSwitcher.switchTo(Views.ROOMSEARCH,new SearchGC(Search.ALL,"")));
		}
		else {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
		}
	}
	//This method gets the user information and set an Entity with those
	private void getPerson(String username) {
		PersonBean pBean = new PersonBean();
		pBean.setUsername(username);
		AccountController aContr = AccountController.getInstance();
		
		try {
			
			this.person = aContr.getOtherAccountInfo(pBean);
		
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//This method get all the review of an account and set a local Observable list
	private void getReviews() {
		AccountBean accBean = new AccountBean();
		ReviewController rContr = ReviewController.getInstance();
		
		accBean.setCf(this.person.getAccount().getCf());
		
		try {
			
			this.reviews = FXCollections.observableArrayList(rContr.getReceivedReviews(accBean));
		
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
