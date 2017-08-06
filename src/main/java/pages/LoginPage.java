package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.ActionDriver;

public class LoginPage extends ActionDriver {

	@FindBy(xpath = "//*[@id='username_login']")
	static WebElement getUsername;

	@FindBy(xpath = "//*[contains(@id,'loginBtn')]")
	static WebElement getLoginBtn;

	@FindBy(xpath = "//*[@id='password_login']")
	static WebElement getPassword;

	public LoginPage(WebDriver driver) throws Exception {
		super(driver);
		PageFactory.initElements(driver,this);
		if (!isElementPresent(getUsername)) {
			throw new Exception("User is not on LoginPage and is on " + getTitle());
		}
	}

	public UserPage signIn(String username, String password) throws Exception {

		type(getUsername, username);
		type(getPassword, password);
		click(getLoginBtn);
		return new UserPage(driver);
	}

	public LoginPage getUsername(String username) {
		type(getUsername, username);
		return this;
	}

	public LoginPage getPassword(String password) {
		type(getPassword, password);
		return this;
	}

	public UserPage clickLoginBtnPositive() throws Exception {
		click(getLoginBtn);
		return new UserPage(driver);
	}

	public LoginPage clickLoginBtnNegative() throws Exception {
		click(getLoginBtn);
		return this;
	}

	

}
