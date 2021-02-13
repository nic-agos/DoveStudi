package test.fd;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logic.bean.AccountBean;
import logic.bean.GroupBean;
import logic.bean.PersonBean;
import logic.controller.GroupController;
import logic.controller.RegistrationController;
import logic.exception.DatabaseException;
import logic.model.dao.AccountDAOImpl;
import logic.model.dao.PersonDAOImpl;
/*
 * 
 * Test by @author Francesco Dalena
 * 					  matr. 0266977
 * 
 * */
public class TestGroupControllerDeleteGroup {
	int idAdmin;
	
	@Before
	public void init() {
		PersonBean adminPBean = new PersonBean("Maciste","Middle School", "Massaia", "WWVVDO87H12H625A",4,4);
		AccountBean adminABean = new AccountBean("WWVVDO87H12H625A","Di Nome", "Di Fatto", "maciste@gmail.com", "pincopallo", "1999/01/01",0);
		RegistrationController regCtrl = RegistrationController.getInstance();
		try {
			regCtrl.register(adminABean, adminPBean);
			idAdmin = PersonDAOImpl.getInstance().getPersonId(adminPBean);
		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
		}
		GroupBean testGroupBean = new GroupBean();
		GroupController groupTestCtrl = GroupController.getInstance();
		
		testGroupBean.setAdmin("WWVVDO87H12H625A");
		testGroupBean.setName("Best Group Ever");
		
		try {
			groupTestCtrl.createGroup(testGroupBean);
		}catch (DatabaseException e) {e.printStackTrace();}
	}
	
	@Test /*Make sure removing a group does remove it from the database*/
	public void testDeleteGroup() {
		GroupBean testGroupBean = new GroupBean();
		GroupController groupTestCtrl = GroupController.getInstance();
		testGroupBean.setAdmin("WWVVDO87H12H625A");
		testGroupBean.setName("Best Group Ever");
		
		boolean value=false;
		try {
			value = groupTestCtrl.deleteGroup(testGroupBean);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertEquals(true,value);		
	}
	
	
	@After /* Delete the two accounts created*/
	public void tearDown() {
		AccountBean adminABean = new AccountBean("WWVVDO87H12H625A","Di Nome", "Di Fatto", "maciste@gmail.com", "pincopallo", "1999/01/01",0);
		try {
			AccountDAOImpl.getInstance().removeAccount(adminABean);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
