package practice.pom.repository;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;
import objectRepoUtility.HomePage;
import objectRepoUtility.LoginPage;
import objectRepoUtility.NewOrgPage;
import objectRepoUtility.OrgInfoPage;
import objectRepoUtility.OrganizationPage;

public class DeleteOrgWithPomTest {
	public static void main(String[] args) throws Throwable {

		FileUtility futil = new FileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String BROWSER = futil.getDataFromPropertiesFile("browser");
		String URL = futil.getDataFromPropertiesFile("url");
		String USERNAME = futil.getDataFromPropertiesFile("username");
		String PASSWORD = futil.getDataFromPropertiesFile("password");

		int r = jutil.generateRandomNum();
		String orgName = futil.getDataFromExcelFile("Org", 1, 2) + r;

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		wutil.maximizeWindow(driver);
		wutil.waitForPage(driver);
		driver.get(URL);

		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		OrganizationPage op = new OrganizationPage(driver);
		NewOrgPage nop = new NewOrgPage(driver);
		OrgInfoPage oip = new OrgInfoPage(driver);

		// step 1:- Login to application

		lp.loginToApp(USERNAME, PASSWORD);

		// Step 2: Navigate to organization module
		hp.getOrglink().click();

		// Step 3: click on "create organization" Button
		op.getNewOrg().click();

		// Step 4: Enter all the details and create organization by reading data from
		// excel file

		nop.createOrg(orgName);

		// verification
		String headerInfo = oip.getHeaderText().getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is created successfully");
		} else {
			System.out.println(orgName + " creation is failed");

		}

		// Step 5: Go back to organization page
		hp.getOrglink().click();

		// Step 6: Search for organization
		nop.getSearchEdit().sendKeys(orgName);
		wutil.select(nop.getSearchDD(), "Organization Name");
		nop.getSearchButton().click();

		// Step 7: In dynamic web table select the newly created org and delete Org
		driver.findElement(By.xpath("//a[text()='" + orgName + "']/../..//a[text()='del']")).click();
		// as this is DYNAMIC element, we wont store it in POM
		wutil.switchToAlertAccept(driver);
		
		System.out.println(orgName+" deleted successfully");

		// Step 8: logout
		hp.logout();
		driver.quit();

	}

}
