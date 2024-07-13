package practice.testNG;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import genericUtility.FileUtility;

public class CreateContactUsingDPDynamically {


	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		//Search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);
		
		//Capture product info
		String x = "//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div/div/div/a/span/span[2]/span[2]";
		String price= driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit();
	}
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		FileUtility futil= new FileUtility();
		int rowCount = futil.getRowCount("Amazon");
		Object[][] objArr= new Object[rowCount][2];
		for(int i= 0;i<rowCount;i++) {
			objArr[i][0]= futil.getDataFromExcelFile("Amazon", i+1, 0);
			objArr[i][1]= futil.getDataFromExcelFile("Amazon", i+1, 1);
		
		
		}
		return objArr;
		
		
		
	}

}
