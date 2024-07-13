package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ToCreateOrgUsingCMDTest {
	@Test
	public void createOrgTest() throws EncryptedDocumentException, IOException {
		//read common data from cmd line
		String URL= System.getProperty("url");
		String BROWSER= System.getProperty("browser");
		String USERNAME= System.getProperty("username");
		String PASSWORD= System.getProperty("password");
		
		//generate random number
		Random ran= new Random();
		int r= ran.nextInt(1000);
		
		//read testscript data from excel file
		FileInputStream efis= new FileInputStream("C:\\Users\\Anjali Unnikrishnan\\Desktop\\code\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String orgName= wb.getSheet("Org").getRow(1).getCell(2).getStringCellValue()+r;
		wb.close();
		
		WebDriver driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		//login
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//go to org link and create org
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verification
		String header = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(header.contains(orgName)) {
			System.out.println(orgName+" is created successfully");
		}else {
			System.out.println(orgName+" creation is failed");
		
		}
		
		//logout
		
		WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action= new Actions(driver);
		action.moveToElement(administrator).click().perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
	}
	

}
