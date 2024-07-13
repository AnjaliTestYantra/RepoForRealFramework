package com.crm.comcast.contactTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.generic.baseUtility.BaseClass;

import objectRepoUtility.ContactInfoPage;
import objectRepoUtility.ContactPage;
import objectRepoUtility.HomePage;
import objectRepoUtility.NewContactPage;
import objectRepoUtility.NewOrgPage;
import objectRepoUtility.OrgInfoPage;
import objectRepoUtility.OrganizationPage;

public class CreateContactTest extends BaseClass {
	

	
	@Test(groups = "smoke")
	public void createContact() throws Throwable, IOException {
		int r = jutil.generateRandomNum();
		String lastName = futil.getDataFromExcelFile("Contact", 1, 2) + r;
		HomePage hp= new HomePage(driver);
		 ContactPage cp = new ContactPage(driver);
		 NewContactPage ncp = new NewContactPage(driver);
		 ContactInfoPage cip = new ContactInfoPage(driver);
		
		// Step 2: Navigate to contacts module
		hp.getContactlink().click();

		// STep 3: click on "create contact" Button
		cp.getNewContact().click();

		// Step 4: Enter all the details and create contact by reading data from excel
		
		ncp.getLastname().sendKeys(lastName);
		ncp.getSave().click();

		// verification
		String header = cip.getHeaderMsg().getText();

		if (header.contains(lastName)) {
			System.out.println(lastName + " is created successfully");
		} else {
			System.out.println(lastName + " creation is failed");

		}

	
	}

	@Test(groups = "regressoin")
	public void createContactWithDate() throws Throwable, IOException {
		HomePage hp= new HomePage(driver);
		 ContactPage cp = new ContactPage(driver);
		 NewContactPage ncp = new NewContactPage(driver);
		 ContactInfoPage cip = new ContactInfoPage(driver);
		
		int r = jutil.generateRandomNum();
		String lastName= futil.getDataFromExcelFile("Contact", 1, 2)+r;
		//Step 2: Navigate to contacts module
				hp.getContactlink().click();
				
				//STep 3: click on "create contact" Button
				cp.getNewContact().click();
				
				//Step 4: Enter all the details and create contact by reading data from excel file
				ncp.getLastname().sendKeys(lastName);
				
				
				//Date using Java Class
				String startSysDate= jutil.getSystemDateYYYYMMDD();
				String endSysDate= jutil.getReqSysDateYYYYMMDD(30);
				ncp.getSupportSystemDate(startSysDate, endSysDate);
				Thread.sleep(5000);
			    
			    //Save the contact
				ncp.getSave().click();
				
				//verification of contact
				String header = cip.getHeaderMsg().getText();
				if(header.contains(lastName)) {
					System.out.println(lastName+" is created successfully");
				}else {
					System.out.println(lastName+" creation is failed");
				
				}
				
				//verify startDate and endDate
				String actStartDate = ncp.getActualStartDate().getText();
				String actEndDate = ncp.getActualEndDate().getText();
				if(actStartDate.equals(startSysDate)) {
					System.out.println(startSysDate+" added to contact successfully");
				}else {
					System.out.println("Failed to add "+startSysDate);
				}
		       if(actEndDate.equals(endSysDate)) {
		    	   System.out.println(endSysDate+" added to contact successfully");
					
				}else {
					System.out.println("Failed to add "+endSysDate);
					
				}
		       	}
	
	@Test(groups = "regressoin")
	public void createContactWIthOrgTest() throws EncryptedDocumentException, IOException {
		//HomePage hp= new HomePage(driver);
		 ContactPage cp = new ContactPage(driver);
		 NewContactPage ncp = new NewContactPage(driver);
		 ContactInfoPage cip = new ContactInfoPage(driver);
		
		OrganizationPage op= new OrganizationPage(driver);
		NewOrgPage nop= new NewOrgPage(driver);
		OrgInfoPage oip= new OrgInfoPage(driver);
		 
		int r = jutil.generateRandomNum();
		String orgName = futil.getDataFromExcelFile("Org", 1, 2) + r;
		String lastName = futil.getDataFromExcelFile("Contact", 1, 2) + r;
		
		// Step 2: Navigate to organization module
		HomePage hp= new HomePage(driver);
				hp.getOrglink().click();
				// STep 3: click on "create organization" Button
				op.getNewOrg().click();

				// Step 4: Enter all the details and create organization by reading data from
				nop.createOrg(orgName);
				System.out.println("created org");


				// verification
				String header = oip.getHeaderText().getText();
				if (header.contains(orgName)) {
					System.out.println(orgName + " is created successfully");
				} else {
					System.out.println(orgName + " creation is failed");

				}
				System.out.println("verified org");

				// STep 5: navigate to contact page
				HomePage hp1= new HomePage(driver);
				hp1.getContactlink().click();

				// Step 6: create new contact
				cp.getNewContact().click();

				// STep 7: enter lastname and org
				ncp.getLastname().sendKeys(lastName);
				ncp.getNewOrgInContact().click();
				ncp.addContactWithOrg(orgName);
				ncp.getSave().click();
				

				// verification of contact
				String headerContact = cip.getHeaderMsg().getText();
				if (headerContact.contains(lastName)) {
					System.out.println(lastName + " is created successfully");
				} else {
					System.out.println(lastName + " creation is failed");

				}
	}
}
