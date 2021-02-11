package test.fd;
import org.junit.Test;

import logic.controller.RegistrationController;
import logic.exception.AccountException;
import logic.exception.DatabaseException;
import logic.model.Account;
import logic.model.Group;
import logic.model.dao.AccountDAOImpl;
import logic.model.dao.GroupDAOImpl;
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
 * 
 * */
public class TestGroupController {
	GroupBean testGroupBean;
	Group testGroup;
	GroupController groupTestCtrl;
	Account testAccount;
	PersonBean adminPBean;
	AccountBean adminABean;
	int idAdmin;
	PersonBean partecipantPBean;
	AccountBean partecipantABean;
	int idPartecipant;
	
	@Before /* Create two accounts to work with */
	public void init() {
		adminPBean = new PersonBean("Maciste","Middle School", "Massaia", "WWVVDO87H12H625A",4,4);
		adminABean = new AccountBean("WWVVDO87H12H625A","Di Nome", "Di Fatto", "maciste@gmail.com", "pincopallo", "1999/01/01",0);
		RegistrationController regCtrl = RegistrationController.getInstance();
		try {
			regCtrl.register(adminABean, adminPBean);
			idAdmin = PersonDAOImpl.getInstance().getPersonId(adminPBean);
		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
		}
		partecipantPBean = new PersonBean("Fragola86","University", "Sapienza", "WWVVDO47H12H625A",3,2);
		partecipantABean = new AccountBean("WWVVDO47H12H625A","Maria", "Dei Monti", "fragola86@gmail.com", "pincopallo", "1999/01/02",0);
		try {
			regCtrl.register(partecipantABean, partecipantPBean);
			idPartecipant = PersonDAOImpl.getInstance().getPersonId(partecipantPBean);
		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@After /* Delete the two accounts created*/
	public void tearDown() {
		try {
			AccountDAOImpl.getInstance().removeAccount(adminABean);
			AccountDAOImpl.getInstance().removeAccount(partecipantABean);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	@Test /* Make sure a new created group has only one member*/
	public void testCreateGroupDefaultSize() {
		groupTestCtrl = GroupController.getInstance();
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
	
	
	
}
