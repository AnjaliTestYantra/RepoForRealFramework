package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToLearnDDTUsingExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//Step 1: get the excel path location&java object of the physical excel file
		FileInputStream fis= new FileInputStream("C:\\Users\\Anjali Unnikrishnan\\Desktop\\testScriptData.xlsx");
	
		//Step 2: Open workbook in read mode
		Workbook wb = WorkbookFactory.create(fis);
		
		//Step 3: Get the control of Org sheet
		Sheet sheet = wb.getSheet("Org");
		
		//Step 4: get the control of row 
		Row row = sheet.getRow(1);
		
		//Step 5: get the control of cell
		Cell cell = row.getCell(2);
		
		String data= cell.getStringCellValue();
		System.out.println(data);
		
		//Step 6: Close the workbook
		wb.close();
		
		
		
		
	}

}
