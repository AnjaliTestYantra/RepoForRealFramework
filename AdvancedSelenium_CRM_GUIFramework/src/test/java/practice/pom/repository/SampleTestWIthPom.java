package practice.pom.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SampleTestWIthPom {

	@FindBy(name="user_name")
	WebElement ele1;
	@FindBy(name="user_password")
	WebElement ele2;
	@FindBy(id="submitButton")
	WebElement ele3;
	
	@Test
	public void sampleTest() {
		WebDriver driver= new ChromeDriver();
		driver.get("http://localhost:8888");
		SampleTestWIthPom s = PageFactory.initElements(driver, SampleTestWIthPom.class);
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("password");
		s.ele3.click();
	}
	
	
}
