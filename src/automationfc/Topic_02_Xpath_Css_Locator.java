package automationfc;




import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Locator {
	//<button id="send2" class="button" name="send" title="Login" type="submit">
	//trong selenium có 3 loại locator là 3 dạng attribute của HTML id/class/name
	//hay được dev set là duy nhất (uniqe)
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	@Test
	public void TC_01_ID() throws InterruptedException {
		//tương tác với element
		driver.findElement(By.id("email")).sendKeys("test@gmal.com");
		Thread.sleep(2000);
		//xóa dữ liệu trong các element có thể nhập được
		driver.findElement(By.id("email")).clear();
		
	}
	@Test
	public void TC_02_Class() throws InterruptedException {
		driver.findElement(By.className("input-text required-entry validate-password")).sendKeys("12345678");
		Thread.sleep(2000);
		driver.findElement(By.className("email")).clear();
	}	
	@Test
	public void TC_03_Name() throws InterruptedException {
		driver.findElement(By.name("login[username]")).sendKeys("test123");
		Thread.sleep(2000);
		driver.findElement(By.name("email")).clear();
	}	
	@Test
	public void TC_04_Tagname() throws InterruptedException {
		driver.findElements(By.tagName("a")).size();
		System.out.println("Sum link = + linkNumber");
		Thread.sleep(2000);
	}
	@Test// nó chỉ wwork với đường link với text cố đinnhj
	public void TC_05_Link_Text() throws InterruptedException {
		//click vào 1 link
		driver.findElement(By.linkText("SITE MAP")).click();
		Thread.sleep(2000);
	}
	@Test// đường link với text tương đối
	public void TC_06_Partial_Link_Text() throws InterruptedException {
		driver.findElement(By.partialLinkText("ADVANCED")).click();
		Thread.sleep(2000);
	}
	@Test
	public void TC_07_Css() throws InterruptedException {
		
		//Id
		driver.findElement(By.cssSelector("#name")).sendKeys("TE");
		Thread.sleep(2000);
		
		//class
		driver.findElement(By.cssSelector(".advanced-search")).isDisplayed();
		Thread.sleep(2000);
		
		//Name
		driver.findElement(By.cssSelector("input[name='short_description']")).sendKeys("test");
		Thread.sleep(2000);
		
		//Link Text
		driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/mobile.html']")).click();
		Thread.sleep(2000);
		
		//partial link
		driver.findElement(By.cssSelector("a[href*='/catalogsearch/advanced/']")).click();
		Thread.sleep(2000);
		
		//tag name
		int linksize= driver.findElements(By.cssSelector("a")).size();
		System.out.println("css Tagname = " + linksize);
		Thread.sleep(2000);
	}
	@Test
	public void TC_07_Xpath() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		Thread.sleep(2000);
		
		//Id
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xpath@gmail.com");
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		Thread.sleep(2000);
		
		//class
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")).sendKeys("test1@gmail.com");
		Thread.sleep(2000);
		
		//name 
		driver.findElement(By.xpath("//input[@name= 'login[username]']")).sendKeys("nga nguyen");
		Thread.sleep(2000);
		
		//Link text
		driver.findElement(By.xpath("//a[text()='About Us']")).click();
		Thread.sleep(2000);
		
		//partical link
		driver.findElement(By.xpath("//a[contains(text(),'customer')]")).click();
		Thread.sleep(2000);
		
		//Tagname
		System.out.println(driver.findElements(By.xpath("//a")).size());
		Thread.sleep(2000);
		
		//Css

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
