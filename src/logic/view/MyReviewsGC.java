package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.AccountBean;
import logic.controller.ReviewController;
import logic.exception.DatabaseException;
import logic.model.*;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

/*Linked FXML file: MyReviews.*/
public class MyReviewsGC implements Initializable{
	
	@FXML
	private BorderPane main;
	@FXML
	private ListView<Review> doneList;
	@FXML
	private ListView<Review> receivedList;
	
	private static final String ERROR = "ERROR";
	
	private ObservableList<Review> doneReviews;
	private ObservableList<Review> receivedReviews;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDone();setReceived();
		//This view has got two separated lists and two different cells layout
		if(doneReviews != null && !doneReviews.isEmpty()) {
			doneList.setItems(doneReviews);
			doneList.setCellFactory(list -> new DoneRevCell());
		}
		
		if(receivedReviews != null && !receivedReviews.isEmpty() ) {
			receivedList.setItems(receivedReviews);
			receivedList.setCellFactory(list -> new RecRevCell());
		}
	}
	
	class DoneRevCell extends ListCell<Review>{
		@Override
		public void updateItem(Review item, boolean empty) {
			super.updateItem(item,empty);
			if(!empty) {
				VBox v = new VBox();
				Label rate = new Label("Rate: " + String.valueOf(item.getRating()));
				Label description = new Label("Description: " + item.getDescription());
				Label title = new Label(item.getTitle());
				Label tag = new Label(item.getTag());
				
				description.setWrapText(true);
				// Link to the reviewd user
				Hyperlink reviewdUser = new Hyperlink();
				reviewdUser.setText(item.getReviewed().getUsername());
				reviewdUser.setOnAction(e ->{
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(reviewdUser.getText())));
				});
				
				v.getChildren().addAll(reviewdUser,title,description,tag,rate);
				v.setSpacing(5);
				v.setAlignment(Pos.CENTER);
				v.setMaxWidth(525);
				setGraphic(v);				
			}
			}
	}
	
	class RecRevCell extends ListCell<Review>{
		@Override
		public void updateItem(Review item, boolean empty) {
			super.updateItem(item,empty);
			if(!empty) {
				VBox v = new VBox();
				Label rate = new Label("Rate: " + String.valueOf(item.getRating()));
				Label description = new Label("Description: " + item.getDescription());
				Label title = new Label(item.getTitle());
				Label tag = new Label(item.getTag());
				
				description.setWrapText(true);
				// Link to the reviewing user
				Hyperlink reviewingUser = new Hyperlink();
				reviewingUser.setText(item.getReviewer().getPerson().getUsername());
				reviewingUser.setOnAction(e ->{
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(reviewingUser.getText())));
				});
				
				v.getChildren().addAll(reviewingUser,title,description,tag,rate);
				v.setSpacing(5);
				v.setAlignment(Pos.CENTER);
				v.setMaxWidth(525);
				setGraphic(v);
			}
		}
	}
	/*this method get all the done reviews by the current user and populate a local observable list*/
	private void setDone() {
		
		ReviewController revContr = ReviewController.getInstance();
		AccountBean accBean = new AccountBean();
		
		accBean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
		
		try {
			this.doneReviews = FXCollections.observableArrayList(revContr.getDoneReviews(accBean));
			
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	/*As above, but for the received reviews*/
	private void setReceived() {
		ReviewController revContr = ReviewController.getInstance();
		AccountBean accBean = new AccountBean();
		
		accBean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
		
		try {
			this.receivedReviews = FXCollections.observableArrayList(revContr.getReceivedReviews(accBean));
		
		}catch(DatabaseException de) {
			JOptionPane.showMessageDialog(null,de.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
}
