package objectRepoUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="user_name")
	private WebElement uname;
	
	@FindBy(name="user_password")
	private WebElement pwd;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	public WebElement getUname() {
		return uname;
	}

	public WebElement getPasswrod() {
		return pwd;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public void loginToApp(String username, String password) {
		uname.sendKeys(username);
		pwd.sendKeys(password);
		loginBtn.click();
	}
	
}
