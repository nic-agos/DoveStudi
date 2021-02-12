package test.fd;
import org.junit.Test;

import logic.controller.RegistrationController;
import logic.exception.AccountException;
import logic.exception.DatabaseException;
import logic.model.dao.AccountDAOImpl;
import logic.model.dao.GroupDAOImpl;
import logic.model.dao.PersonDAOImpl;
import logic.bean.AccountBean;
import logic.bean.GroupBean;
import logic.bean.PersonBean;
import logic.controller.GroupController;


import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
/*
 * 
 * TESTING BY @author Francesco Dalena
 * 					  matr. 0266977
 * 
 * */
public class TestGroupController {
	
	static int idAdmin;
	static int idPartecipant;
	
	@BeforeClass /* Create two accounts to work with */
	public static void init() {
		PersonBean adminPBean = new PersonBean("Maciste","Middle School", "Massaia", "WWVVDO87H12H625A",4,4);
		AccountBean adminABean = new AccountBean("WWVVDO87H12H625A","Di Nome", "Di Fatto", "maciste@gmail.com", "pincopallo", "1999/01/01",0);
		RegistrationController regCtrl = RegistrationController.getInstance();
		try {
			regCtrl.register(adminABean, adminPBean);
			idAdmin = PersonDAOImpl.getInstance().getPersonId(adminPBean);
		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
		}
		PersonBean partecipantPBean = new PersonBean("Fragola86","University", "Sapienza", "WWVVDO47H12H625A",3,2);
		AccountBean partecipantABean = new AccountBean("WWVVDO47H12H625A","Maria", "Dei Monti", "fragola86@gmail.com", "pincopallo", "1999/01/02",0);
		try {
			regCtrl.register(partecipantABean, partecipantPBean);
			idPartecipant = PersonDAOImpl.getInstance().getPersonId(partecipantPBean);
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
	
	@Test /*Make sure adding a member generate a size increment by 1*/
	public void testAddGroupPartecipantSize() {
		GroupBean testGroupBean = new GroupBean();
		GroupController groupTestCtrl = GroupController.getInstance();
		PersonBean partecipantPBean = new PersonBean("Fragola86","University", "Sapienza", "WWVVDO47H12H625A",3,2);
		
		testGroupBean.setAdmin("WWVVDO87H12H625A");
		testGroupBean.setName("Best Group Ever");
		
		int oldPartecipants=0;
		int newPartecipants=0;
		try {
			oldPartecipants = groupTestCtrl.getGroupParticipants(testGroupBean).size();
			groupTestCtrl.addGroupParticipant(testGroupBean, partecipantPBean);
			newPartecipants = groupTestCtrl.getGroupParticipants(testGroupBean).size();
		} catch (DatabaseException |AccountException e) {
			e.printStackTrace();
		}
		assertEquals(oldPartecipants+1,newPartecipants);		
	}
	
	@Test /*Make sure removing a group does remove it from the database*/
	public void testDeleteGroup() {
		GroupBean testGroupBean = new GroupBean();
		GroupController groupTestCtrl = GroupController.getInstance();
		testGroupBean.setAdmin("WWVVDO87H12H625A");
		testGroupBean.setName("Best Group Ever");
		
		boolean value = true;
		try {
			groupTestCtrl.deleteGroup(testGroupBean);
			for(GroupBean gb: GroupDAOImpl.getInstance().getAllGroups())
				if(!gb.equals(testGroupBean)) {
					value=false;
				}
		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
		}
		assertEquals(false,value);		
	}
	
	@AfterClass /* Delete the two accounts created*/
	public static void tearDown() {
		AccountBean adminABean = new AccountBean("WWVVDO87H12H625A","Di Nome", "Di Fatto", "maciste@gmail.com", "pincopallo", "1999/01/01",0);
		AccountBean partecipantABean = new AccountBean("WWVVDO47H12H625A","Maria", "Dei Monti", "fragola86@gmail.com", "pincopallo", "1999/01/02",0);
		try {
			AccountDAOImpl.getInstance().removeAccount(adminABean);
			AccountDAOImpl.getInstance().removeAccount(partecipantABean);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	
	
}
