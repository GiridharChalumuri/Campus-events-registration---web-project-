package seleniumTest;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class AdminLoginTest {
	
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
		WebElement submit = driver.findElement(By.id("login"));
		email.sendKeys("root");
		Thread.sleep(2000);
		password.sendKeys("akshay");
		Thread.sleep(2000);
		submit.click();
		Thread.sleep(2000);
		Assert.assertEquals("Admin Login Page", driver.getTitle());
		
	}
	
	@Test
	public void LoginFailTest() throws InterruptedException{
		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submit = driver.findElement(By.id("login"));
		email.sendKeys("root");
		Thread.sleep(2000);
		password.sendKeys("12345678");
		Thread.sleep(2000);
		submit.click();
		Thread.sleep(2000);
		Assert.assertEquals("Login Page", driver.getTitle());
		
	}
	
	
	
	@After
	public void closePage(){
	driver.quit();
	}

}
