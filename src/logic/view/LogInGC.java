package logic.view;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.bean.AccountBean;
import logic.controller.LoginController;
import logic.exception.*;
import logic.model.Person;
import logic.util.Session;
import logic.util.ViewSwitcher;
import logic.util.enumeration.Views;
import javax.swing.JOptionPane;

/*Linked FXML file: Login.fxml*/
public class LogInGC implements Initializable{
	
	@FXML
	private Button backBtn;
	@FXML
	private Button logInBtn;
	@FXML
	private Button regBtn;
	@FXML 
	private BorderPane main;
	@FXML
	private TextField emailField;
	@FXML
	private PasswordField pswLbl;
	
	private static final String ERROR = "ERROR";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*nothing to do here for now*/
	}
	
	@FXML /*Action associated to the logInBtn*/
	public void logIn() {
		Person p;
		AccountBean bean = new AccountBean();
		bean.setEmail(emailField.getText());
		bean.setPassword(pswLbl.getText());		
		LoginController ctrl = LoginController.getInstance();
		
		try {
			p=ctrl.login(bean);
			if(p!=null) {
				//Update session informations
				Session.getSession().setCurrUser(p);
				Session.getSession().setLogged(true);
				
				Stage stage = (Stage) main.getScene().getWindow();
				stage.setScene(ViewSwitcher.switchTo(Views.MYACCOUNT,null));
			}
		}
		catch(LoginException e){
			JOptionPane.showMessageDialog(null,e.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
		catch(DatabaseException ex){
			JOptionPane.showMessageDialog(null,ex.getMessage(),ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@FXML /*Action associated to the backBtn*/
	public void back() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.HOME, null));
	}
	
	@FXML /*Action associated to the regBtn*/
	public void registration() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(ViewSwitcher.switchTo(Views.REGISTRATION,null));
	}		
}
