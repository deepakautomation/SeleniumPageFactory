package utils;

import java.util.HashMap;

public class ProjectProperty {
	
	public static String fileSeperator;
	public static String currentWorkingDirectory;
	public static String chromeDriverPathForWindows;
	public static String chromeDriverPathForMac;
	public static String ffDriverPathForWindows;
	public static String ffDriverPathForMac;
	public static String configFilePath;
	public static String testDataFilePath;
	public static String screenshotFilePath;
	public HashMap<Object, Object> globalHashmap;
	
	public ProjectProperty(){
		fileSeperator = System.getProperty("file.separator");
		currentWorkingDirectory=System.getProperty("user.dir");
		chromeDriverPathForWindows=currentWorkingDirectory+fileSeperator+"src"+fileSeperator+"test"+fileSeperator+
				"resources"+fileSeperator+"drivers"+fileSeperator+"chromedriver.exe";
		chromeDriverPathForMac=currentWorkingDirectory+fileSeperator+"src"+fileSeperator+"test"+fileSeperator+
				"resources"+fileSeperator+"drivers"+fileSeperator+"chromedriver.exe";
		ffDriverPathForWindows=currentWorkingDirectory+fileSeperator+"src"+fileSeperator+"test"+fileSeperator+
				"resources"+fileSeperator+"drivers"+fileSeperator+"geckodriver.exe";
		ffDriverPathForMac=currentWorkingDirectory+fileSeperator+"src"+fileSeperator+"test"+fileSeperator+
				"resources"+fileSeperator+"drivers"+fileSeperator+"geckodriver.exe";
		configFilePath=currentWorkingDirectory+fileSeperator+"config.properties";
		testDataFilePath=currentWorkingDirectory+fileSeperator+"src"+fileSeperator+"test"+fileSeperator+
				"resources"+fileSeperator+"testdata"+fileSeperator;
		screenshotFilePath=currentWorkingDirectory+fileSeperator+"src"+fileSeperator+"test"+fileSeperator+
				"resources"+fileSeperator+"screenshot"+fileSeperator;
		globalHashmap = new HashMap<Object, Object>();
		
		loadConfigAndSystemPropertiesIntoHashMap();
		
	}

	private void loadConfigAndSystemPropertiesIntoHashMap() {
		globalHashmap  = ReaderUtility.storePropertiesInHashMap(ReaderUtility.readConfigFile(configFilePath));
		
	}
	
	public String getPropertyFromGlobalHashmap(String key){
		return globalHashmap.get(key).toString();
	}

	
	

}
