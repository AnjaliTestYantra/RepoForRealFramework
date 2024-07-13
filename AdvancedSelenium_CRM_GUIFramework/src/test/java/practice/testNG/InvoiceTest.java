package practice.testNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.listenerUtility.ListenersImplementationClass;
import com.crm.comcast.listenerUtility.RetryListenersImpClass;
import com.crm.generic.baseUtility.BaseClass;

//@Listeners(ListenersImplementationClass.class)
public class InvoiceTest extends BaseClass {
	@Test(retryAnalyzer = RetryListenersImpClass.class)
	public void invoiceTest() {
		System.out.println("Execute Create Invoice");
		String actTitle= driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("Step 1");
		System.out.println("Step 2");
		System.out.println("Step 3");
		System.out.println("Step 4");


	}
	@Test
	public void invoiceWithContactTest() {
		System.out.println("Execute Create Invoice with contact");
	
		System.out.println("Step 1");
		System.out.println("Step 2");
		System.out.println("Step 3");
		System.out.println("Step 4");


	}
	@Test(retryAnalyzer = RetryListenersImpClass.class)
	public void activateSim() {
		System.out.println("Execute Activate SIM");
		
		Assert.assertEquals("", "Login");
		System.out.println("Step 1");
		System.out.println("Step 2");
		System.out.println("Step 3");
		System.out.println("Step 4");
	}

}
