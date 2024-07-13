package com.crm.comcast.contactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.comcast.listenerUtility.RealListenersImpClass;
import com.crm.generic.baseUtility.BaseClass;

import objectRepoUtility.ContactInfoPage;
import objectRepoUtility.ContactPage;
import objectRepoUtility.HomePage;
import objectRepoUtility.NewContactPage;
import objectRepoUtility.NewOrgPage;
import objectRepoUtility.OrgInfoPage;
import objectRepoUtility.OrganizationPage;

//@Listeners(com.crm.comcast.listenerUtility.RealListenersImpClass.class)
public class CreateContactWithExtentReportTest extends BaseClass{

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
		Assert.fail();//to take ss
		ncp.getSave().click();

		// verification using Hard Assert
		String header = cip.getHeaderMsg().getText();
		boolean status = header.contains(lastName);
		Assert.assertEquals(status, true);
	
		
		//Verification of lastname using soft assert
		SoftAssert soft= new SoftAssert();
		String actLastName= driver.findElement(By.id("dtlview_Last Name")).getText();
		soft.assertEquals(actLastName, lastName);

		soft.assertAll();
	
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
				
				boolean status = header.contains(lastName);
				Assert.assertEquals(status, true);
					
				
				//verify startDate and endDate
				String actStartDate = ncp.getActualStartDate().getText();
				String actEndDate = ncp.getActualEndDate().getText();
				boolean dateStatus = actStartDate.equals(startSysDate);
				SoftAssert soft=new SoftAssert();
				soft.assertEquals(actStartDate, startSysDate, "Failed to add"+startSysDate);
				soft.assertEquals(actEndDate, endSysDate, "Failed to add"+endSysDate);
			
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
				boolean orgStatus = header.contains(orgName);
				Assert.assertEquals(orgStatus, true);
				
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
				boolean contactStatus = headerContact.contains(lastName);
				Assert.assertEquals(contactStatus, true);
				
	}

}
