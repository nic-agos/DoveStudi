package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.*;
import logic.controller.*;
import logic.exception.*;
import logic.model.*;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class MyRoomsGC implements Initializable {
	
	@FXML
	private BorderPane main;
	@FXML
	private ListView<Room> myRoomsList;
	
	private static final String ERROR = "ERROR";
	
	private ObservableList<Room> myRooms;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setRooms();
		if(!myRooms.isEmpty()) {
			myRoomsList.setItems(myRooms);
			myRoomsList.setCellFactory(list -> new RoomCell());
		}
	}
	
	class RoomCell extends ListCell<Room>{
		@Override
		public void updateItem(Room item, boolean empty) {
			super.updateItem(item,empty);
			if(!empty) {
				VBox v = new VBox();
				Label title = new Label("Room Name+ "+item.getName());
				Label description = new Label(item.getSpecification().getDescription());
				Label address = new Label ("Address: " + item.getAddress());
				Label cap = new Label("CAP: " + item.getSpecification().getCap());
				Label date = new Label("Date: " + item.getSpecification().getDate());
				Label start = new Label ("Start Time: " + item.getSpecification().getStartTime());
				Label end = new Label ("End Time: " + item.getSpecification().getEndTime());
				Label totalSeats = new Label ("Total Seats: "+ String.valueOf(item.getNumParticipants()));
				Label availableSeats = new Label ("Available Seats: "+ String.valueOf(item.getNumAvailableSeats()));
				ObservableList<Hyperlink> linkList = FXCollections.observableArrayList();
				
				for (Person p : item.getParticipants()) {
					Hyperlink link = new Hyperlink();
					link.setText(p.getUsername());
					link.setOnAction(e->{
						Stage stage = (Stage) main.getScene().getWindow();
						stage.setScene(ViewSwitcher.switchTo(Views.OTHERACCOUNT, new OtherAccountGC(link.getText())));
					});
					linkList.add(link);
				}
				
				ListView<Hyperlink> partecipants = new ListView<>();
				partecipants.setItems(linkList);
				partecipants.setOrientation(Orientation.HORIZONTAL);
				partecipants.setPrefHeight(25);
				partecipants.setMaxHeight(USE_PREF_SIZE);
				
				Button delete = new Button("Delete This Room");
				delete.setOnAction(e->{
					RoomBean bean = new RoomBean();
					bean.setId(item.getId());
					RoomController ctrl = RoomController.getInstance();
					try{
						ctrl.deleteRoom(bean);
					}catch (DatabaseException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
					}
					
					Stage stage = (Stage) main.getScene().getWindow();
					stage.setScene(ViewSwitcher.switchTo(Views.MYROOMS, null));
				});
				
				v.getChildren().addAll(title,description,address,cap,date,start,end,totalSeats,availableSeats,partecipants,delete);
				setGraphic(v);			
			}
		}
	}
	
	private void setRooms() {
		AccountBean bean = new AccountBean();
		bean.setCf(Session.getSession().getCurrUser().getAccount().getCf());
		RoomController ctrl = RoomController.getInstance();
		
		try {
			this.myRooms=FXCollections.observableArrayList(ctrl.getMyRooms(bean));
		}
		catch (DatabaseException ex1) {
			JOptionPane.showMessageDialog(null,ex1.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
}
