package com.crm.comcast.orgTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.crm.generic.baseUtility.BaseClass;

import genericUtility.FileUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;
import objectRepoUtility.HomePage;
import objectRepoUtility.LoginPage;
import objectRepoUtility.NewOrgPage;
import objectRepoUtility.OrgInfoPage;
import objectRepoUtility.OrganizationPage;

public class CreateOrgTest extends BaseClass {
	@Test(groups = "smoke")
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
		// excel file
		nop.getOrgName().sendKeys(orgName);
		nop.getSave().click();

		// verification
		String header = oip.getHeaderText().getText();
		if (header.contains(orgName)) {
			System.out.println(orgName + " is created successfully");
		} else {
			System.out.println(orgName + " creation is failed");

		}

	}

	@Test(groups = "regressoin")
	public void createOrgWithIndustry() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		OrganizationPage op = new OrganizationPage(driver);
		NewOrgPage nop = new NewOrgPage(driver);
		OrgInfoPage oip = new OrgInfoPage(driver);
		int r = jutil.generateRandomNum();
		String orgName = futil.getDataFromExcelFile("Org", 1, 2) + r;
		// Step 2: Navigate to organization module
		hp.getOrglink().click();

		// STep 3: click on "create organization" Button
		op.getNewOrg().click();

		// Step 4: Enter all the details and create organization by reading data from
		// excel file

		String industry = futil.getDataFromExcelFile("Org", 4, 3);
		String type = futil.getDataFromExcelFile("Org", 4, 4);
		nop.createOrg(orgName, industry, type);

		// verification
		String header = oip.getHeaderText().getText();
		if (header.contains(orgName)) {
			System.out.println(orgName + " is created successfully");
		} else {
			System.out.println(orgName + " creation is failed");

		}
	}

	@Test(groups = "regressoin")
	public void createOrgWithPhoneNum() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		OrganizationPage op = new OrganizationPage(driver);
		NewOrgPage nop = new NewOrgPage(driver);
		OrgInfoPage oip = new OrgInfoPage(driver);
		int r = jutil.generateRandomNum();
		String orgName = futil.getDataFromExcelFile("Org", 1, 2) + r;

		// Step 2: Navigate to organization module
		hp.getOrglink().click();

		// STep 3: click on "create organization" Button
		op.getNewOrg().click();

		// Step 4: Enter all the details and create organization by reading data from
		// excel file

		String phNum = futil.getDataFromExcelFile("Org", 7, 3);
		nop.getOrgName().sendKeys(orgName);
		nop.getPhNumber().sendKeys(phNum);
		nop.getSave().click();

		// verification
		String header = oip.getHeaderText().getText();
		if (header.contains(orgName)) {
			System.out.println(orgName + " is created successfully");
		} else {
			System.out.println(orgName + " creation is failed");

		}
	}
}
