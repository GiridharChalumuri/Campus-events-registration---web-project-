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

public class checkoutTest {

	String topicSelected;

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
		Thread.sleep(1000);
		submit.click();
		WebElement eventSelected = driver.findElement(By.id("event1"));
		topicSelected = eventSelected.getText();
		eventSelected.click();
		WebElement addToCart = driver.findElement(By.name("mode"));
		addToCart.click();
		Thread.sleep(1000);
	}
	@Test
	public void checkout_Test() throws InterruptedException{
		WebElement checkout = driver.findElement(By.id("checkout"));
		checkout.click();
		Thread.sleep(1000);
		Assert.assertEquals("Thank You Page", driver.getTitle());
	}
    
    @After
	public void closePage(){
	driver.quit();
	}
}
