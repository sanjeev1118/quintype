package com.quintype.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class TestUtil { 


	public static String getScreenshot(WebDriver driver, String caseID)  {
		SimpleDateFormat formatter = new SimpleDateFormat("EEE_dd_MM_yyyy_hh_mm_ss_a");
		String filepath = ".\\.\\test-output\\Screenshots\\"+"\\TC_"+caseID+"_"+formatter.format(new Date())+".png";
		try {
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File(filepath);
			FileUtils.copyFile(srcFile, destFile);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return filepath;
	}

	public static String[][] getData(ExcelReader excel,String sheetName,String testName) throws Exception {
		String[][] data;
		int testStartRow=1000;
		int testDataStartRow=0; 
		
		for(int i=1; i<=excel.getRowCount(sheetName); i++) {
			if(excel.getCellValue(sheetName, i, 1).equals(testName)) {	
				testStartRow=i;
				break;
			}
		}
		testDataStartRow =testStartRow+2;
		int temp=testDataStartRow;
		int noOfRows=0;
		
		while(!excel.getCellValue(sheetName, temp, 1).equals("")) {
			temp++;
			noOfRows++;
		}
		int testEndRow = testDataStartRow+noOfRows-1;
		int noOfCols= excel.getLastCellNum(sheetName, testStartRow+1);
		data = new String[noOfRows][noOfCols];
		int rowInex=0;
		
		for(int i=testDataStartRow; i<=testEndRow; i++,rowInex++) {
			int colIndex=0;
			for(int j=1; j<=noOfCols; j++,colIndex++) {
				data[rowInex][colIndex] = excel.getCellValue(sheetName, i, j);
				System.out.print(data[rowInex][colIndex]+" ");
			}
			System.out.println();
		}

		return data;
	}
}
