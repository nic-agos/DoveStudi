package test.fd;
import org.junit.Test;

import logic.controller.RegistrationController;

import logic.exception.DatabaseException;
import logic.model.dao.AccountDAOImpl;

import logic.model.dao.PersonDAOImpl;
import logic.bean.AccountBean;
import logic.bean.GroupBean;
import logic.bean.PersonBean;
import logic.controller.GroupController;


import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;

/*
 * 
 * TESTING BY @author Francesco Dalena
 * 					  matr. 0266977
 * 
 * */
public class TestGroupControllerCreateGroupDefaultSize {
	
	int idAdmin;

	
	@Before /* Create two accounts to work with */
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
	}
	
	@Test /* Make sure a new created group has only one member*/
	public void testCreateGroupDefaultSize() {
		GroupBean testGroupBean = new GroupBean();
		GroupController groupTestCtrl = GroupController.getInstance();
		
		testGroupBean.setAdmin("WWVVDO87H12H625A");
		testGroupBean.setName("Best Group Ever");
		int size=0;
		try {
			groupTestCtrl.createGroup(testGroupBean);
			size = groupTestCtrl.getGroupParticipants(testGroupBean).size();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertEquals(1,size);	
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
