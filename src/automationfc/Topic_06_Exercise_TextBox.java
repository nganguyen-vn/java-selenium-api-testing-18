package automationfc;
import java.util.concurrent.TimeUnit;
//import java.util.List;
import java.util.Random;

//import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Exercise_TextBox {
	WebDriver driver;
	String password, userIDValue;
	String name, gender, dateOfBirth, address, city, state, pin, phone, email;
	
	By customerNameTextbox=By.className("name");
	By genderMaleRadiobutton= By.xpath("//input[@value='m']");
	By genderTextbox= By.className("gender");
	By dateOfBirthTextbox=By.className("dob");
	By addressTextArea=By.className("addr");
	By stateTextbox=By.className("state");
	By cityTextbox=By.className("city");
	By pinTextbox=By.className("pinno");
	By phoneTextbox=By.className("telephone");
	By emailTextbox=By.className("email");
	By passwordTextbox=By.className("password");
	
	@BeforeClass
	public void BeforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libraries/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		name = "Justin John"; 
		gender = "female";
		dateOfBirth = "08/08/1987"; 
		address = "11 Nguyen Trai\nHai Chau\nDa Nang"; 
		city = "Los Angeles";  
		state = "CA";  
		pin = "235545"; 
		phone = "0923583475"; 
		email = "nga"+ randomNumber() + "@gmail.com"; 
		password= "12345678";
	}
@Test

public void TC_01_ID() throws InterruptedException {
	driver.get("http://demo.guru99.com/v4/");
	driver.findElement(By.xpath("//a[contains(text(),'here')]")).click();
	driver.findElement(By.name("emailid")).sendKeys("test123@gmail.com");
	driver.findElement(By.name("btnLogin")).click();
	userIDValue= driver.findElement(By.xpath("//td[contains(text(),'User ID :')]/following-sibling::td")).getText();
	password= driver.findElement(By.xpath("//td[contains(text(),'Password')]/following-sibling::td")).getText();
	driver.get("http://demo.guru99.com/v4/");
	driver.findElement(By.name("uid")).sendKeys(userIDValue);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("btnLogin")).click();
	Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
	driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	
	
	//input
	driver.findElement(customerNameTextbox).sendKeys(name);
	driver.findElement(genderMaleRadiobutton).sendKeys(gender);
	driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
	driver.findElement(addressTextArea).sendKeys(address);
	driver.findElement(stateTextbox).sendKeys(state);
	driver.findElement(cityTextbox).sendKeys(city);
	driver.findElement(pinTextbox).sendKeys(pin);
	driver.findElement(phoneTextbox).sendKeys(phone);
	driver.findElement(passwordTextbox).sendKeys(password);
	driver.findElement(emailTextbox).sendKeys(email);
	Thread.sleep(2000);
}
	public int randomNumber() {
		Random rand=new Random();
		return rand.nextInt(9999);
}
@AfterClass
public void afterClass() {
	driver.quit();
}
}
