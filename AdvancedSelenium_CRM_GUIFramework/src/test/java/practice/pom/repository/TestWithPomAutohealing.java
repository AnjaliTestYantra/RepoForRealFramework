package practice.pom.repository;

import java.time.Duration;
import java.util.jar.Attributes.Name;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class TestWithPomAutohealing {

	@FindBy(name="user_name")
	WebElement ele1;
	
//	@FindBys({@FindBy(name = "user_password" ), @FindBy(xpath ="//input[@type='password']" ) })
//	WebElement ele2;
	
//	to check the working of FINDBYS by giving one wrong locator
	@FindBys({@FindBy(name = "password" ), @FindBy(xpath ="//input[@type='password']" ) })
	WebElement ele2;
	
//	@FindAll({@FindBy(id = "submitButton"), @FindBy(xpath = "//input[@type='submit']"), @FindBy(xpath = "//input[@value='Login']")})
//	WebElement ele3;
	
//	to check the working of FINDALL by giving one wrong locator
	@FindAll({@FindBy(id = "submit"), @FindBy(xpath = "//input[@type='submit']"), @FindBy(xpath = "//input[@value='Login']")})
	WebElement ele3;
	@Test
	public void sampleTest() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888");
		TestWithPomAutohealing pom = PageFactory.initElements(driver, TestWithPomAutohealing.class);
		pom.ele1.sendKeys("admin");
		pom.ele2.sendKeys("password");
		pom.ele3.click();
		driver.quit();
	}
	
	}

