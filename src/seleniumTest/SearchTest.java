package seleniumTest;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class SearchTest {
	WebDriver driver;
	@Before
	public void openWelcomePage() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver","chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:8080/ooad_basic/login.jsp");
		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submit = driver.findElement(By.name("submit"));
		email.sendKeys("giridhar@gmail.com");
		password.sendKeys("1234");
		Thread.sleep(1000);
		submit.click();
				
	}
	@Test
	public void searchtest() throws InterruptedException{
		WebElement education_checkbox = driver.findElement(By.id("education"));
		education_checkbox.click();
		WebElement submit2 = driver.findElement(By.name("submit"));
		Thread.sleep(1000);
		submit2.click();
		List<WebElement> elements = driver.findElements(By.id("event-type"));
		java.util.Iterator<WebElement> i = elements.iterator();

		while(i.hasNext()){
		WebElement element = i.next();
		Assert.assertEquals("Education", element.getText());
		}

		
	}
    
    @After
	public void closePage(){
	driver.quit();
	}

}
