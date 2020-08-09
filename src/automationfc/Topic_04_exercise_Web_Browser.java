package automationfc;

import java.util.concurrent.TimeUnit;
//import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_exercise_Web_Browser {
	WebDriver driver;
	
	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}@Test

	public void TC_01_ID() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
		Thread.sleep(2000);
		
		
	
	}@Test

	public void TC_02_ID() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
		Thread.sleep(2000);
	}@Test

	public void TC_03_ID() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
		
		driver.navigate().back();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
		Thread.sleep(2000);
	}@Test

	public void TC_04_ID() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		
		String loginPageSource=driver.getPageSource();
		
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		
		String registerPageSource=driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
		Thread.sleep(2000);
}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	}
	
	


