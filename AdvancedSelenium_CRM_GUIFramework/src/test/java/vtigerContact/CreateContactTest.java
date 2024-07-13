package vtigerContact;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;

public class CreateContactTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileUtility futil= new FileUtility();
		JavaUtility jutil= new JavaUtility();
		WebDriverUtility wutil= new WebDriverUtility();
		
		String BROWSER= futil.getDataFromPropertiesFile("browser");
		String URL= futil.getDataFromPropertiesFile("url");
		String USERNAME= futil.getDataFromPropertiesFile("username");
		String PASSWORD= futil.getDataFromPropertiesFile("password");
		
		int r= jutil.generateRandomNum();


		WebDriver driver=null;
		if(BROWSER.equals("chrome")) {
			driver= new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver= new FirefoxDriver();
		}else if(BROWSER.equals("edge")) {
			driver= new EdgeDriver();
		}else  {
			driver= new ChromeDriver();
		}
		wutil.maximizeWindow(driver);
		wutil.waitForPage(driver);
		driver.get(URL);
		
		//step 1:- Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("SubmitButton")).click();
		
		//Step 2: Navigate to contacts module
		driver.findElement(By.linkText("Contacts")).click();
		
		//STep 3: click on "create contact" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 4: Enter all the details and create contact by reading data from excel file
		String lastName= futil.getDataFromExcelFile("Contact", 1, 2)+r;
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		//verification
			String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(header.contains(lastName)) {
				System.out.println(lastName+" is created successfully");
			}else {
				System.out.println(lastName+" creation is failed");
			
			}
		
		//Step 5: logout
		
		WebElement administrator= driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.mouseHover(driver, administrator);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
	}


	}


