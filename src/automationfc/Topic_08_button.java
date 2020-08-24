package automationfc;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_08_button {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	// checkbox
	By firstCheckbox = By.xpath("//input[@value='Anemia']");
	By secondCheckbox = By.xpath("//input[@value='Asthma']");
	By thirdCheckbox = By.xpath("//input[@value='Arthritis']");
	By allCheckboxes = By.xpath("//input[@value='checkbox']");

	// radio
	By firstRadio = By.xpath("//input[@value='3-4 days']");
	By secondRadio = By.xpath("//input[@value='I have a strict diet']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor = (JavascriptExecutor) driver;

	}

	public void TC_01_Jquery() throws InterruptedException {
		driver.get("http://www.fahasa.com/customer/account/create");

		driver.findElement(By.cssSelector("li.popup-login-tab")).click();
		WebElement loginButton = driver.findElement(By.cssSelector(".fhs-btn-login"));

		// verify login butotn is disable
		boolean status = loginButton.isEnabled();
		System.out.println("Login status=" + status);
		Assert.assertFalse(status);

		driver.findElement(By.cssSelector("#login_username")).sendKeys("test@gmail.com");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("1232434");
		sleepInSecond(2);

		// verify button is enable
		status = loginButton.isEnabled();
		System.out.println("Login status=" + status);
		Assert.assertTrue(status);

		// click Login
		loginButton.click();

		String errorMesssage = driver.findElement(By.cssSelector(".fhs-login-msg")).getText();
		Assert.assertEquals(errorMesssage, "Số điện thoại/Email hoặc Mật khẩu sai");
		sleepInSecond(5);

		// dùng javascrip để remove thuộc tính disable của button
		driver.findElement(By.cssSelector("#login_username")).clear();
		driver.findElement(By.cssSelector("#login_password")).clear();
		Assert.assertFalse(status);
		removeDisableAttribute(loginButton);
		sleepInSecond(5);
		loginButton.click();
		Assert.assertEquals();
	}

	public void TC_02_Default_Checkbox_Radio() throws InterruptedException {
		driver.get("https://automationfc.github.io/multiple-fields/");

		// verify 3 checkboxes là deselected
		// input[@value=""]
		Assert.assertFalse(driver.findElement(firstCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(secondCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(thirdCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(firstRadio).isSelected());
		Assert.assertFalse(driver.findElement(secondRadio).isSelected());

		// click 5 thằng trên
		driver.findElement(firstCheckbox).click();
		driver.findElement(secondCheckbox).click();
		driver.findElement(thirdCheckbox).click();
		driver.findElement(firstRadio).click();
		driver.findElement(secondRadio).click();
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(firstCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(secondCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(thirdCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(firstRadio).isSelected());
		Assert.assertTrue(driver.findElement(secondRadio).isSelected());

		driver.navigate().refresh();

		// click all checkbox
		List<WebElement> checkboxes = driver.findElements(allCheckboxes);
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			sleepInSecond(2);
		}
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			sleepInSecond(2);
		}
		for (WebElement checkbox : checkboxes) {
			Assert.assertFalse(checkbox.isSelected());
		}

	}

	// thẻ div thì click được nhưng ko verify được, thẻ input bị ẩn ko click được
	// nhưng verify được
	public void TC_03_Custom_Checkbox_Radio() throws InterruptedException {

		driver.get("https://material.angular.io/components/checkbox/examples");

		By checkedCheckbox = By.xpath("//span[contains(text(), 'Checked')]/preceding-sibling::div/input");

		clickByJavascript(driver.findElement(By.xpath("//span[contains(text(), 'Checked')]/preceding-sibling::div")));
		sleepInSecond(3);
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[contains(text(), 'Checked')]/preceding-sibling::div/input"))
						.isSelected());

	}

	public void sleepInSecond(long time) {
			try {
				Thread.sleep(time *1000);
				
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	// public void removeDisableAttribute(WebElement element) {
	// jsExecutor. executeScript("argument[0].removeAttribute('disable')", element);

	// }
	public void clickByJavascript(WebElement element) {
		jsExecutor.executeScript("argument[0].click();", element);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}