package automationfc;
import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_interaction {
	WebDriver driver;
	WebElement element;
	Actions action;
	
	@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
			driver = new ChromeDriver();
			action= new Actions(driver);
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


}@Test

public void TC_01_Jquery() throws InterruptedException {
	driver.get("http://www.myntra.com");
	//khi dùng method đều cần 1 method để perform
	element= driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()= 'Kids']"));
	action.moveToElement(element).perform();
	sleepInSecond(2);
	driver.findElement(By.xpath("//ul[u@class='desktop-vavBlock']//a[text()='Home & Bath']")).click();
	Assert.assertEquals(driver.getCurrentUrl(),"https://www.myntra.com/kids-home-bath");
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class=''breadcrumbs-crumb' and text()= 'Kids Home Bath']")).isDisplayed());
	
	

}
@Test

public void TC_02_Jquery() throws InterruptedException {
	driver.get("");
	String[] selectedTextExpected= {"1","2","3","4","5","6","7","8"};
	List<WebElement> allItems= driver.findElements(By.cssSelector("#selectable>li"));
	
	action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(7)).release().perform();
	//verify chọn 1-8 thành công
	List<WebElement> allItemsSelected= driver.findElements(By.cssSelector(".ui-selected"));
	// verify sz=8
	Assert.assertEquals(allItemsSelected.size(),8);
	
	//tạo ra 1 arraylisst để lưu lại selected text
	ArrayList<String> allItemsSelectedText= new ArrayList<String>();
	

	for (WebElement webElement : allItemsSelected) {
		allItemsSelectedText.add(webElement.getText());
		System.out.println(webElement.getText());
		
	}
	Object[] selectedTextActual= (Object[]) allItemsSelectedText.toArray();
	Assert.assertEquals(selectedTextExpected, selectedTextActual);
	
	
	

}
@Test

public void TC_03_Jquery() throws InterruptedException {
	driver.get("");
	String[] selectedTextExpected= {"1","2","3","4","5","6","7","8"};
	List<WebElement> allItems= driver.findElements(By.cssSelector("#selectable>li"));
	// 1. nhấn ctrl xuông 2. 
	
	action.keyDown(Keys.CONTROL).perform();
	//2 click vào số cần chọn
	action.click(allItems.get(0)).click(allItems.get(3)).click(allItems.get(6)).click(allItems.get(11)).perform();
	// nhả ctrl
	action.keyUp(Keys.CONTROL).perform();
	sleepInSecond(4);
	List<WebElement> allItemsSelected= driver.findElements(By.cssSelector(".ui-selected"));
	Assert.assertEquals(allItemsSelected.size(), 4);
}

@Test

public void TC_04_Double_Click() throws InterruptedException {
	driver.get("");
	element= driver.findElement(By.xpath("//button[text()=' Double click me']"));
	action.doubleClick(element).perform();
	Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()= 'Hello Automation']")).isDisplayed());
	
}
@Test

public void TC_05_Right_Click() throws InterruptedException {
	driver.get("");
	element= driver.findElement(By.xpath("//span[text()='right click me']"));
	action.contextClick(element).perform();
	
	//hover quit
	element=driver.findElement(By.cssSelector(".context-menu-icon-quit"));
	action.moveToElement(element).perform();
	
	//verify  quit có hover/visible
	String quitClassAttribute= element.getAttribute("class");
	System.out.println(quitClassAttribute);
	
	Assert.assertTrue(quitClassAttribute.contains("context-menu-hover"));
	Assert.assertTrue(quitClassAttribute.contains("context-menu-visible"));
	
	Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")));
	
	
	
	
}
@Test

public void TC_06_Drag_Drop() throws InterruptedException {
	driver.get("");
	WebElement sourceCircle= driver.findElement(By.cssSelector("#draggable"));
	WebElement targetCircle= driver.findElement(By.cssSelector("#dropable"));

	action.dragAndDrop(sourceCircle, targetCircle).perform();
	sleepInSecond(2);
	Assert.assertEquals(targetCircle.getText(), "You did great!");
}
@Test

public void TC_07_Drag_Drop_HTML5() throws InterruptedException {
	driver.get("");
	String sourceCss="#column-a";
	String targetCss="#column-b";	
	String java_script=readFile(javascriptPath);
	java_script= java_script+ "$(\"" + sourceCss+ "\").simulateDragDrop({dropTarget: \"" + targetCss + "\"});";
	jsExecutor.executeScrip(java_script);
	sleepInSecond(2);
	Assert.assertTrue(isDisplayed()) {})
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