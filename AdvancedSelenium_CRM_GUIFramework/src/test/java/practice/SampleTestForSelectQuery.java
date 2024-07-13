package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class SampleTestForSelectQuery {

	public static void main(String[] args) throws SQLException {
		
		//Step 1: load & register the DB driver
		Driver ref= new Driver();
		DriverManager.registerDriver(ref);
		
		//Step 2: connect to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		System.out.println("connection established successfully");
		
		//Step 3: Create SQL Statement
		Statement stat = conn.createStatement();
		
		//Step 4: Execute Select Query and get result
		ResultSet query = stat.executeQuery("select * from project");
		while(query.next()) {
			String result = query.getString(1)+"\t"+query.getString(2)+"\t"+query.getString(3)+"\t"+query.getString(4)+"\t"+query.getString(5)+"\t"+query.getString(6);
			System.out.println(result);
		}
		
		//Step 5: Close connection
		conn.close();
		System.out.println("Connection is closed");

	}

}
