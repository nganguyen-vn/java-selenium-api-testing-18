package automationfc;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_UploadFile {
	
		WebDriver driver;
		JavascriptExecutor jsExecutor;
		String source_folder= System.getProperty("user.dir");
		String image_name_01= "Bphone.jpg";
		String image_name_02= "iPhone.jpg";
		String image_name_03= "Samsung.jpg";
		
		String image_01_path= source_folder + "\\uploadFiles\\"+ image_name_01;
		String image_02_path= source_folder + "\\uploadFiles\\"+ image_name_02;
		String image_03_path= source_folder + "\\uploadFiles\\"+ image_name_03;
		
	@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
			driver = new ChromeDriver();
			//explicitWait= new WebDriverWait(driver,30);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		

		}



	@Test
	public void TC_01_Sendkeys()  {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path);
		sleepInSecond(1);
		
		
		uploadFile.sendKeys(image_02_path);
		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		sleepInSecond(1);
		
		uploadFile.sendKeys(image_03_path);
		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		sleepInSecond(1);
		
				
	}
	@Test
	public void TC_02_Sendkeys_multiFile()  {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path + "\n"+ image_02_path + "\n" + image_03_path);
		sleepInSecond(1);
		
		Assert.assertTrue (driver.findElement(By.xpath("//p[@class='name' and text()=' "+image_name_01 +"']")).isDisplayed());
		Assert.assertTrue (driver.findElement(By.xpath("//p[@class='name' and text()=' "+image_name_02 +"']")).isDisplayed());
		Assert.assertTrue (driver.findElement(By.xpath("//p[@class='name' and text()=' "+image_name_03 +"']")).isDisplayed());
				
		
		List<WebElement> startButton= driver.findElements(By.cssSelector("td.start"));
		for(WebElement start: startButton) {
			start.click();
			sleepInSecond(1);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ image_name_01+" ' ]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ image_name_02+" ' ]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ image_name_03+" ' ]")).isDisplayed());
		
		
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'"+ image_name_01 + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'"+ image_name_02 + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'"+ image_name_03 + "')]"));
		
				
	}
	@Test
	public void TC_03_Sendkeys_GoFile()  {
	
		driver.get("https://gofile.io/uploadfile");
		String parentID= driver.getWindowHandle();
		
		WebElement uploadFile= driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(image_01_path+ "\n" + image_02_path+ "\n"+ image_03_path);
		sleepInSecond(1);
		
		 driver.findElement(By.cssSelector("button#btnUpload")).click();
		 Assert.assertTrue(driver.findElement(By.cssSelector("button[aria-label='OK']")).isDisplayed());
		 driver.findElement(By.cssSelector("button[aria-label='OK']")).click();
		 
		 Assert.assertTrue(driver.findElement(By.cssSelector("a#link")).isDisplayed());
		 driver.findElement(By.cssSelector("a#link")).isDisplayed();
		 
		 switchToWindowByID(parentID);
		 
		 Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']/following-sibling::td[@class]//i[contains[@class,'download')]")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']/following-sibling::td[@class]//i[contains[@class,'download')]")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']/following-sibling::td[@class]//i[contains[@class,'download')]")).isDisplayed());
		
		 Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_01+"']/following-sibling::td[@class]//i[contains[@class,'play')]")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_02+"']/following-sibling::td[@class]//i[contains[@class,'play')]")).isDisplayed());
		 Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_name_03+"']/following-sibling::td[@class]//i[contains[@class,'play')]")).isDisplayed());
				
		 
	}
	public boolean isImageDisplayed(String xpathLocator) {
		jsExecutor= (JavascriptExecutor) driver;
		Boolean imagePresence = (Boolean)
				((JavascriptExecutor) driver).executeScript
				("return arguments [0].complate && typeof argument [0].naturalWidth"
						+ "!=\"undefined\" && arguments [0].naturalWidth >0", driver.findElement(By.xpath(xpathLocator)));
		return imagePresence;
		
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
	public void sleepInSecond (long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			
		}
	}
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
}


