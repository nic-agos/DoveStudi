package test.na;

import java.sql.SQLException;


import org.junit.BeforeClass;
import org.junit.AfterClass;

import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import logic.bean.*;
import logic.controller.*;
import logic.exception.*;
import logic.model.dao.*;

/*
 * Selenium test class Agostinelli Niccolo' matr. 0269776
 */

public class SeleniumTestMakeReview {
	
	WebDriver driver;
	static int roomId = 0;
	
//	method to create two account on the db with one room and one past reservation
	@BeforeClass
	public static void initialize() {
		RegistrationController regContr = RegistrationController.getInstance();
		ReservationController resContr = ReservationController.getInstance();
		RoomController roomContr = RoomController.getInstance();
		
		RoomDAOImpl roomDao = RoomDAOImpl.getInstance();
		
		AccountBean a1 = new AccountBean("TTTFNC76P27H501J", "Francesco", "Totti", "francesco.totti@gmail.com", "magicaroma", "1976:09:27", 0);
		PersonBean p1 = new PersonBean("pupone76", "High School", "Trigoria", "TTTFNC76P27H501J", 0, 0);
		
		AccountBean a2 = new AccountBean("DRSDNL83L24H501X", "Daniele", "De Rossi", "daniele.derossi@gmail.com", "magicaroma", "1983:07:24", 0);
		PersonBean p2 = new PersonBean("danielino83", "High School", "Trigoria", "DRSDNL83L24H501X", 0, 0);
		
		RoomBean roomBean = new RoomBean();
		roomBean.setName("bella stanza");
		roomBean.setAddress("via dei monti parioli 10");
		roomBean.setNumParticipants(5);
		roomBean.setOwner("TTTFNC76P27H501J");

		RoomSpecBean roomSpecBean = new RoomSpecBean();
		roomSpecBean.setCap("00197");
		roomSpecBean.setDescription("Stanza spaziosa e tranquilla con wi-fi disponibile");
		roomSpecBean.setDate("2021-02-11");
		roomSpecBean.setStartTime("15:00");
		roomSpecBean.setEndTime("19:00");
		
		
		
		AccountBean reservingUser = new AccountBean();
		reservingUser.setCf("DRSDNL83L24H501X");
		
		RoomBean tempRoomBean = new RoomBean();
		
		try {
			
			regContr.register(a1, p1);
			regContr.register(a2, p2);
			
			roomContr.postRoom(roomBean, roomSpecBean);
			
			roomId = roomDao.getRoomId(roomBean);
			
			tempRoomBean.setId(roomId);
			
			resContr.makeReservation(tempRoomBean, reservingUser);
			
			
		}catch(DatabaseException | RoomException | SQLException | AccountException | ReservationException e) {
			e.printStackTrace();
		} 
	}

//	tests the correct creation of a review via web gui
	@Test
	public void testMakeReview() {
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.get("http://localhost:8080/DoveStudi.git/index.jsp");
		driver.findElement(By.xpath("/html/body/div[2]/div/button[2]/a")).click();
		driver.findElement(By.xpath("/html/body/div[3]/form/div[1]/div/div/input")).sendKeys("daniele.derossi@gmail.com");
		driver.findElement(By.xpath("/html/body/div[3]/form/div[2]/div/div/input")).sendKeys("magicaroma");
		driver.findElement(By.xpath("/html/body/div[3]/form/div[4]/button")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div/div")).click();
		driver.get("http://localhost:8080/DoveStudi.git/AccountMyPastReservations.jsp");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div/div[2]/div[2]/form/button")).click();
		driver.findElement(By.xpath("/html/body/div[4]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div/form/button")).click();
		driver.findElement(By.xpath("/html/body/div[3]/form/div[3]/div[2]/input")).sendKeys("Bel Giocatore");
		driver.findElement(By.xpath("/html/body/div[3]/form/div[4]/div/textarea")).sendKeys("Grande giocatore simbolo di Roma");
		driver.findElement(By.xpath("/html/body/div[3]/form/div[6]/div/button")).click();
		
		WebElement txtBoxContent = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/div[2]/div/div[2]/div[4]/p"));
		int rating = Integer.parseInt(txtBoxContent.getText());
		
		assertEquals(1, rating);

	}
	

	@AfterClass
	public static void destroy() {
		
		AccountDAOImpl accountDao = AccountDAOImpl.getInstance();
		RoomController rContr = RoomController.getInstance();
		
		AccountBean a1 = new AccountBean();
		AccountBean a2 = new AccountBean();
		RoomBean r1 = new RoomBean();
		
		a1.setCf("TTTFNC76P27H501J");
		a2.setCf("DRSDNL83L24H501X");
		
		r1.setId(roomId);
		try {
			
			rContr.deleteRoom(r1);
			accountDao.removeAccount(a1);
			accountDao.removeAccount(a2);
			
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}
	
}
