package listeners;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import core.OpenAndCloseBrowser;
import ru.yandex.qatools.allure.annotations.Attachment;
import utils.ProjectProperty;

public class CustomListeners extends TestListenerAdapter {
	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	  @Override
	  public void onTestFailure(ITestResult tr) {
		  try {
			 String methodname=tr.getName().toString().trim();
			screenshot(tr);
			saveImageAttach(methodname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	 
	  @Override
	  public void onTestSkipped(ITestResult tr) {
	  }
	 
	  @Override
	  public void onTestSuccess(ITestResult tr) {
	  }
	  
	  
	  static File location=null;
	  public void screenshot(ITestResult tr) throws IOException{
		  System.setProperty(ESCAPE_PROPERTY, "false");
		  WebDriver driver=OpenAndCloseBrowser.driverInstance();
		  Date date=new Date();
		  DateFormat dateFormat=new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ssaa");
		  File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  location=new File(ProjectProperty.screenshotFilePath+tr.getName()+
				  dateFormat.format(date)+".png");
		  FileUtils.copyFile(src, location);
		 
		  	  
	  }
	  
	  @Attachment(value = "{0}", type = "image/png")
	    public static byte[] saveImageAttach(String methodname) {
	        byte[] res=null;
	        try {
	            BufferedImage image = ImageIO.read(location);
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(image, "png", baos);
	            res=baos.toByteArray();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return res;
	    }

}
