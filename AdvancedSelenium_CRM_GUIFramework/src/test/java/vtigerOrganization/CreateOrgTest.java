package vtigerOrganization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest {

	public static void main(String[] args) throws IOException {
	FileInputStream pfis= new FileInputStream("C:\\Users\\Anjali Unnikrishnan\\Desktop\\commonData.properties");
		Properties prop = new Properties();
		prop.load(pfis);
		
		String BROWSER= prop.getProperty("browser");
		String URL= prop.getProperty("url");
		String USERNAME= prop.getProperty("username");
		String PASSWORD= prop.getProperty("password");
		WebDriver driver= null;
		if(BROWSER.equals("chrome")) {
			driver= new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver= new FirefoxDriver();
		}else if(BROWSER.equals("edge")) {
			driver= new EdgeDriver();
		}else  {
			driver= new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
		FileInputStream efis= new FileInputStream("C:\\Users\\Anjali Unnikrishnan\\Desktop\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		Sheet sh= wb.getSheet("Org");
		Row r = sh.getRow(1);
		String orgName= r.getCell(2).toString();
		wb.close();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save[Alt+s]']")).click();
		
		//Step 5: logout
		Actions action= new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
		
		

	}
	

}
