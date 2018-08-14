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

public class LoginTest {

	WebDriver driver;
	@Before
	public void openLoginPage() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver","chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/ooad_basic/login.jsp");
		Assert.assertEquals("Login Page", driver.getTitle());
		
	}
	
	@Test
	public void LoginSucessTest() throws InterruptedException{
		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submit = driver.findElement(By.name("submit"));
		email.sendKeys("giridhar@gmail.com");
		password.sendKeys("1234");
		Thread.sleep(5000);
		submit.click();
		Thread.sleep(5000);
		Assert.assertEquals("Welcome Page", driver.getTitle());
		
	}
	
	@Test
	public void LoginFailTest() throws InterruptedException{
		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submit = driver.findElement(By.name("submit"));
		email.sendKeys("giridhar@gmail.com");
		password.sendKeys("12345678");
		Thread.sleep(5000);
		submit.click();
		Thread.sleep(5000);
		Assert.assertEquals("Login Page", driver.getTitle());
		
	}
	
	
	@After
	public void closePage(){
	driver.quit();
	}
	

}
