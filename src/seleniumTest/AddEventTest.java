package seleniumTest;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEventTest {
	
	WebDriver driver;
	@Before
	public void openLoginPage() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver","chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/ooad_basic/login.jsp");
		Thread.sleep(2000);
		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submit = driver.findElement(By.id("login"));
		email.sendKeys("root");
		Thread.sleep(2000);
		password.sendKeys("akshay");
		Thread.sleep(2000);
		submit.click();
		Thread.sleep(2000);
		
		
	}
	
	
	@Test
	public void testAddEvent() throws InterruptedException{
	
			WebElement addeventlink = driver.findElement(By.id("addevent"));
			addeventlink.click();
			Thread.sleep(2000);
			
			WebElement title_box = driver.findElement(By.id("title"));
			title_box.sendKeys("swimming");
			Thread.sleep(1000);
			
			WebElement type_box = driver.findElement(By.id("type"));
			type_box.sendKeys("sports");
			Thread.sleep(1000);
			
			WebElement date_box = driver.findElement(By.id("date"));
			date_box.sendKeys("07/30/2018");
			Thread.sleep(1000);
			
			WebElement loc_box = driver.findElement(By.id("loc"));
			loc_box.sendKeys("UTD Activity Center");
			Thread.sleep(1000);
			
			WebElement price_box = driver.findElement(By.id("price"));
			price_box.sendKeys("40");
			Thread.sleep(1000);
			
			WebElement time_box = driver.findElement(By.id("time"));
			time_box.sendKeys("3pm");
			Thread.sleep(1000);
			
			WebElement desc_box = driver.findElement(By.id("desc"));
			desc_box.sendKeys("swim matches");
			Thread.sleep(1000);
			
			WebElement add_eventlink = driver.findElement(By.id("add_event"));
			add_eventlink.click();
			Thread.sleep(2000);
			
			WebElement resultText = driver.findElement(By.id("result"));
			Assert.assertEquals("Event has been added", resultText.getText());
	
	}
	
	@After
	public void closePage(){
	driver.quit();
	}

}
