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

public class Topic_04_Web_Broser_Element {
	//Biến= Thuộc tính= Field
	//biến toàn  (fullName): global sử dụng cho toàn bộ Class
	public String fullName="Nguyen Van A";
	public String address="234 Nguyễn Trãi";
	
	//Hàm/ phương thức
	//Biến cục bộ (local) (String fullname) chỉ sd trong method
	//hàm get: lấy dữ liệu, hàm set: gán dữ liệu
	//trong TH biến toàn bộ giống tên biến cục bộ, cần this. để phân biệt
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFullname() {
		return fullName;
	}

	//Khai báo 1 biến đại diện cho Webdriver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		
		//mở browser lên
		driver = new ChromeDriver();
		//driver.close(); // đóng tab hiện tại
		//driver.quit(); //đóng luôn browser
		//driver.findElement(arg0)
		
		//lấy url của tab hiện tại
		String loginPageUrl = driver.getCurrentUrl();
		System.out.println(loginPageUrl);
		
		//lấy title
		String loginPageTitle = driver.getTitle();
		System.out.println(loginPageTitle);
		//hoặc in luôn ntn
		System.out.println(driver.getTitle());
		
		//dùng hàm TestNG để verify kq
		Assert.assertTrue(loginPageUrl.equals("https://www.facebook.com"));
		//thường dùng assertEquals đế so sánh kq
		//nếu dùng nhiều lần thì cần khai báo biển, nếu chỉ dùng 1 lần để verify thì chỉ dùng 1 lần vd
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com");
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
		

	}
