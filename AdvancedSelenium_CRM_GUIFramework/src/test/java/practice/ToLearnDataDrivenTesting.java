package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ToLearnDataDrivenTesting {

	public static void main(String[] args) throws IOException {

		
		//Step 1:- Get the java representation object of the physical file
		
		FileInputStream fis= new FileInputStream("C:\\Users\\Anjali Unnikrishnan\\Desktop\\commonData.properties");
		
		//Step 2:- Using properties class, load all the keys
		
		Properties prop = new Properties();
		prop.load(fis);
		
		//Step 3:- Get the value based on key
		System.out.println(prop.getProperty("browser"));
		System.out.println(prop.getProperty("url"));
	}

}
