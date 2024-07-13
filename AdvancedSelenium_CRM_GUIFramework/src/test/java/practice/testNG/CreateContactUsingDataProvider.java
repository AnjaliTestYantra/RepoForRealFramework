package practice.testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactUsingDataProvider {

	@Test(dataProvider = "getData")
	public void createContactTest(String fname, String lname) {
		System.out.println("FirstName: "+fname+" LastName: "+lname);
	}
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr= new Object[3][2];
		objArr[0][0]= "Deepak";
		objArr[0][1]= "HR";
		objArr[1][0]= "Sam";
		objArr[1][1]= "SH";
		objArr[2][0]= "John";
		objArr[2][1]= "Smith";
		return objArr;
	}
}
