package test.fd;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestGroupCreation {
	WebDriver driver;
	@Test
	public void test() {
			System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
			
			driver=new ChromeDriver();
			
			driver.get("http://localhost:8080/DoveStudi.git/index.jsp");
			
	}
}