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

public class CartTest {
	
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
		Thread.sleep(2000);
		submit.click();
		WebElement eventSelected = driver.findElement(By.id("event1"));
		topicSelected = eventSelected.getText();
		eventSelected.click();
	}
	
	@Test
	public void addToCartTest() throws InterruptedException{
		WebElement addToCart = driver.findElement(By.id("addCart"));
		addToCart.click();
		Thread.sleep(2000);
		Assert.assertEquals("Cart", driver.getTitle());
		List<WebElement> cartEvents = driver.findElements(By.name("eventsInCart"));
		Iterator i = cartEvents.iterator();
		boolean flag = false;
		while(i.hasNext()){
			WebElement cartItem = (WebElement) i.next();
			if(cartItem.getText().equals(topicSelected)){
				flag = true;
			}
		}
		Assert.assertEquals(true, flag);
	}
	
	@Test
	public void removeFromCartTest() throws InterruptedException{
		WebElement addToCart = driver.findElement(By.name("mode"));
		addToCart.click();
		Thread.sleep(2000);
		Assert.assertEquals("Cart", driver.getTitle());
		WebElement eventToBeDeleted = driver.findElement(By.id("eventsInCart1"));
		String eventToDelete = eventToBeDeleted.getText();
		WebElement deleteButton = driver.findElement(By.id("delete1"));
		deleteButton.click();
		Thread.sleep(1000);
		List<WebElement> cartEvents = driver.findElements(By.name("eventsInCart"));
		Iterator i = cartEvents.iterator();
		boolean flag = false;
		while(i.hasNext()){
			WebElement cartItem = (WebElement) i.next();
			if(cartItem.getText().equals(eventToDelete)){
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
