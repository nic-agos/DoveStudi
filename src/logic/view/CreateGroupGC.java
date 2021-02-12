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
import logic.bean.GroupBean;
import logic.controller.GroupController;
import logic.exception.*;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;

/*Linked FXML file: */
public class CreateGroupGC implements Initializable{
	
	@FXML
	private Button backBtn;
	@FXML
	private Button createBtn;
	@FXML
	private BorderPane main;
	@FXML
	private TextField nameField;
	
	private static final String ERROR = "ERROR";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*nothing to do here for now*/
	}
	
	@FXML
	public void createGroup() {
		GroupBean gBean = new GroupBean();
		boolean value;
		gBean.setName(nameField.getText());
		gBean.setAdmin(Session.getSession().getCurrUser().getAccount().getCf());
		
		try {
			gBean.validate();
			
			GroupController gCtrl = GroupController.getInstance();
			
			value = gCtrl.createGroup(gBean);
			
			if(value) {
				Stage stage = (Stage) main.getScene().getWindow();
				stage.setScene(ViewSwitcher.switchTo(Views.MYGROUPS, null));
			}
			
		}catch(DatabaseException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}catch(GroupException ge) {
			JOptionPane.showMessageDialog(null,ge.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@FXML
	public void backAction() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.back());
	}

}
