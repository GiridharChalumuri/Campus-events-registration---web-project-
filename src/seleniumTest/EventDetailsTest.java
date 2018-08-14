package seleniumTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EventDetailsTest {

	WebDriver driver;
	@Before
	public void openLoginPage() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver","chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/ooad_basic/login.jsp");
		Assert.assertEquals("Login Page", driver.getTitle());
		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submit = driver.findElement(By.name("submit"));
		email.sendKeys("giridhar@gmail.com");
		password.sendKeys("1234");
		Thread.sleep(2000);
		submit.click();
	}
	
	@Test
	public void openDetails() throws InterruptedException{
		WebElement eventSelected = driver.findElement(By.id("event1"));
		String topicSelected = eventSelected.getText();
		eventSelected.click();
		Assert.assertEquals("Details Page", driver.getTitle());
		WebElement eventDisplayed = driver.findElement(By.name("topic"));
		String topicReceived = eventDisplayed.getAttribute("value");
		Assert.assertEquals(topicSelected, topicReceived);
		
	}
    
    @After
	public void closePage(){
	driver.quit();
	}
	
	

}
