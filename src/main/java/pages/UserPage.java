package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.ActionDriver;

public class UserPage extends ActionDriver{
	
	
	@FindBy(xpath="//*[@id='feed-posting-text']/p[1]")
    static WebElement verifyUser;
	
	
	
	public UserPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver,this);
		if(!isElementPresent(verifyUser)){
			throw new Exception("User is not on UserPage and is on "+getTitle());
		}
	}
	
	
	public boolean verifyUser(String text){
		return isTextEqualTo(verifyUser, text);
	}
	
	

}
