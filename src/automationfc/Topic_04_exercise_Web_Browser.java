package automationfc;

import java.util.concurrent.TimeUnit;
import java.util.List;
//import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_exercise_Web_Browser {
	WebDriver driver;
	
	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	}@Test

	public void TC_01_ID() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
		Thread.sleep(2000);
		
		
	
	}@Test

	public void TC_02_ID() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
		Thread.sleep(2000);
	}@Test

	public void TC_03_ID() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
		
		driver.navigate().back();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
		Thread.sleep(2000);
	}@Test

	public void TC_04_ID() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		
		String loginPageSource=driver.getPageSource();
		
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		
		String registerPageSource=driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));
		Thread.sleep(2000);
	}@Test

	public void TC_05_Web_Element() throws InterruptedException {
		
		//cách viết 1
		WebElement lastnameTextbox = driver.findElement(By.xpath("//input[@name='lastname']"));
		lastnameTextbox.clear();
		lastnameTextbox.sendKeys("Test 123");
		 //cách viết 2
		driver.findElement(By.xpath("//input[@name='lastname']")).clear();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Test 123");
		
		//find nhiều element
		List<WebElement> sexRadioButton = driver.findElements(By.name("sex"));
		System.out.println("Radio button size = "+ sexRadioButton.size());
		for(WebElement radio: sexRadioButton) {
			radio.click();
			Thread.sleep(2000);
		//
			
		WebElement element = driver.findElement(By.xpath(""));
		
		// xóa dữ liệu trong 1 Textbox hoặc Textarea
		element.clear();
		
		// click vào 1 button/link
		element.click();
		
		//nhập dl
		element.sendKeys("");
		
		//lấy giá trị của 1 element đang thao tác
		String lastNamePlaceHoderText=element.getAttribute("");
		System.out.println(lastNamePlaceHoderText);
		
		//test giao diện font/size/...
		 element = driver.findElement(By.id("loginbutton"));
		String backgroundColorValue = element.getCssValue("background-color");
		String fontSize = element.getCssValue("font-size");
		System.out.println("Ma mau = "+ backgroundColorValue);
		System.out.println("font size = "+ fontSize);
		
		
		//get kích thước của vị trí đó so với chiều ngang và chiều cao của browser
		element.getLocation();
		
		//get chiều ngang chiều dọc của element
		element.getSize();
		
		//getagname
		element=driver.findElement(By.cssSelector("#email"));
		System.out.println("Tagname = "+ element.getTagName());
				
		//getText
		element=driver.findElement(By.xpath("//div[@id='header_block']/span"));
		System.out.println("Header text = "+element.getText());
		Thread.sleep(2000);
		
		// hàm isdisplay ktra 1 cái element có hiển thị ko. 
		Assert.assertTrue(element.isDisplayed());
		
		// isenable ktra 1 element có bị disable không. True là đang enabel, Flase là đang disabel
		Assert.assertTrue(element.isEnabled());
		
		// ktra 1 element có thể được chọn hay chưa dùng isSelected, True là được chọn, False là chưa đc chọn
		Assert.assertTrue(element.isSelected());
		
		//tagname: áp dụng cho Form
		element= driver.findElement(By.xpath("//inout[@id='email']"));
		element.submit();
		
		}
	}@Test

	public void TC_06_Web_Element_Exercise() throws InterruptedException {
	driver.get("");
	
		//Email textbox
		//if (driver.findElement(By.id("mail")).isDisplayed());{
			//driver.findElement(By.id("mail")).sendKeys("Automation Testing");
			//Thread.sleep(2000);
		//}
		//Education Textarea
			//if (driver.findElement(By.id("edu")).isDisplayed());{
			//driver.findElement(By.id("edu")).sendKeys("Automation Testing");
			//Thread.sleep(2000);
			//}	
		//radio button
			//if (driver.findElement(By.id("under_18")).isDisplayed());{
			//driver.findElement(By.id("under_18")).sendKeys("Automation Testing");
			//Thread.sleep(2000);
			//}
			//viet lại test case này cho gọn
			if (isElementDisplayed("//input[@id='mail']")){
			driver.findElement(By.id("mail")).sendKeys("Automation Testing");
			Thread.sleep(2000);}
			
			if (isElementDisplayed("//textarea[@id='edu']")){
				driver.findElement(By.id("edu")).sendKeys("Automation Testing");
				Thread.sleep(2000);}
			
			if (isElementDisplayed("//input[@id='under_18']")){
				driver.findElement(By.id("under_18")).sendKeys("Automation Testing");
				Thread.sleep(2000);}
}@Test

	public void TC_07_Web_Element_Exercise() throws InterruptedException {
	driver.get("");
	//boolean status;

	//Email textbox
	//status=driver.findElement(By.id("mail")).isEnabled();
	//if (status){	
	//	System.out.println("Email is enabled");
	//} else {
	//	System.out.println("Email is disabled"); 
	//}
	
	//Education
	//status= driver.findElement(By.id("edu")).isEnabled();
	//if (status){	
		//	System.out.println("Edu is enabled");
		//} else {
		//	System.out.println("Edu is disabled"); 
		//}
		
	//Slider 02
	//status= driver.findElement(By.id("slider-2")).isEnabled();
	//if (status){	
	//System.out.println("slider is enabled");
	//	} else {
		//	System.out.println("slider is disabled"); 
		//}
	//tối ưu code chỉ cần 3 dòng code sau
	Assert.assertTrue(isElementEnable("//input[@id='mail']"));
	Assert.assertTrue(isElementEnable("//input[@id='edu']"));
	Assert.assertTrue(isElementEnable("//input[@id='slider-2']"));
	
	
	
}@Test

	public void TC_08_Web_Element_Exercise() throws InterruptedException {
	driver.get("");
	//click lần 1
	driver.findElement(By.id("over_18")).click();
	//click lần 1
	driver.findElement(By.id("development")).click();
	
	Assert.assertTrue(isElementSelected(By.id("over_18")).isSelected());
	Assert.assertTrue(isElementSelected(By.id("development")).isSelected());
	
	//click lần 2
		driver.findElement(By.id("over_18")).click();
		//click lần 2
		driver.findElement(By.id("development")).click();
		
		Assert.assertTrue(isElementSelected(By.id("over_18")).isSelected());
		Assert.assertFalse(isElementSelected(By.id("development")).isSelected());
	
		
		}
		private WebElement isElementSelected(By id) {
	// TODO Auto-generated method stub
	return null;
}

		public boolean isElementDisplayed(String xpathValue ) {
			WebElement element= driver.findElement(By.xpath(xpathValue));
			if(element.isDisplayed()) {
				System.out.println("Element with xpath ="+ xpathValue+ "is displayed");
				return true;
			} else {
				System.out.println("Element with xpath ="+ xpathValue + "is not displayed");
				return false;}
			}
			
		public boolean isElementEnable(String xpathValue ) {
				WebElement element= driver.findElement(By.xpath(xpathValue));
				if(element.isEnabled()) {
					System.out.println("Element with xpath ="+ xpathValue+ "is enabled");
					return true;
				} else {
					System.out.println("Element with xpath ="+ xpathValue + "is disable");
					return false;}
				}
			
		public boolean isElementSelected(String xpathValue ) {
					WebElement element= driver.findElement(By.xpath(xpathValue));
					if(element.isSelected()) {
						System.out.println("Element with xpath ="+ "is selected");
						return true;
					} else {
						System.out.println("Element with xpath ="+  "is deselected");
						return false;}
					
		}
		
		//có 2 kiểu Buildin type:  String/float/int.. và User type do người dùng tự đn 
		//thao tác với 1 element
		//click vào 1 button/link...
		//sendkey vào 1 textbox
		//tick vào tất các checkbox
		//thao tác với nhiều element trên table

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	}
	
	


