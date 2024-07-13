package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToCreateOrgUsingJson {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		//to read common data from json file
		
		JSONParser parser= new JSONParser();
		Object obj = parser.parse(new FileReader("C:\\Users\\Anjali Unnikrishnan\\Desktop\\code\\appCommonData.json"));
		JSONObject map= (JSONObject) obj;
		
		String BROWSER= map.get("browser").toString();
		String URL= map.get("url").toString();
		String USERNAME= map.get("username").toString();
		String PASSWORD= map.get("password").toString();
		
		 //generate random number
	    Random ran= new Random();
	    int r = ran.nextInt(1000);
	    		

		//read testdata from excel file
		FileInputStream efis= new FileInputStream("C:\\Users\\Anjali Unnikrishnan\\Desktop\\code\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
	   String orgName = wb.getSheet("Org").getRow(1).getCell(2).toString()+r;
	    wb.close();
	    
	    FirefoxOptions option= new FirefoxOptions();
	    option.addArguments("--incognito");
	    
		  WebDriver driver= new FirefoxDriver(option);
		  driver.manage().window().maximize();
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
		  
		//verification
			String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(header.contains(orgName)) {
				System.out.println(orgName+" is created successfully");
			}else {
				System.out.println(orgName+" creation is failed");
			
			}
		  //sign out
		  Actions action = new Actions(driver);
		  WebElement administrator= driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		  action.moveToElement(administrator).click().perform();
		  WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		 		  
		  WebElement signout = driver.findElement(By.linkText("Sign Out"));
		  wait.until(ExpectedConditions.elementToBeClickable(signout));
		  action.click(signout).perform();
		 
		  driver.quit();
		 

	

	}

}
