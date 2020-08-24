package automationfc;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Dropdown {

	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test

	public void TC_01_ID() throws InterruptedException {
		driver.get("https://www.facebook.com");
		
		//co cái dropdowwn xuất hiện thì mới khởi tạo
		// khởi tạo 1 biến select: đi tìm element có id bằng day
		select= new Select(driver.findElement(By.id("month")));
		
		//ktr 1 dropdown có hỗ trợ chọn nhiều ko
		boolean status= select.isMultiple();
		System.out.println(status);
		Assert.assertFalse(status);
		
		//index nhận kiểu số, value nhận String. Không nên dùng
		select.selectByIndex(2);
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 2");
		
		//ktra đã chọn thành công
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 5");
		select.selectByValue("5");
		sleepInSecond(3);
		
		
		
		//visible text
		select.selectByVisibleText("Thang 10");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Tháng 10");
		
		//get ra tất cả các thẻ option của dropdown
		List<WebElement> monthOption= select.getOptions();
		
		//In ra xem có bn giá trị
		int monthOptionSize= monthOption.size();
		System.out.println("Month item number ="+ monthOptionSize);
		
		//ktra số lượng của item dropdowwn
		
		Assert.assertEquals(monthOptionSize, 13);
		// In ra item value của nó là gì
		// for iterator
		// list thì dùng get
		for (int i=0; i< monthOption.size(); i++) {
			System.out.println("For iterator"+ monthOption.get(i).getText());
			
		}
		
		
	
		
		//ktr dropdown hiển thị đúng gtr
		List<String> actualItem= new ArrayList<String>();
		actualItem.add("Tháng");
		actualItem.add("Tháng 1");
		actualItem.add("Tháng 2");
		actualItem.add("Tháng 3");
		actualItem.add("Tháng 3");
		actualItem.add("Tháng 4");
		actualItem.add("Tháng 5");
		actualItem.add("Tháng 6");
		actualItem.add("Tháng 7");
		actualItem.add("Tháng 8");
		actualItem.add("Tháng 9");
		actualItem.add("Tháng 10");
		actualItem.add("Tháng 11");
		actualItem.add("Tháng 12");
		
		ArrayList<String> expectedItem = new ArrayList<String>();
		
		//for each
		for (WebElement option: monthOption) {
			System.out.println("For each:"+ option.getText());
		}
		Assert.assertEquals(actualItem, expectedItem);
		
	}


	@Test

	public void TC_02_ID() throws InterruptedException {
	driver.get("");
	select = new Select(driver.findElement(By.xpath("//select [@id='job2']")));
	Assert.assertTrue(select.isMultiple());
	
	select.selectByVisibleText("Automation");
	select.selectByVisibleText("Mobile");
	select.selectByVisibleText("Performance");
	
	int optionSelected= select.getAllSelectedOptions().size();
	System.out.println("Item selected=" + optionSelected);
	Assert.assertEquals( optionSelected, 13);
	
	List<WebElement> optionSelectedElement= select.getAllSelectedOptions();
	for (WebElement option : optionSelectedElement) {
		System.out.println(option.getText());
	}
	//bỏ chọn
	select.deselectAll();
	optionSelected= select.getAllSelectedOptions().size();
	System.out.println("Item selected=" + optionSelected);
	Assert.assertEquals( optionSelected, 0);
	
	//hay dùng nhiều
	//khởi tạo tìm, dropdown select = new Select(driver.findElement())
	//chọn dữ liệu: select.selectByVisibleText()
	//verify: select.getFirstSelectOption().getText();
	//làm bài tập với nopecommerce
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
	

