package test.fm;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import logic.bean.AccountBean;
import logic.bean.PersonBean;
import logic.bean.RoomBean;
import logic.bean.RoomSpecBean;
import logic.controller.RegistrationController;
import logic.controller.RoomController;
import logic.exception.DatabaseException;
import logic.exception.RoomException;
import logic.model.dao.AccountDAOImpl;
import logic.model.dao.PersonDAOImpl;
import logic.model.dao.RoomDAOImpl;

/*
 * 
 * Test by @author Flavia Magnoni
 * 				    matr. 0228318
 * 
 * */

public class SeleniumTestMakeReservation {
	
	
	static final String PASSWORD_1="seleniumTest1";
	static final String PASSWORD_2="seleniumTest2";
	static final String EMAIL_1="mario97@gmail.com";
	static final String EMAIL_2="marta96@gmail.com";
	
	static int resId;
	static int persId;
	
	WebDriver driver;
	
	/* Initialize: create two accounts and post a room */
	@Before
	public void initialize() {
		RegistrationController regContr = RegistrationController.getInstance();
		
		PersonDAOImpl personDAO = PersonDAOImpl.getInstance();
		
		RoomController roomContr = RoomController.getInstance();
		
		AccountBean a1 = new AccountBean("RSSMTN02T18F205M", "Mario", "Rossi", EMAIL_1, PASSWORD_1, "1997:08:27", 0);
		PersonBean p1 = new PersonBean("mario97", "High School", "AB", "RSSMTN02T18F205M", 0, 0);
		
		AccountBean a2 = new AccountBean("MRUFBL08R47A662B", "Marta", "Verdi", EMAIL_2, PASSWORD_2, "1996:07:24", 0);
		PersonBean p2 = new PersonBean("marta83", "High School", "CD", "MRUFBL08R47A662B", 0, 0);
		
		
		RoomBean rBean = new RoomBean();
		rBean.setName("Stanza al centro di Roma");
		rBean.setAddress("Via del Corso");
		rBean.setNumParticipants(3);
		rBean.setOwner("MRUFBL08R47A662B");

		RoomSpecBean rs = new RoomSpecBean();
		rs.setCap("00186");
		rs.setDescription("Studente di ingegneria. Cerco un gruppo di studio con cui studiare materie del primo anno. Stanza provvista di Wifi.");
		rs.setDate("2021-03-03");
		rs.setStartTime("16:00");
		rs.setEndTime("20:00");
		
		try {
			
			regContr.register(a1, p1);
			regContr.register(a2, p2);
			
			persId = (personDAO.getPersonId(p2));
			
			roomContr.postRoom(rBean, rs);
			
		}catch (RoomException e) {
			e.printStackTrace();
		}
		catch (DatabaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* Test of makeReservation */
	@Test
	public void makeReservationTest() {
			System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
			
			driver=new ChromeDriver();
			
			driver.get("http://localhost:8080/DoveStudi.git/index.jsp");
			
			driver.findElement(By.xpath("/html/body/div[2]/div/button[2]/a")).click();
			driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(EMAIL_1);
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(PASSWORD_1);
			driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();
			
			driver.get("http://localhost:8080/DoveStudi.git/SearchRooms.jsp");
			
			driver.findElement(By.xpath("/html/body/div[7]/div/form/button")).click();
			WebElement roomResName = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div[1]/div[2]/p"));
			assertEquals("Stanza al centro di Roma", roomResName.getText());
			
			driver.close();
	}
	
	/* Delete the accounts and the room which have been created*/
	@After 
	public void destroy() {
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		
		AccountBean a1 = new AccountBean();
		AccountBean a2 = new AccountBean();
		
		a1.setCf("RSSMTN02T18F205M");
		a2.setCf("MRUFBL08R47A662B");
		
		RoomDAOImpl rDAO = RoomDAOImpl.getInstance();
		RoomBean roomBean = new RoomBean();
		roomBean.setName("Stanza al centro di Roma");
		try {
			roomBean.setId(rDAO.getRoomId(roomBean));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		RoomController rContr = RoomController.getInstance();
		try {
			rContr.deleteRoom(roomBean);
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}
		
		try {
			accountDao.removeAccount(a1);
			accountDao.removeAccount(a2);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
