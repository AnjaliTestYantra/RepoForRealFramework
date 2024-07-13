package practice.testNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfo {
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
	public Object[][] getData(){
		Object[][] objArr= new Object[3][2];
		objArr[0][0]= "iphone";
		objArr[0][1]= "Apple iPhone 15 (128 GB) - Black";
		objArr[1][0]= "iphone";
		objArr[1][1]= "Apple iPhone 13 (128GB) - Blue";
		objArr[2][0]= "iphone";
		objArr[2][1]= "Apple iPhone 13 (128GB) - Pink";
		return objArr;
		
	}

}
