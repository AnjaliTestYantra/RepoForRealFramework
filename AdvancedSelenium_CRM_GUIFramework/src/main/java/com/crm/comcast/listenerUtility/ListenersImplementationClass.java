package com.crm.comcast.listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.crm.generic.baseUtility.BaseClass;

public class ListenersImplementationClass implements ITestListener,ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report generation");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
	}

	@Override
	public void onTestStart(ITestResult result) {
		String method= result.getMethod().getMethodName();
		System.out.println("===STRT "+method+"===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String method= result.getMethod().getMethodName();
		System.out.println("===END "+method+"===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String method= result.getMethod().getMethodName();
		 
		String sysTime = new Date().toString().replace(" ", "_").replace(":", "_");
		TakesScreenshot tks= (TakesScreenshot) BaseClass.sDriver;
		File src = tks.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("./errorShots/"+method+"_"+sysTime+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String method= result.getMethod().getMethodName();
		System.out.println("===SKIP "+method+"===");
	}
	

}
