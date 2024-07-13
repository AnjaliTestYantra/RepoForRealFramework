package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadMultipleDataBasedOnConditionFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis= new FileInputStream("C:\\Users\\Anjali Unnikrishnan\\Desktop\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Org");
		String expectedData="tc_03";
		String data1="";
		String data2="";
		String data3="";
		boolean flag= false;
		int rowCount= sh.getLastRowNum();
		for(int i=0;i<=rowCount;i++) {
			String data="";
			data= sh.getRow(i).getCell(0).toString();
			//System.out.println(data);
			if(data.equals(expectedData)) {
				flag= true;
				data1=sh.getRow(i).getCell(1).toString();
				data2= sh.getRow(i).getCell(2).toString();
				data3=sh.getRow(i).getCell(3).toString();
				
			}
			
		}
		if(flag==false) {
			System.out.println("data not available");
		}else {
			System.out.println(data1);
			System.out.println(data2);
			System.out.println(data3);
		}
		

		wb.close();
	}

}
