package automationfc;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Pop_Up {
	WebDriver driver;
	WebDriverWait explicitWait;
	boolean status;
	
	//@BeforeClass
	//public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		//driver = new ChromeDriver();
		//explicitWait= new WebDriverWait(driver,30);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		
		//jsExecutor=(JavascriptExecutor) driver;

	//}
	
	
	

	public void TC_01_popup_fix()  {
		driver.get("http://zingpoll.com");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("Loginform")));
		sleepInSecond(2);
		driver.findElement(By.id("Loginform")).click();
		sleepInSecond(2);
		
		
		//login popup hiển thị
		status= driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Login popup hiển thị=" +status);
		Assert.assertTrue(status);
		
		//click để close pop up
		driver.findElement(By.cssSelector("#Login .close")).click();
		sleepInSecond(2);
		
		//login pop up ko hiển thị
		status= driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Login popup hiển thị=" +status);
		Assert.assertFalse(status);
		
		sleepInSecond(2);
		driver.findElement(By.id("Loginform")).click();
		driver.findElement(By.id("loginEmail")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("loginPassword")).sendKeys("automationfc");
		driver.findElement(By.id("button-login")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='username' and contains(text(), 'Automation Testing')]")).isDisplayed());
		
		
	
	
	}
	@Test

	public void TC_02_popup_random()  {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		explicitWait= new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://blog.testproject.io");
		sleepInSecond(5);
		
		if(driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			//ktr sign up button hieenr thij
			Assert.assertTrue(driver.findElement(By.cssSelector(".right-arr.lazyloaded")).isDisplayed());
			//close pop up
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSecond(2);
			
			//step 2
			driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("Selenium");
			
			//step 3 click search icon
			driver.findElement(By.cssSelector("#search-2 .glass")).click();
			
			//step 4 verify Selenium in title
			List<WebElement> allArticleTitle= driver.findElements(By.cssSelector(".post-title"));
			for (WebElement article: allArticleTitle) {
				String articleText= article.getText().trim();
				Assert.assertTrue(articleText.contains("Selenium"));;
			}
			
			
		}
	
	}
	
	

	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time *1000);
			
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
