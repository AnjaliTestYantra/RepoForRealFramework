package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.jdbc.Driver;

public class ToWorkOnHRMAndVerifyDataInDBWithGUI {

	public static void main(String[] args) throws SQLException {
		String projName= "MakeMyTrip_03";
		String projManager="Anjali";
		//create project in GUI using selenium code
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://106.51.90.215:8084/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		WebElement signin = driver.findElement(By.xpath("//button[text()='Sign in']"));
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(signin));
		signin.click();
		
		//go to projects link and create project
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys(projName);
		driver.findElement(By.name("createdBy")).sendKeys(projManager);
		WebElement statusDD = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		Select sel= new Select(statusDD);
		sel.selectByIndex(1);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
			
		//verify the project in backend(DB) using JDBC
		Driver ref= new Driver();
		DriverManager.registerDriver(ref);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		System.out.println("Database connection established");
		Statement stat = conn.createStatement();
		ResultSet query = stat.executeQuery("select * from project");
		boolean flag= false;
		String actProjName="";
		while(query.next()) {
			 actProjName= query.getString(4);
			if(actProjName.equals(projName)) {
				flag= true;
				System.out.println(actProjName+" is available in DB===>PASS");
			}
		}
		if(flag==false) {
			System.out.println(actProjName+" is not available in DB===>FAIL");
		}
		
		conn.close();
		System.out.println("Database connection closed");
		
		//close the browser
		driver.quit();
	}
	

}
