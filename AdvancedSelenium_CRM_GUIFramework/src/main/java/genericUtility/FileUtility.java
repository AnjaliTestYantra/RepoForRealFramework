package genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileUtility {

	//properties file
	public String getDataFromPropertiesFile(String key) throws IOException {
		FileInputStream pfis= new FileInputStream("./vtiger-commonData/commonData.properties");
		Properties prop= new Properties();
		prop.load(pfis);
		String data= prop.getProperty(key);
		return data;
	}

	//JSON file
	public String getDataFromJsonFile(String key) throws IOException, ParseException {
		FileReader file= new FileReader("./vtiger-commonData/appCommonData.json");
		JSONParser parser= new JSONParser();
		Object obj = parser.parse(file);
		JSONObject map= (JSONObject) obj;
		String data= map.get(key).toString();
		return data;
	}

	//Excel file
	public String getDataFromExcelFile(String sheetName, int row, int cell) throws EncryptedDocumentException, IOException {
		FileInputStream efis= new FileInputStream("./vtiger-testData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String data = wb.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
		wb.close();
		return data;
	}
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream efis= new FileInputStream("./vtiger-testData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;
	}
	public void setDataBackToExcel(String sheetName, int row, int cell, String data) throws EncryptedDocumentException, IOException {
		FileInputStream efis= new FileInputStream("./vtiger-testData/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		wb.getSheet(sheetName).getRow(row).createCell(cell);
		FileOutputStream fos= new FileOutputStream("./vtiger-testData/testScriptData.xlsx");
		wb.write(fos);
		wb.close();
	}
	
	

}
