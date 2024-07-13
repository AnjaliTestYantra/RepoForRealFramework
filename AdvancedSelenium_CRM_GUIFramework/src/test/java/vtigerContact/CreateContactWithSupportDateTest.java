package vtigerContact;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

public class CreateContactWithSupportDateTest {
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
		
		//Date using Java Class
		Date dateObj= new Date();
		SimpleDateFormat simple= new SimpleDateFormat("yyyy-MM-dd");
		String startDate = simple.format(dateObj);
		Calendar calendar = simple.getCalendar();
		calendar.add(Calendar.DAY_OF_MONTH,30);
	    String endDate = simple.format(calendar.getTime());
	    driver.findElement(By.name("support_start_date")).clear();
	    driver.findElement(By.name("support_start_date")).sendKeys(startDate);
	    driver.findElement(By.name("support_end_date")).clear();
	    driver.findElement(By.name("support_end_date")).sendKeys(endDate);
	    
	    //Save the contact
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verification of contact
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(lastName)) {
			System.out.println(lastName+" is created successfully");
		}else {
			System.out.println(lastName+" creation is failed");
		
		}
		
		//verify startDate and endDate
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actStartDate.equals(startDate)) {
			System.out.println(startDate+" added to contact successfully");
		}else {
			System.out.println("Failed to add "+startDate);
		}
       if(actEndDate.equals(endDate)) {
    	   System.out.println(endDate+" added to contact successfully");
			
		}else {
			System.out.println("Failed to add "+endDate);
			
		}
       
     //Step 5: logout
		
     		WebElement administrator= driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
     		wutil.mouseHover(driver, administrator);
     		driver.findElement(By.linkText("Sign Out")).click();
     		driver.quit();
     		
	}

}
