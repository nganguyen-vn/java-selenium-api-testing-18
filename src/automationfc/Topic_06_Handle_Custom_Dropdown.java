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

public class Topic_06_Handle_Custom_Dropdown {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		explicitWait= new WebDriverWait(driver,30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		jsExecutor=(JavascriptExecutor) driver;

	}

	@Test

	public void TC_01_Jquery() throws InterruptedException {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		
		selectItemDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()= '5']")).isDisplayed());
				
		selectItemDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "10");
		selectItemDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "15");
		selectItemDropdown("//span[@id='number-button']", "//li[@class='ui-menu-item']/div", "19");
	
	}
	
	@Test

	public void TC_02_Angular() throws InterruptedException {
		driver.get("");
		
		selectItemDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]","//ul[@id='games-option']/li","Basketball");
		Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Basketball");
		
	}
	@Test

	public void TC_03_React() throws InterruptedException {
		driver.get("");
		
		selectItemDropdown("//i[@class='dropdown icon']", "//span[@class='text']","Justen Kitsune");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='text' and text()='Justen Kitsune']")).isDisplayed(), false);
		
		
	}
	@Test

	public void TC_04_React() throws InterruptedException {
		driver.get("http://indrimuska.github.io");
		
		sendkeyToEditDropdown("//dic[@id='default-place']/input", "Ford");
		Assert.assertEquals(getHiddenText("#defaul-place li.es-visible"), "Ford");
		
		
	}
	
	@Test

	public void TC_05_advanced() throws InterruptedException {
		driver.get("http://multiple-select.wenzhixin.net.cn");
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		String[] month= {"January", "April", "July"};
		selectMultiItemDropdown("//option/parent::select/following-sibling::div", "//option/parent::select/following-sibling::div//label/span", expectedValueItem);
		Assert.assertTrue(areItemSelected(month));
		
		
		driver.navigate().refresh();
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));	
		String[] months= {"January", "April", "July", "December"};
		selectMultiItemDropdown("//option/parent::select/following-sibling::div", "//option/parent::select/following-sibling::div//label/span", expectedValueItem);
		Assert.assertTrue(areItemSelected(months));
		
		
	}
	public void sendkeyToEditDropdown (String locator, String value) {
		driver.findElement(By.xpath(locator)).clear();
		driver.findElement(By.xpath(locator)).sendKeys(value);
		sleepInSecond(1);
		driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
		sleepInSecond(1);
		
		
		
	}
	
	
	//ham dung nhieu lan
	public void selectItemDropdown(String parentLocator, String itemLocator, String expectedItem) {
		
		// click vao the bat ky de hien thi item trong dropdown
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
		driver.findElement(By.xpath(parentLocator)).click();
		
		//cho tat ca value hien thi
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(itemLocator)));
		
		//lay het item va dua va list element
		List <WebElement> allItems= driver.findElements(By.xpath(itemLocator));
		
		//tổng số lượng item trong dropdown
		System.out.println("Item size="+ allItems.size());
		
		//Duyet item
		for (WebElement item: allItems) {
			String actualItem=item.getText();
			
			if (actualItem.equals(expectedItem)) {
				
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				explicitWait.until(ExpectedConditions.elementToBeClickable(item));
				item.click();
				sleepInSecond(1);
				break;
			}
		}
		
	}
	public String getHiddenText(String cssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator+"\").text");
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
