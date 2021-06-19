package com.quintype.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	protected Workbook workbook;

	public ExcelReader(String filepath)
	{
		InputStream inputStream=null;

		try {
			checkFileExists(filepath);
			inputStream = new FileInputStream(filepath);
			if(filepath.toLowerCase().endsWith(".xlsx"))
			{
				workbook = new XSSFWorkbook(inputStream);
				inputStream.close();
			}
			else if(filepath.toLowerCase().endsWith(".xls"))
			{
				workbook = new HSSFWorkbook(inputStream);
				inputStream.close();
			} else {
				throw new Exception(filepath+" does not have valid file format.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean checkFileExists(String strFileName) throws Exception
	{
		if (!(new File(strFileName).exists()))
		{
			throw new Exception("File " + strFileName + " does not exist");
		}

		return true;
	}
	public int getRowCount(String sheetName) throws Exception  {
		int sheetIndex = workbook.getSheetIndex(sheetName);
		if(sheetIndex == -1) {
			throw new Exception("Sheet name-"+sheetName+" does not exist.");
		}
		int rowCount=workbook.getSheetAt(sheetIndex).getLastRowNum()+1;

		return rowCount;
	}


	public int getLastCellNum(String sheetName,int rowNum) throws Exception {
		if(sheetName==null || sheetName.isEmpty())
			throw new Exception("Sheet name:"+sheetName+" does not exist.");

		Sheet sheet = workbook.getSheet(sheetName);
		if(sheet==null)
			throw new Exception("Sheet name:"+sheetName+" does not exist.");
		Row row= sheet.getRow(rowNum-1);
		if(row==null)
			throw new Exception("Row no:"+rowNum+" is an empty row in Sheet name:"+sheetName);
		int lastCellNum= row.getLastCellNum();

		return lastCellNum;
	}



	public String getCellValue(String sheetName,int rowNum,int colNum) {
		String cellValue="";
		Sheet sheet = workbook.getSheet(sheetName);
		if(sheet==null)
			return "";

		Row row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		Cell cell= row.getCell(colNum-1);
		if(cell==null)
			return "";

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cellValue = Double.toString(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			cellValue = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			Boolean.toString(cell.getBooleanCellValue());
			break;
		default:
			break;
		}

		return cellValue;
	}
}
