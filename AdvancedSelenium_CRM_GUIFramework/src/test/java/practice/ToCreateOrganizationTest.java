package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ToCreateOrganizationTest {

	public static void main(String[] args) throws IOException {
		
		//read commondata from properties file
		FileInputStream fis= new FileInputStream("C:\\Users\\Anjali Unnikrishnan\\Desktop\\commonData.properties");
		Properties prop= new Properties();
		prop.load(fis);
		String BROWSER= prop.getProperty("browser");
		String URL= prop.getProperty("url");
		String USERNAME= prop.getProperty("username");
		String PASSWORD= prop.getProperty("password");
		
		 //generate random number
	    Random ran= new Random();
	    int r = ran.nextInt(1000);
	    
		
		//read testdata from excel file
		FileInputStream efis= new FileInputStream("C:\\Users\\Anjali Unnikrishnan\\Desktop\\code\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
	   String orgName = wb.getSheet("Org").getRow(1).getCell(2).toString()+r;
	    wb.close();
		
	   
	    
		  WebDriver driver= new FirefoxDriver(); driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		  driver.get(URL);
		  
		 //Login to CRM 
		  
		 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		 driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		 driver.findElement(By.id("submitButton")).click();
		 
		 //Click on Organization link
		  driver.findElement(By.linkText("Organizations")).click();
		  
		  //Click on create organization button
		  driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		  
		  //enter details and create organization
		  driver.findElement(By.name("accountname")).sendKeys(orgName);
		  driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		  
		  //sign out
		  Actions action = new Actions(driver);
		  action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click().perform();
		  driver.findElement(By.linkText("Sign Out")).click();
		 
		  driver.quit();
		 

	}

}
