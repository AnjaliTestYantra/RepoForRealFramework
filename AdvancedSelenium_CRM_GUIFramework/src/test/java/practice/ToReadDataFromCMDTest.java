package practice;

import org.testng.annotations.Test;

public class ToReadDataFromCMDTest{
	@Test
	public void readdataFromCmd() {
		String URL= System.clearProperty("url");
		String BROWSER= System.clearProperty("browser");
		String USERNAME= System.clearProperty("username");
		String PASSWORD= System.clearProperty("password");
		System.out.println(URL);
		System.out.println(BROWSER);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		
	}

}
