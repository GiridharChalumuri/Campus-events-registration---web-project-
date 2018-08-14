package seleniumTest;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CancelReservationTest {

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
		WebElement checkout = driver.findElement(By.id("checkout"));
		checkout.click();
		Thread.sleep(1000);
	}
	@Test
	public void cancelreservatio() throws InterruptedException{
		WebElement myreservation = driver.findElement(By.id("myreservation"));
		myreservation.click();
		Thread.sleep(1000);
		WebElement cancel = driver.findElement(By.id("topic1"));
		String canceled = cancel.getText();
		WebElement cancelbutton = driver.findElement(By.id("cancel1"));
		cancelbutton.click();
		List<WebElement> reservationEvents = driver.findElements(By.name("topic"));
		Iterator i = reservationEvents.iterator();
		boolean flag = false;
		while(i.hasNext()){
			WebElement reservationItem = (WebElement) i.next();
			if(reservationItem.getText().equals(canceled)){
				flag = true;
			}
		}
		Assert.assertNotEquals(true, flag);
	}
    
    @After
	public void closePage(){
	driver.quit();
	}

}
