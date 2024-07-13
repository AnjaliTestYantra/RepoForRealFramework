package com.crm.generic.baseUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import genericUtility.DataBaseUtility;
import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import genericUtility.UtilityClassObject;
import genericUtility.WebDriverUtility;
import objectRepoUtility.HomePage;
import objectRepoUtility.LoginPage;

public class BaseClass {
	public DataBaseUtility dutil= new DataBaseUtility();
	public FileUtility futil= new FileUtility();
	public JavaUtility jutil= new JavaUtility();
	public WebDriverUtility wutil= new WebDriverUtility();
	public WebDriver driver= null;
	public static WebDriver sDriver= null;
	
	public HomePage hp;
	public LoginPage lp;
	
	
	
	@BeforeSuite(groups = {"smoke", "regressoin"})
	public void configBS() {
		System.out.println("Connect to DB, Report config");
		dutil.getDbConnection();
		
		
		
	}
	
	
	// @Parameters("BROWSER")
	
	@BeforeClass(groups = {"smoke", "regressoin"})
	public void configBC(/*String browser*/) throws IOException {
		
		System.out.println("Launch the Browser");
		//for mvn execution using CMD
		//String BROWSER= System.getProperty("browser");
		String BROWSER= futil.getDataFromPropertiesFile("browser");
		
		// String BROWSER= browser;//for CBT
		
		if(BROWSER.equals("chrome")) {
			driver= new ChromeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver= new FirefoxDriver();
		}else if(BROWSER.equals("edge")) {
			driver= new EdgeDriver();
		}else {
			driver= new ChromeDriver();
		}
		sDriver=driver;
		UtilityClassObject.setDriver(driver);
		wutil.waitForPage(driver);
	}
	
	@BeforeMethod(groups = {"smoke", "regressoin"})
	public void configBM() throws IOException {
		  lp= new LoginPage(driver);
		System.out.println("Login to application");
	    String URL= futil.getDataFromPropertiesFile("url");
		String USERNAME= futil.getDataFromPropertiesFile("username");
		String PASSWORD= futil.getDataFromPropertiesFile("password");
		driver.get(URL);
		wutil.maximizeWindow(driver);
		lp.loginToApp(USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups = {"smoke", "regressoin"})
	public void configAM() {
		 hp = new HomePage(driver);
		System.out.println("Log out");
		hp.logout();
	}
	
	@AfterClass(groups = {"smoke", "regressoin"})
	public void configAC() {
		System.out.println("Close the Browser");
		driver.quit();
	}
	
	@AfterSuite(groups = {"smoke", "regressoin"})
	public void configAS() {
		System.out.println("Close the DB, Report Backup");
		
		dutil.closeDbConnection();
	}

}
