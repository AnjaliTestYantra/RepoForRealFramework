package practice.testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CreateContactTest {
	@Test
	public void createContact() {
		System.out.println("Execute createContact ");
	}
	@Test
	public void createContactWithSupportDate() {
		System.out.println("Execute createContactWithSupportDate ");
	}

	@BeforeMethod
	public void configBM() {
		System.out.println("Execute BM");
	}
	@BeforeClass
	public void configBC() {
		System.out.println("Execute BC");
	}
	@AfterMethod
	public void configAM() {
		System.out.println("Execute AM");
	}
	@AfterClass
	public void configAC() {
		System.out.println("Execute AC");
	}
	@BeforeSuite
	public void configBS() {
		System.out.println("Execute BS");
	}
	@AfterSuite
	public void configAS() {
		System.out.println("Execute AS");
	}
}
