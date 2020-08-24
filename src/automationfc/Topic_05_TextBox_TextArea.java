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

public class Topic_05_TextBox_TextArea {
WebDriver driver;
String userIDValue;
String password, loginPageUrl;
String name, dateOfBirth, editAddress, editCity, editState, editPin, editPhone, editEmail, gender, customerID;
String address, city, state, pin, phone, email;

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
		gender = "male";
		dateOfBirth = "1986-08-08"; 
		address = "11 Nguyen Trai\nHai Chau\nDa Nang"; 
		city = "Los Angeles";  
		state = "CA";  
		pin = "235545"; 
		phone = "0923583475"; 
		email = "nga"+ randomNumber() + "@gmail.com"; 
		driver.get("http://demo.guru99.com/");
		loginPageUrl=driver.getCurrentUrl();

		//edit
		 
		editAddress = "11 Nguyen Trai\nHai Chau\nCan Tho"; 
		editCity = "Ha Noi";  
		editState = "De";  
		editPin = "434666"; 
		editPhone = "0930535555"; 
		editEmail = "nga"+ randomNumber() + "@gmail.com"; 
		

}@Test

public void TC_01_ID() throws InterruptedException {
	driver.findElement(By.xpath("//a[text()='here']")).click();
	
	driver.findElement(By.className("emailid")).sendKeys(email);
	//driver.findElement(By.className("emailid")).sendKeys("test123@gmail.com");
	
	
	driver.findElement(By.className("btLogin")).click();

	//lấy text động, tìm attribute cố định
	driver.findElement(By.xpath("//td[text()='User ID:']/following-sibling::td")).getText();
	
	userIDValue= driver.findElement(By.xpath("//td[text()='User ID:']/following-sibling::td")).getText();
	password= driver.findElement(By.xpath("//td[text()='password']/following-sibling::td")).getText();
	
	System.out.println(userIDValue);
	System.out.println(password);
	
	
	
}@Test

public void TC_02_ID() throws InterruptedException {
	
	driver.get(loginPageUrl);
	
	driver.findElement(By.name("uid")).sendKeys( userIDValue);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("btnLogin")).click();
	
	Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Welcome to manager's page of guru99 bank");
	
}@Test

public void TC_03_ID() throws InterruptedException {
	//input
	driver.findElement(customerNameTextbox).sendKeys(name);
	driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
	driver.findElement(addressTextArea).sendKeys(address);
	driver.findElement(stateTextbox).sendKeys(state);
	driver.findElement(cityTextbox).sendKeys(city);
	driver.findElement(pinTextbox).sendKeys(pin);
	driver.findElement(phoneTextbox).sendKeys(phone);
	driver.findElement(passwordTextbox).sendKeys(password);
	driver.findElement(emailTextbox).sendKeys(email);
	
	//click Submit
	driver.findElement(By.name("sub")).click();
	
	//verify create new customer
	Assert.assertEquals(driver.findElement(By.className("heading3")).getText(), "Customer registered Successfully!");
	
	//
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer name']/following-sibling::td")).getText(), name);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=' Birthdate']/following-sibling::td")).getText(), dateOfBirth);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address ']/following-sibling::td")).getText(), address.replace("\n", " "));
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=' City']/following-sibling::td")).getText(), city);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=' State']/following-sibling::td")).getText(), state);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=' Pin']/following-sibling::td")).getText(), pin);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email ']/following-sibling::td")).getText(), email);
	
	customerID= driver.findElement(By.xpath("//td[text()='Customer ID ']/following-sibling::td")).getText();
}@Test

public void TC_04_ID() throws InterruptedException {
	driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	driver.findElement(By.className("cusid")).sendKeys(customerID);
	driver.findElement(By.name("AccSbmit")).click();
	// text nằm bên ngoài thẻ thì dùng getText, text nằm trong attribute thì dùng getAttibute
	Assert.assertEquals(driver.findElement(customerNameTextbox).getAttribute("value"), name);
	Assert.assertEquals(driver.findElement(genderTextbox).getAttribute("value"), gender);
	Assert.assertEquals(driver.findElement(dateOfBirthTextbox).getAttribute("value"), dateOfBirth);
	Assert.assertEquals(driver.findElement(addressTextArea).getAttribute("value"), address);
	Assert.assertEquals(driver.findElement(stateTextbox).getAttribute("value"), state);
	Assert.assertEquals(driver.findElement(cityTextbox).getAttribute("value"), city);
	Assert.assertEquals(driver.findElement(pinTextbox).getAttribute("value"), pin);
	Assert.assertEquals(driver.findElement(phoneTextbox).getAttribute("value"), phone);
	Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), email);
	
	//verify name, gender và dob là disable field
	Assert.assertTrue(driver.findElement(customerNameTextbox).isEnabled());
	Assert.assertTrue(driver.findElement(dateOfBirthTextbox).isEnabled());
	Assert.assertTrue(driver.findElement(genderTextbox).isEnabled());
	Assert.assertTrue(driver.findElement(dateOfBirthTextbox).isEnabled());
	
	
	// edit data
	driver.findElement(addressTextArea).clear();
	driver.findElement(addressTextArea).sendKeys(editAddress);
	driver.findElement(stateTextbox).clear();
	driver.findElement(stateTextbox).sendKeys(editState);
	driver.findElement(cityTextbox).clear();
	driver.findElement(cityTextbox).sendKeys(editCity);
	driver.findElement(pinTextbox).clear();
	driver.findElement(pinTextbox).sendKeys(editPin);
	driver.findElement(phoneTextbox).clear();
	driver.findElement(phoneTextbox).sendKeys(editPhone);
	driver.findElement(emailTextbox).clear();
	driver.findElement(emailTextbox).sendKeys(editEmail);
	
	//click submit
	driver.findElement(By.className("sub")).click();
	
	//verify update customer
	Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),"Customer details updated Successfully!!!");
			
	//verify matching
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer name']/following-sibling::td")).getText(), name);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=' Birthdate']/following-sibling::td")).getText(), dateOfBirth);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address ']/following-sibling::td")).getText(), editAddress.replace("\n", " "));
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=' City']/following-sibling::td")).getText(), editCity);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=' State']/following-sibling::td")).getText(), editState);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()=' Pin']/following-sibling::td")).getText(), editPin);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email ']/following-sibling::td")).getText(), editEmail);
	
	
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
