package automationfc;

import java.util.concurrent.TimeUnit;
import java.util.Random;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Technical {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test

	public void TC_01_ID() throws InterruptedException {

		driver.get("http://live.demoguru99.com/");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		driver.findElement(By.id("email")).sendKeys("");
		Thread.sleep(2000);

		driver.findElement(By.id("pass")).sendKeys("");
		Thread.sleep(2000);

		driver.findElement(By.id("send2")).click();
		Thread.sleep(2000);

		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");

	}

	@Test

	public void TC_02_ID() throws InterruptedException {

		driver.findElement(By.id("email")).sendKeys("1232434@11242.24324");
		Thread.sleep(2000);

		driver.findElement(By.id("pass")).sendKeys("123456");
		Thread.sleep(2000);

		driver.findElement(By.id("send2")).click();
		Thread.sleep(2000);

		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test

	public void TC_03_ID() throws InterruptedException {

		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		Thread.sleep(2000);

		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("123");
		Thread.sleep(2000);

		driver.findElement(By.id("send2")).click();
		Thread.sleep(2000);

		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test

	public void TC_04_ID() throws InterruptedException {

		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		Thread.sleep(2000);

		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("123123123");
		Thread.sleep(2000);

		driver.findElement(By.id("send2")).click();
		Thread.sleep(2000);

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]")).getText(),
				"Invalid login or password.");

	}

	@Test

	public void TC_05_ID() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		Thread.sleep(2000);

		// driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("123123");
		Thread.sleep(2000);

		driver.findElement(By.id("send2")).click();
		Thread.sleep(2000);

		Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'My Dashboard')]")).getText(),
				"MY DASHBOARD");
		Assert.assertEquals(
				driver.findElement(By.xpath("//strong[contains(text(),'Hello, Automation Testing!')]")).getText(),
				"Hello, Automation Testing!");

		Assert.assertTrue(driver.findElement(By.xpath("div[@class='box-content']//p[contains(.'Automation Testing')]"))
				.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("div[@class='box-content']//p[contains(.'automation_13@gmail.com')]"))
						.isDisplayed());
		// Click Account
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		// Click Logout
		driver.findElement(By.xpath("//a[@title='Log out']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
		Thread.sleep(2000);

	}

	@Test
	public void TC_06_ID() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.findElement(By.id("firstname")).sendKeys("Nga");
		Thread.sleep(2000);

		driver.findElement(By.name("lastname")).sendKeys("Nguyen");
		Thread.sleep(2000);

		String email = "adele" + randomNumber() + "@gmail.com";
		driver.findElement(By.className("validate-email")).sendKeys(email);
		Thread.sleep(2000);

		driver.findElement(By.id("password")).sendKeys("123456");
		Thread.sleep(2000);

		driver.findElement(By.name("confirmation")).sendKeys("123456");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Thread.sleep(2000);

		String successMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");
		Thread.sleep(2000);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Nga Nguyen')]"))
				.isDisplayed());
		Thread.sleep(2000);

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'" + email + "')]"))
				.isDisplayed());
		// Click Account
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		// Click Logout
		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
		// Chờ cho element nào đó trên Home hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//img[@class='large']")).isDisplayed());
		Thread.sleep(2000);

	}

	@Test

	public void TC_07_ID() throws InterruptedException {

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.name("firstname")).sendKeys("Richard");
		driver.findElement(By.id("lastname")).sendKeys("Rihana");
		String email = "rihana" + randomNumber() + "@gmail.com";
		driver.findElement(By.className("validate-email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.name("confrirmation")).sendKeys("123456");
		// click vào button Register
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		String successMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='box']//p[contains(text(), Richard Rihana)]")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='box']//p[contains(.,'" + email + "')]")).isDisplayed());
		// Click Account
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		// Click Logout
		driver.findElement(By.xpath("//a[@title='Log out']")).click();
		// Chờ cho element nào đó trên Home hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());

		Thread.sleep(2000);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);

	}

}
