package automationfc;
import java.util.List;
import java.util.Set;
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

public class Topic_12_iFrame {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		//explicitWait= new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		jsExecutor=(JavascriptExecutor) driver;

	}
	
	public void TC_01_iFrame()  {
		driver.get("https://kyna.vn");
		
		//switch vào iframe sd index
		//driver.switchTo().frame(1);
		
		//switch vào iframe dùng xpath
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframge")));
		
		String facebookLike= driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(facebookLike, "169k likes");
		
		//switch to default content
		driver.switchTo().defaultContent();
		
		//check search textbox hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='live-search-bar']")).isDisplayed());
		
		//switch to webchat
		driver.switchTo().frame("cs_chat_iframe");
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".chat-area")).isDisplayed());
		
		//input text to area
		driver.findElement(By.tagName("textarea")).sendKeys("Selenium");
		driver.findElement(By.tagName("textarea")).sendKeys(Keys.ENTER);
		Assert.assertTrue(driver.findElement(By.id("sign-in-menu")).isDisplayed());
		sleepInSecond(3);
		
		//switch to default content
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Java");
		driver.findElement(By.cssSelector(".search-button")).click();
		Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "'Java'");
	}
	@Test
	public void TC_02_window()  {
		driver.get("https://kyna.vn");
		
		//lấy ra id tại tab có driver đang đứng
		String parentID= driver.getWindowHandles();
		System.out.println("Parent ID= "+parentID);
		
		//click to vietnamwork link tại pre-footer
		clickToElementByJS("//img[@alt='vietnamwork.com']");
		sleepInSecond(3);
		
		switchToWindowByID (parentID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.vietnamworks.com/?utm_source=from_kyna");
		
		
		Set<String> allWindowIDs= driver.getWindowHandles();
		for (String windowID: allWindowIDs) {
			System.out.println(windowID);
		}
		
		//driver.findElement(By.xpath("//img[@alt='vietnamwork.com']")).click();
		//sleepInSecond(2);
		
		//Assert.assertEquals(driver.getCurrentUrl(), "Tuyển dụng việc làm, tìm việc làm nhanh mới nhất | Vietnamwork");
		
		
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time *1000);
			
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void clickToElementByJS (String locator) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
	}
	public void switchToWindowByID(String parentID) {
		//lấy ra tất cả các ID của window đang có
		Set<String> allWindows= driver.getWindowHandles();
		
		//dùng vòng lặp để duyệt qua từng ID
		for (String runWindow: allWindows) {
			
			//ktra cái ID nào mà khác với parent
			if(!runWindow.equals(parentID)) {
				
				// switch vào ID đó
				driver.switchTo().window(runWindow);
				
				//thoát khỏi vòng lặp khi thỏa mãn điều kiện
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(String title) {
		Set<String> allWindows= driver.getWindowHandles();
		for (String runWindow: allWindows) {
			driver.switchTo().window(runWindow);
			String currentWin= driver.getTitle();
			if(currentWin.equals(title)) {
				break;
			}
		}
	}
	
	public boolean closeAllWindowsWithoutParent (String parentID) {
		Set<String> allWindows= driver.getWindowHandles();
		for (String runWindow: allWindows) {
			
			if(!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
				
			}
		}
		driver.switchTo().window(parentID);
		if(driver.getWindowHandles().size()==1)
			return true;
		else
			return false;
	}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}

	}
