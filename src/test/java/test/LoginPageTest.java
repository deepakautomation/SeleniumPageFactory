package test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import core.OpenAndCloseBrowser;
import listeners.CustomListeners;
import pages.HomePage;
import pages.LoginPage;
import pages.UserPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Listeners(CustomListeners.class)
public class LoginPageTest extends OpenAndCloseBrowser{

	
//public static final String loginSteps="1. Click on LoginLink<br> 2. Fill in Credentials<br> 3. Verify user";	
	
    @Features("Meritnation Login")
    @Stories("Login with valid credentials")
    @Step("User open Meritnation.com, enter credentials, click on login button")
    @Severity(SeverityLevel.CRITICAL)
	@Test(dataProviderClass=dataprovider.DataProviderForLogin.class,dataProvider="getCredentails"/*,
			retryAnalyzer = utils.RetryAnalyzer.class*/)
	public void loginFunc(String username, String password) throws Exception{
		
		HomePage homePage=new HomePage(driver);
		LoginPage loginPage = homePage.clickLoginLink();
		UserPage userPage = loginPage.signIn(username, password);
		assert userPage.verifyUser("What new with  yo"):"Expected: failed";
		
	}
    
   
}
