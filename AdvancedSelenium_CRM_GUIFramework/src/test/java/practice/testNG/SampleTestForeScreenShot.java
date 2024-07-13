package practice.testNG;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleTestForeScreenShot {

	public static void main(String[] args) throws IOException {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://www.amazon.in/");
		
		//STep 1: create an object of event firing webdriver/ takesscreenshot
		TakesScreenshot tks= (TakesScreenshot) driver;
		
		//Step 2: use screenshot methos to get file type ss
		File src = tks.getScreenshotAs(OutputType.FILE);
		
		//step 3: store ss in local system
		org.openqa.selenium.io.FileHandler.copy(src, new File("./errorShots/amazon.png"));
		
		driver.quit();
		
		
	}

}
