package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.bean.RoomBean;
import logic.bean.RoomSpecBean;
import logic.controller.RoomController;
import logic.exception.DatabaseException;
import logic.exception.RoomException;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

public class PostRoomGC implements Initializable{
	
	@FXML
	private Button postBtn;
	@FXML 
	private Button backBtn;
	@FXML
	private BorderPane main;
	@FXML
	private TextField roomName;
	@FXML
	private TextField roomAddress;
	@FXML
	private TextField capLbl;
	@FXML
	private DatePicker date;
	@FXML
	private TextField seatLbl;
	@FXML
	private TextArea description;
	@FXML
	private ChoiceBox<String> start;
	@FXML
	private ChoiceBox<String> end;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		start.getItems().addAll("7:00","7:30","7:00","7:30","7:00","7:30","7:00","7:30","7:00","7:30","7:00","7:30","7:30","7:30","7:30","7:30","7:30","7:30","7:30","7:30","7:30","7:00","7:30","7:00","7:30","7:00","7:30","7:00");
		end.getItems().addAll("7:00","7:30","7:00","7:30","7:00","7:30","7:00","7:30","7:00","7:30","7:00","7:30","7:30","7:30","7:30","7:30","7:30","7:30","7:30","7:30","7:30","7:00","7:30","7:00","7:30","7:00","7:30","7:00");
	}
	
	@FXML
	public void postRoom() {
		RoomBean rBean = new RoomBean();
		RoomSpecBean rSBean = new RoomSpecBean();
		
		rBean.setName(roomName.getText());
		rBean.setAddress(roomAddress.getText());
		if(seatLbl.getText() == "") {
			rBean.setNumParticipants(0);
		}else {
			rBean.setNumParticipants(Integer.parseInt(seatLbl.getText()));
		}
		//rBean.setOwner(Session.getSession().getCurrUser().getAccount().getCf());
		
		rSBean.setDescription(description.getText());
		rSBean.setDate(String.valueOf(date.getValue()));
		rSBean.setCap(capLbl.getText());
		rSBean.setStartTime(start.getValue());
		rSBean.setEndTime(end.getValue());
		try {
			rBean.validate();
			rSBean.validate();
			
			RoomController ctrl = RoomController.getInstance();
			boolean value = ctrl.postRoom(rBean, rSBean);
			if(value) {
				Stage stage = (Stage) main.getScene().getWindow();
				stage.setScene(ViewSwitcher.switchTo(Views.MYROOMS, null));
			}
		}
		catch(RoomException e) {
			 JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		catch(DatabaseException ex){
			 JOptionPane.showMessageDialog(null,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	@FXML
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
}
