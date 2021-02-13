package test.fd;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import logic.bean.AccountBean;
import logic.bean.GroupBean;
import logic.bean.PersonBean;
import logic.controller.GroupController;
import logic.controller.RegistrationController;
import logic.exception.DatabaseException;
import logic.model.dao.AccountDAOImpl;
/*
 * 
 * Test by @author Francesco Dalena
 * 					  matr. 0266977
 * 
 * */

public class SeleniumTestGroupCreation {
	static final String PASSWORD="pincopallo";
	static final String EMAIL="maciste@gmail.com";
	static final String GROUPNAME="TestGroup";
	WebDriver driver;
	@Before
	public void init() {
		PersonBean adminPBean = new PersonBean("Maciste","Middle School", "Massaia", "WWVVDO87H12H625A",4,4);
		AccountBean adminABean = new AccountBean("WWVVDO87H12H625A","Di Nome", "Di Fatto", EMAIL, PASSWORD, "1999/01/01",0);
		RegistrationController regCtrl = RegistrationController.getInstance();
		try {
			regCtrl.register(adminABean, adminPBean);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
			System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
			
			driver=new ChromeDriver();
			
			driver.get("http://localhost:8080/DoveStudi.git/index.jsp");
			
			driver.findElement(By.xpath("/html/body/div[2]/div/button[2]/a")).click();
			driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(EMAIL);
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(PASSWORD);
			driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();
			
			driver.get("http://localhost:8080/DoveStudi.git/MyGroups.jsp");

			driver.findElement(By.xpath("/html/body/div[3]/form/button")).click();
			driver.findElement(By.xpath("//*[@id=\"groupName\"]")).sendKeys(GROUPNAME);
			driver.findElement(By.xpath("/html/body/div[3]/form/div[2]/div/button")).click();
			
			WebElement txtBoxContent = driver.findElement(By.xpath("//*[@id=\"myCard\"]"));
			
			assertEquals(GROUPNAME,txtBoxContent.getText());
			
			driver.close();
	}
	
	@After /* Delete the two accounts created*/
	public void tearDown() {
		AccountBean adminABean = new AccountBean("WWVVDO87H12H625A","Di Nome", "Di Fatto", "maciste@gmail.com", "pincopallo", "1999/01/01",0);
		try {
			AccountDAOImpl.getInstance().removeAccount(adminABean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		GroupBean gBean=new GroupBean();
		gBean.setAdmin("WWVVDO87H12H625A");
		gBean.setName(GROUPNAME);
		try {
			GroupController.getInstance().deleteGroup(gBean);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		
	}
}