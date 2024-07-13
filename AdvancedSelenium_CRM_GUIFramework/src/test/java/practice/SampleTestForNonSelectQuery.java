package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleTestForNonSelectQuery {

	public static void main(String[] args) throws SQLException {
		
		//Step 1: Load and Register the driver
		Driver ref= new Driver();
		DriverManager.registerDriver(ref);
		
		//Step 2: Establish the connection to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		System.out.println("Established the connection successfully");
		
		//Step 3: create the statement
		Statement stat = conn.createStatement();
		
		//Step 4: execute non select query
		int query = stat.executeUpdate("insert into project values('TY_PROJ_2003','Anjali','06/26/2024','WOODENSTREET_03','On Going','300');");
		System.out.println(query);
		
		//Step 5: close the connection
		conn.close();
        System.out.println("Closed the connection");
	}

}
