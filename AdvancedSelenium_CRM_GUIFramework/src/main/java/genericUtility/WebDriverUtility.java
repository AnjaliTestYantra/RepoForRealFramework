package genericUtility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitForPage(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	}
	public void elementToBePresent(WebDriver driver, WebElement ele) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void switchNewBrowserTab(WebDriver driver, String partialUrl) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it= set.iterator();
		while(it.hasNext()) {
			String windowId= it.next();
			driver.switchTo().window(windowId);
			String actUrl=driver.getCurrentUrl();
			if(actUrl.contains(partialUrl)) {
				break;
			}
		}
	}
	public void switchToTabOnTitle(WebDriver driver, String partialTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it= set.iterator();
		while(it.hasNext()) {
			String windowId= it.next();
			driver.switchTo().window(windowId);
			String actUrl=driver.getTitle();
			if(actUrl.contains(partialTitle)) {
				break;
			}
		}
	}
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameId) {
		driver.switchTo().frame(nameId);
	}
	public void switchToFrame(WebDriver driver,WebElement ele) {
		driver.switchTo().frame(ele);
	}
	public void switchToAlertAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void switchToAlertCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	public void select(WebElement ele, String visibleText) {
		Select sel= new Select(ele);
		sel.selectByVisibleText(visibleText);
	}
	public void select(WebElement ele, int index) {
		Select sel= new Select(ele);
		sel.selectByIndex(index);
	}
	public void select(String value, WebElement ele) {
		Select sel= new Select(ele);
		sel.selectByValue(value);
	}
	public void mouseHover(WebDriver driver, WebElement ele) {
		Actions action= new Actions(driver);
		action.moveToElement(ele).perform();
	}
	public void doubleClick(WebDriver driver, WebElement ele) {
		Actions action= new Actions(driver);
		action.doubleClick(ele).perform();
	}
	public void rightClick(WebDriver driver, WebElement ele) {
		Actions action= new Actions(driver);
		action.contextClick(ele).perform();
	}
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}



}
