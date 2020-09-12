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
import org.testng.annotations.Test;

import java.sql.Driver;

public class Topic_13_JavaScript_Executor {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	WebElement element;

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
public void TC_01()  {
	driver.get("https://www.myntra.com");
	WebElement homeAndPathLink= driver.findElement(By.xpath("//a[text()= 'Home & Bath']"));
	jsExecutor.executeScript("argument[0].click();", homeAndPathLink);
	Thread.sleep(30000);
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()= 'Kids Home Bath']")).isDisplayed());
	
			
}
@Test
public void TC_02()  {
	navigateToUrlByJS("http://live.demoguru99.com");
	String liveGuruDomain = (String) executeForBrowser ("return document.domain;");
	Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
	
	String liveGuruUrl= (String) executeForBrowser ("return document.URL;");
	Assert.assertEquals(liveGuruUrl, "http://live.demoguru99.com");
	
	clickToElementByJS("//a[text()= 'Mobile']");
	clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
	
	String liveGuruInnerValue = getPageInnerText();
	Assert.assertTrue(liveGuruInnerValue.contains("Samsung Galaxy was added to your shopping cart."));
	Assert.assertTrue(verifyTextInInnerText("Samsung Galaxy was added to your shopping cart."));
	
	clickToElementByJS("//a[text()='Customer Service']");
	String customerServiceTitle = (String) executeForBrowser ("return document.title;");
	Assert.assertEquals(customerServiceTitle, "Customer Service");
	
	scrollToElement("//input[@id='newletter']");
	Assert.assertTrue(verifyTextInInnerText("Praesent ipsum libero,"));
	navigateToUrlByJS("http://demo.guru99.com/v4/");
	String demoGuruDomain = (String) executeForBrowser("return document.domain;");
	Assert.assertEquals(demoGuruDomain,"demo.guru99.com");
	
	
	
	
}
@Test
public void TC_03_remove_attribute()  {

	
	
	
	
}
public String getPageInnerText() {
	return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}
public Object executeForBrowser(String javascript) {
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	return jsExecutor.executeScript(javascript);
}

public boolean verifyTextInInnerText(String textExpected) {
	//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
	System.out.println("Text actual= "+ textActual);
	return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
	//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
	//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	jsExecutor.executeScript("window.location = '" + url + "'");
}

public void highlightElement(String locator) {
	element = driver.findElement(By.xpath(locator));
	String originalStyle = element.getAttribute("style");
	jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
	sleepInSecond(1);
	jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

}

public void clickToElementByJS(String locator) {
	element= driver.findElement(By.xpath(locator));
	//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	//WebElement element = getElement(driver, locator);
	jsExecutor.executeScript("arguments[0].click();", element);
}

public void scrollToElement(String locator) {
	element= driver.findElement(By.xpath(locator));
	//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	//WebElement element = getElement(driver, locator);
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
}

public void sendkeyToElementByJS(String locator, String value) {
	element= driver.findElement(By.xpath(locator));
	//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	//WebElement element = getElement(driver, locator);
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
	element= driver.findElement(By.xpath(locator));
	//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	//WebElement element = getElement(driver, locator);
	jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
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
}