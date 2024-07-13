package vtigerOrganization;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;

public class CreateOrgWIthPhoneNumTest {
	public static void main(String[] args) throws IOException {
		
		
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
		
		//Step 2: Navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		
		//STep 3: click on "create organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 4: Enter all the details and create organization by reading data from excel file
		String orgName= futil.getDataFromExcelFile("Org", 1, 2)+r;
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		String phNum = futil.getDataFromExcelFile("Org", 7, 3);
	
		driver.findElement(By.id("phone")).sendKeys(phNum);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		//verification
			String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(header.contains(orgName)) {
				System.out.println(orgName+" is created successfully");
			}else {
				System.out.println(orgName+" creation is failed");
			
			}
		
		//Step 5: logout
		
		WebElement administrator= driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.mouseHover(driver, administrator);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
	}

}
