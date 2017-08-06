package core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import utils.ProjectProperty;

public class OpenAndCloseBrowser {
	
	public static WebDriver driver;
	public static ProjectProperty projectProperty;
	public static String currentOS;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static DesiredCapabilities caps;
	
	public OpenAndCloseBrowser(){
		projectProperty = new ProjectProperty();
		currentOS = System.getProperty("os.name");
	}
	
	
	@BeforeClass
	@Parameters({"grid"})
	public void openBrowser(String grid) throws MalformedURLException{
		
		if(projectProperty.getPropertyFromGlobalHashmap("browser").equalsIgnoreCase("chrome")){
			configureChrome(grid);
		}else if(projectProperty.getPropertyFromGlobalHashmap("browser").equalsIgnoreCase("firefox")){
			configureFirefox(grid);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(projectProperty.getPropertyFromGlobalHashmap("url"));
		log.debug("Execution started");
	
	}
	
	@Parameters({"grid"})
	private void configureFirefox(String grid) throws MalformedURLException {
		if(grid.equalsIgnoreCase("yes")){
		if(currentOS.toLowerCase().contains("windows")){
			System.setProperty("webdriver.chrome.driver", ProjectProperty.ffDriverPathForWindows);
			caps.setPlatform(Platform.WINDOWS);
			caps= DesiredCapabilities.firefox();
		}else if(currentOS.toLowerCase().contains("mac")){
			System.setProperty("webdriver.chrome.driver", ProjectProperty.ffDriverPathForMac);
			caps.setPlatform(Platform.WINDOWS);
			caps= DesiredCapabilities.firefox();
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);
		}else{
			if(currentOS.toLowerCase().contains("windows")){
				System.setProperty("webdriver.chrome.driver", ProjectProperty.ffDriverPathForWindows);
			}else if(currentOS.toLowerCase().contains("mac")){
				System.setProperty("webdriver.chrome.driver", ProjectProperty.ffDriverPathForMac);
			}
			driver = new FirefoxDriver();
			
		}
	}
	
	
	private void configureChrome(String grid) throws MalformedURLException {
		if(grid.equalsIgnoreCase("yes")){
		if(currentOS.toLowerCase().contains("windows")){
			System.setProperty("webdriver.chrome.driver", ProjectProperty.chromeDriverPathForWindows);

			caps= DesiredCapabilities.chrome();
		}else if(currentOS.toLowerCase().contains("mac")){
			System.setProperty("webdriver.chrome.driver", ProjectProperty.chromeDriverPathForMac);
			caps.setPlatform(Platform.WINDOWS);
			caps= DesiredCapabilities.chrome();
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);
		}else{
			if(currentOS.toLowerCase().contains("windows")){
				System.setProperty("webdriver.chrome.driver", ProjectProperty.chromeDriverPathForWindows);
			}else if(currentOS.toLowerCase().contains("mac")){
				System.setProperty("webdriver.chrome.driver", ProjectProperty.chromeDriverPathForMac);
			}
			driver = new ChromeDriver();
			
		}
	}

	@AfterClass
	public void closeBrowser(){
		driver.quit();
		log.debug("Execution stopped");
	}

	public static WebDriver driverInstance() {
		
		return driver;
	}
}
