package com.quintype.utils;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.quintype.base.Base;

public class TestListener implements ITestListener {
	
	private String caseID = "01";
	private String successMsg;
	private String failedMsg;

	public void onTestStart(ITestResult result) {
		try {
			Object[] parameters = result.getParameters();
			caseID = parameters[0].toString();
			successMsg = parameters[parameters.length-1].toString();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println(caseID + " Passed");
		
	}

	public void onTestFailure(ITestResult result) {
		System.out.println(caseID + " Failed");
		failedMsg = result.getThrowable().getMessage();
		//String screenshotFile = TestUtil.getScreenshot(Base.driver, caseID);
		//Reporter.log("<h2>Screenshot:-</h2>\n<br><img src='"+new File(screenshotFile).getAbsolutePath().toString()+"' style=\"width:1080px;height:720px;\" /></br>");
		if(result.getThrowable() !=null)
			result.getThrowable().printStackTrace();
	}

	public void onTestSkipped(ITestResult result) {
		failedMsg = result.getThrowable().getMessage();
		System.out.println("Test Case Id:-"+caseID+" Skipped......");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		System.out.println(context.getCurrentXmlTest().getName()+" Test execution started.....");
	}

	public void onFinish(ITestContext context) {
		System.out.println(context.getCurrentXmlTest().getName()+" Test execution finished.....");
	}

}
