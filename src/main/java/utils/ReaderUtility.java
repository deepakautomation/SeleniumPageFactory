package utils;
import java.io.*;
import java.util.*;

public class ReaderUtility {


    public static List<String[]> getCompleteRows(String FilePath) throws IOException {

        File inFile = new File(FilePath);
        List<String[]> completeData = new ArrayList<String[]>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inFile));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
            throw e1;
        }
        try {
            String csvRow = "";

            // Skip reading the header
            // reader.readLine();
            while ((csvRow = reader.readLine()) != null) {
                String[] csvRowAsArray = csvRow.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for (int i = 0; i < csvRowAsArray.length; i++) {
                    csvRowAsArray[i] = csvRowAsArray[i].trim();
                    if (csvRowAsArray[i].startsWith("\"") && csvRowAsArray[i].endsWith("\"")) {
                        csvRowAsArray[i] = csvRowAsArray[i].substring(1, csvRowAsArray[i].length() - 1);
                        csvRowAsArray[i] = csvRowAsArray[i].trim();
                    }
                    if (csvRowAsArray[i].contains("\"\"")) {
                        csvRowAsArray[i] = csvRowAsArray[i].replaceAll("\"\"", "\"");
                    }
                }
                completeData.add(csvRowAsArray);
            }
        } catch (Exception e) {

        } finally {
            reader.close();
        }
        return completeData;
    }


    public static String getCellValue(List<String[]> fileData, int row, String columnHeader) throws Exception{
        try {
            int columnNumber = 0;
            String[] headers = fileData.get(0);

            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equalsIgnoreCase(columnHeader)) {
                    columnNumber = i;
                    break;
                }
            }

            String[] requiredRow = fileData.get(row);
            return requiredRow[columnNumber];
        }catch (Exception e){
            throw e;
        }
    }

	public static Properties readConfigFile(String filePath){
	   try{
	   File file = new File(filePath);
	   FileInputStream inputStream = new FileInputStream(file);
	   Properties prop = new Properties();
	   prop.load(inputStream);
	   return prop;
	   }catch(Exception e){
		   e.printStackTrace();
		   return null;
	   }
	   
	   
   }
	
	public static HashMap<Object, Object> storePropertiesInHashMap(Properties prop){
		try{
			HashMap<Object, Object> map = new HashMap<Object, Object>();
			for(final Map.Entry<Object, Object> entry : prop.entrySet()){
				map.put(entry.getKey(),entry.getValue());
			}
			return map;
		}catch(Exception e){
			   e.printStackTrace();
			   return null;
		   }
		
	}
}
