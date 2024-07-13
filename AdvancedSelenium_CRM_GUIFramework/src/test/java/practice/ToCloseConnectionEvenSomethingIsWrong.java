package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ToCloseConnectionEvenSomethingIsWrong {

	public static void main(String[] args) throws SQLException {
		Connection conn= null;
		
		try{
			//step 1: load and register the driver
		Driver ref= new Driver();
		DriverManager.registerDriver(ref);
		
		//step 2: establish the connection to DB
		 conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		System.out.println("Established the connection");
		
		//step 3: create statement
		Statement stat = conn.createStatement();
		
		//step 4: executequery
		ResultSet query = stat.executeQuery("select * from prj");// given wrong query to check DB connection gets closed or not
		//ResultSet query = stat.executeQuery("select * from project");
		System.out.println("query executed");
		while(query.next()) {
		String projName = query.getString(4);
		System.out.println(projName);
		}
		
		}catch (Exception e) {
			System.out.println("Handled the exception");
     	}
		finally {
			//step 5: close the connection
			conn.close();
			System.out.println("Connectoin is closed");
		}
		
		

	}

}
