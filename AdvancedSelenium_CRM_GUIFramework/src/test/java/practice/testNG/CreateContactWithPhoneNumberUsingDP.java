package practice.testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactWithPhoneNumberUsingDP {
	@Test(dataProvider = "getData")
	public void createContactTest(String fname, String lname, long phNum) {
		System.out.println("FirstName: "+fname+" LastName: "+lname+" Phone Number: "+phNum);
	}
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr= new Object[3][3];
		objArr[0][0]= "Deepak";
		objArr[0][1]= "HR";
		objArr[0][2]= 9876543210l;// bcs its long
		objArr[1][0]= "Sam";
		objArr[1][1]= "SH";
		objArr[1][2]= 9876543212l;
		objArr[2][0]= "John";
		objArr[2][1]= "Smith";
		objArr[2][2]= 9876543219l;
		return objArr;
	}

}
