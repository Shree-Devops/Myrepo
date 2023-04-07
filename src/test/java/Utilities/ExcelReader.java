package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public XSSFWorkbook wb = null;
	public XSSFSheet sh = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	
	public ExcelReader (String path) {
		try {
			this.path = path;
			fis = new FileInputStream(path);
			wb = new XSSFWorkbook(fis);
			sh = wb.getSheet("Sheet1");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getRowCount(String sheet) {
		sh = wb.getSheet(sheet);
		int totalRows = sh.getLastRowNum() +1 ;
		return totalRows;
	}
	public int getColumnCount(String sheet) {
		sh = wb.getSheet(sheet);
		row = sh.getRow(1);
		int count = row.getLastCellNum();
		return count;
	}
	public String getCellData(String sheet, int rowNum, int columnNum ) {
		sh = wb.getSheet(sheet);
		row = sh.getRow(rowNum);
		cell = row.getCell(columnNum);
		String cellValue = cell.getStringCellValue();
		return cellValue;
	}
}