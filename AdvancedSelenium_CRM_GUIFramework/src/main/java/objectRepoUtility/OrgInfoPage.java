package objectRepoUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPage {
	WebDriver driver;

	public OrgInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 @FindBy(xpath = "//span[@class='dvHeaderText']")
	 private WebElement headerText;

	
	public WebElement getHeaderText() {
		return headerText;
	}
	 
	

}
