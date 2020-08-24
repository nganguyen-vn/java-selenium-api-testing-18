package automationfc;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert_popup {
	WebDriver driver;
	Alert alert;
	
	@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}@Test
	
	public void TC_01_Jquery() throws InterruptedException {
		driver.get("http://demo.guru99.com/v4/index.php");
		
		//click login
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		sleepInSecond(2);
		
		// Switch vao Alert
		alert= driver.switchTo().alert();
		
		//get text của alert
		System.out.println("Alert text");
		
		//send text vào alert
		//alert.sendKeys("");
		
		//accept
		alert.accept();
		sleepInSecond(2);
		
		//cancel
		//alert.dismiss();
		
		
		
	}@Test	
	
	public void TC_02_Jquery() throws InterruptedException {
		driver.get("");
		
		//click login
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		sleepInSecond(2);
		
		// Switch vao Alert
		alert= driver.switchTo().alert();
		
		//get text của alert
		System.out.println("Alert text");
		
		//send text vào alert
		//alert.sendKeys("");
		
		//accept
		alert.accept();
		sleepInSecond(2);
		
		//cancel
		//alert.dismiss();
		
		
		
	}
	@Test
	public void TC_03_AuthenAlert() throws InterruptedException {
		String username="admin";
		String password="admin";
		
		//xác thực qua đường link không bật alert
		driver.get("http://" + username+":"+ password+"@"+ "the-internet.herokuapp.com/basic_auth");
		sleepInSecond(2);
		
		//by pass alert
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
		
		
		
		
		
		
	}
	@Test
	public void TC_04_AuthenAlert() throws InterruptedException {
		//không biết URL là gì
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		
		//click to basic Auth link
		
		
		//get link href
		String basicAuthenLink= driver.findElement(By.xpath("//a[text()=' Basic Auth']")).getAttribute("href");
		handleAuthenticationAlert(basicAuthenLink, username, password);
			
	}

	
	public void handleAuthenticationAlert(String link, String username, String password) {
		//link= http://the-internet.herokuapp.com/basic_auth
		String splitLink[]= link.split("//");
		link = splitLink[0]+ "//"+ username + ":"+ password+ "@"+ splitLink[1];
		driver.get(link);
		
	}
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time *1000);
			
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

