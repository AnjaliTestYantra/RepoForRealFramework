package vtigerContact;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

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

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileUtility futil = new FileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String BROWSER = futil.getDataFromPropertiesFile("browser");
		String URL = futil.getDataFromPropertiesFile("url");
		String USERNAME = futil.getDataFromPropertiesFile("username");
		String PASSWORD = futil.getDataFromPropertiesFile("password");

		int r = jutil.generateRandomNum();

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		wutil.maximizeWindow(driver);
		wutil.waitForPage(driver);
		driver.get(URL);

		// step 1:- Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("SubmitButton")).click();

		// Step 2: Navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();

		// STep 3: click on "create organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 4: Enter all the details and create organization by reading data from
		// excel file
		String orgName = futil.getDataFromExcelFile("Org", 1, 2) + r;

		driver.findElement(By.name("accountname")).sendKeys(orgName);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verification
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (header.contains(orgName)) {
			System.out.println(orgName + " is created successfully");
		} else {
			System.out.println(orgName + " creation is failed");

		}

		// STep 5: navigate to contact page
		driver.findElement(By.linkText("Contacts")).click();

		// Step 6: create new contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// STep 7: enter lastname and org
		String lastName = futil.getDataFromExcelFile("Contact", 1, 2) + r;
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		String parentWindpw = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

//			//STep 8: switch to child window to add org
		Set<String> allWin = driver.getWindowHandles();
		allWin.remove(parentWindpw);
		for (String currWIn : allWin) {
			driver.switchTo().window(currWIn);
			driver.findElement(By.name("search_text")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.linkText(orgName)).click();
		}
		driver.switchTo().window(parentWindpw);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
//	        
//	        //step 8: switching window using iterator
//	       Set<String> set = driver.getWindowHandles();
//	       Iterator<String> it= set.iterator();
//	       while(it.hasNext()) {
//	    	   String windowId= it.next();
//	    	   driver.switchTo().window(windowId);
//	    	   String actUrl=driver.getCurrentUrl();
//	    	   if(actUrl.contains("module=Accounts")) {
//	    		   break;
//	    	   }
//	       }
//	       driver.findElement(By.name("search_text")).sendKeys(orgName);
//			driver.findElement(By.name("search")).click();
//			driver.findElement(By.linkText(orgName)).click();
//			
//			//Switch back to parent wind using iterator
//			Set<String> set1 = driver.getWindowHandles();
//			 Iterator<String> it1= set1.iterator();
//			 while(it1.hasNext()) {
//				 String windowId= it.next();
//		    	   driver.switchTo().window(windowId);
//		    	   String actUrl=driver.getCurrentUrl();
//		    	   if(actUrl.contains("Contacts&action")) {
//		    		   break;
//		    	   }
//			 }
//			
		// verification of contact
		String headerContact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerContact.contains(lastName)) {
			System.out.println(lastName + " is created successfully");
		} else {
			System.out.println(lastName + " creation is failed");

		}

		// Step 5: logout

		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.mouseHover(driver, administrator);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
