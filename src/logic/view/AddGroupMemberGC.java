package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.bean.*;
import logic.controller.GroupController;
import logic.exception.*;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

/*Linked FXML file: */
public class AddGroupMemberGC implements Initializable{
	
	@FXML
	private BorderPane main;
	@FXML 
	private Button backBtn;
	@FXML
	private Button addBtn;
	@FXML
	private TextField usernameLbl;
	
	private static final String ERROR = "ERROR";
	
	private String groupName;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**/
		}
	
	public AddGroupMemberGC(String gName) {
		this.groupName = gName;
	}
	
	@FXML
	public void addMember() {
		
		GroupBean gBean = new GroupBean();
		PersonBean pBean = new PersonBean();
		boolean value;
		
		gBean.setAdmin(Session.getSession().getCurrUser().getAccount().getCf());
		gBean.setName(groupName);
		
		pBean.setUsername(usernameLbl.getText());
		
		try {
			
			GroupController gCtrl = GroupController.getInstance();
			value = gCtrl.addGroupParticipant(gBean, pBean);
			
			if(value) {
				Stage stage = (Stage) main.getScene().getWindow();
				stage.setScene(ViewSwitcher.switchTo(Views.MYGROUPS, null));
			}
			
		}catch(DatabaseException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}catch(AccountException ae) {
			JOptionPane.showMessageDialog(null,ae.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@FXML
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}
}
