package genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;
import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection conn;
	
	//Establish connection
	public void getDbConnection(String url, String username, String password) {
		try {
			Driver ref= new Driver();
			DriverManager.registerDriver(ref);
			conn= DriverManager.getConnection(url, username, password);
			}catch(Exception e) {
				
			}
	}
	public void getDbConnection() {
		try {
		Driver ref= new Driver();
		DriverManager.registerDriver(ref);
		conn= DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		}catch(Exception e) {
			
		}
		
	}
	
	//Close the DB connection
	public void closeDbConnection() {
		try {
			conn.close();
		}catch(Exception e) {
			
		}
	}
	
	//execute select query
	public ResultSet executeSelectQuery(String query) {
		ResultSet result= null;
		try {
			Statement stat = conn.createStatement();
			result= stat.executeQuery(query);
		}catch(Exception e) {
			
		}
		return result;
	}
	
	
	//execute non select query
	public int executeNonSelectQuery(String query) {
		int result= 0;
		try {
			Statement stat = conn.createStatement();
			result= stat.executeUpdate(query);
		}catch(Exception e) {
			
		}
		return result;
	}
   
}
