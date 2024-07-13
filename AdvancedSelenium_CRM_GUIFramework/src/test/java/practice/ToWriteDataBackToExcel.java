package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToWriteDataBackToExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis= new FileInputStream("C:\\Users\\Anjali Unnikrishnan\\Desktop\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Org");
		Row r = sh.getRow(1);
		Cell c = r.getCell(4);
		c.setCellType(CellType.STRING);
		c.setCellValue("PASS");
		FileOutputStream fos= new FileOutputStream("C:\\\\Users\\\\Anjali Unnikrishnan\\\\Desktop\\\\testScriptData.xlsx");
		wb.write(fos);
		wb.close();
		System.err.println("Executed");
		
		
	}

}
