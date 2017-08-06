package dataprovider;

import java.io.File;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import utils.ExcelHandler;
import utils.ProjectProperty;

public class DataProviderForLogin {

	
	@DataProvider
	public static Object[][] getCredentails(ITestContext context) throws Exception{
		
		String excelName=context.getCurrentXmlTest().getParameter("excelName");
		String excelSheet=context.getCurrentXmlTest().getParameter("excelSheet");
		File location=new File(ProjectProperty.testDataFilePath+excelName);
		ExcelHandler excel=new ExcelHandler(location);
		excel.selectSheet(excelSheet);
		String username=excel.getCellData(1,1).getContents();
		String password=excel.getCellData(2,1).getContents();
		return new Object[][]{{username,password}};
	}
	
}
