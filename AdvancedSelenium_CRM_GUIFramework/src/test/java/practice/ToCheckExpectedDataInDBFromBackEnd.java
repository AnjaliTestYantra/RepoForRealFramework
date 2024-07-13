package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class ToCheckExpectedDataInDBFromBackEnd {
	@Test
	public void projectCheckTest() throws SQLException {
		//String expectedProjName= "WOODENSTREET_03";
		String expectedProjName= "WOODENSTREET";
		//String expectedProjName="FireFlink_17987";
		boolean flag= false;
		
		
		//Step 1: Load and register the driver
		Driver ref= new Driver();
		DriverManager.registerDriver(ref);
		
		//Step 2: establish the connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		System.out.println("Connection is established");
		
		
		//Step 3: create statement
		Statement stat = conn.createStatement();
		
		//Step 4: Execute query
		ResultSet query = stat.executeQuery("select * from project");
		while(query.next()) {
			String actualProjName= query.getString(4);
			if(actualProjName.equals(expectedProjName)) {
				flag = true;
				System.out.println(expectedProjName+" is available in DB======> PASS");
			}
			
		}
		if(flag==false){
			System.out.println(expectedProjName+" is not available in DB======> FAIL");
			Assert.fail();// so that it shows TC failed
		}
		
		//Step 5: close the connection
		conn.close();
		System.out.println("Connectoin is closed");
		
	}

}
