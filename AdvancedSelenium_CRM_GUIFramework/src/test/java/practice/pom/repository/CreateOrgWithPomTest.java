package practice.pom.repository;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;
import objectRepoUtility.HomePage;
import objectRepoUtility.LoginPage;
import objectRepoUtility.NewOrgPage;
import objectRepoUtility.OrgInfoPage;
import objectRepoUtility.OrganizationPage;

public class CreateOrgWithPomTest {


@Test
public void createOrgTest() throws Throwable{
		
		FileUtility futil= new FileUtility();
		JavaUtility jutil= new JavaUtility();
		WebDriverUtility wutil= new WebDriverUtility();
		
		String BROWSER= futil.getDataFromPropertiesFile("browser");
		String URL= futil.getDataFromPropertiesFile("url");
		String USERNAME= futil.getDataFromPropertiesFile("username");
		String PASSWORD= futil.getDataFromPropertiesFile("password");
		
		int r= jutil.generateRandomNum();
		String orgName= futil.getDataFromExcelFile("Org", 1, 2)+r;

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
		
		LoginPage lp= new LoginPage(driver);
		HomePage hp= new HomePage(driver);
		OrganizationPage op= new OrganizationPage(driver);
		NewOrgPage nop= new NewOrgPage(driver);
		OrgInfoPage oip= new OrgInfoPage(driver);
		
		
		//step 1:- Login to application
         
         lp.loginToApp(USERNAME, PASSWORD);
		
		//Step 2: Navigate to organization module
		hp.getOrglink().click();
		
		//STep 3: click on "create organization" Button
		op.getNewOrg().click();
		
		//Step 4: Enter all the details and create organization by reading data from excel file
		
		nop.createOrg(orgName);
	
		  
		//verification
			String headerInfo= oip.getHeaderText().getText();
			if(headerInfo.contains(orgName)) {
				System.out.println(orgName+" is created successfully");
			}else {
				System.out.println(orgName+" creation is failed");
			
			}
		
		//Step 5: logout
		hp.logout();
		driver.quit();
		

	}

}
