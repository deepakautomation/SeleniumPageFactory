package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionDriver {
	
	protected WebDriver driver;
	long longWait=60;
	long shortWait=30;
	
	public ActionDriver(WebDriver driver) {
		this.driver=driver;
	}

	public void waitForElement(WebElement element){
		WebDriverWait wait=new WebDriverWait(driver, longWait);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void click(WebElement element){
		waitForElement(element);
		element.click();
	}
	
	 public void type(WebElement element, String text){
	    	waitForElement(element);
	    	element.clear();
	    	element.sendKeys(text);
	    }
	
	 public void navigateTo(String url){
    	 driver.get(url);
    }
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	
	public boolean isTextEqualTo(WebElement element, String text){
    	waitForElement(element);
    	return element.getText().equalsIgnoreCase(text);
    }
	
	
	public boolean isElementPresent(WebElement element){
    	waitForElement(element);
    	return element.isEnabled();
    	
    }

}
