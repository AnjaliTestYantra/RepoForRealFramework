package practice.testNG;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.baseUtility.BaseClass;

import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import objectRepoUtility.HomePage;
import objectRepoUtility.NewOrgPage;
import objectRepoUtility.OrgInfoPage;
import objectRepoUtility.OrganizationPage;

public class SampleExtentReportTest extends BaseClass {
	@Test
	public void createOrg() throws Throwable, IOException {
		FileUtility futil = new FileUtility();
		JavaUtility jutil = new JavaUtility();

		OrganizationPage op = new OrganizationPage(driver);
		NewOrgPage nop = new NewOrgPage(driver);
		OrgInfoPage oip = new OrgInfoPage(driver);
		int r = jutil.generateRandomNum();
		String orgName = futil.getDataFromExcelFile("Org", 1, 2) + r;

		// Step 2: Navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();
		System.out.println("navigated to ORG page");
		// STep 3: click on "create organization" Button
		op.getNewOrg().click();

		// Step 4: Enter all the details and create organization by reading data from
		
		nop.getOrgName().sendKeys(orgName);
		nop.getSave().click();

		// verification
		String header = oip.getHeaderText().getText();
		boolean orgStatus = header.contains(orgName);
		Assert.assertEquals(orgStatus, true);
		
		//To attach Extent Report
		//Spark report Config
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("SampleTest");
		spark.config().setReportName("Sample");
		spark.config().setTheme(Theme.DARK);
		
		//Add environment info and sreate test
		ExtentReports report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "WIndows10");
		report.setSystemInfo("Browser", "Chrome");
		
		ExtentTest test= report.createTest("Sample Create Org");
		test.log(Status.INFO, "Login to app");
		test.log(Status.INFO, "Navigated to contact page");
		test.log(Status.INFO, "Create organization");
		 if("HDFC".equals("HDFC")) {
			 test.log(Status.PASS, "org is created");
			 
		 }else {
			 test.log(Status.FAIL, "org is not created");
		 }
			 
        report.flush();

		

	}

}
