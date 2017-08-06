package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.ActionDriver;

public class HomePage extends ActionDriver{

	 @FindBy(xpath="//*[@id='main-header']/div[1]/div[2]/div[2]/a[1]/span[1]")
	    static WebElement loginLink;
	
	public HomePage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver,this);
		if(!isElementPresent(loginLink)){
			throw new Exception("User is not on HomePage and is on "+getTitle());
		}
	}

	
	public LoginPage clickLoginLink() throws Exception{
		click(loginLink);
		return new LoginPage(driver);
		
	}

}
