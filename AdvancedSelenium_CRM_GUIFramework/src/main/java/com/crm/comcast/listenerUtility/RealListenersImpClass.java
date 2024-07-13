package com.crm.comcast.listenerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.baseUtility.BaseClass;

import genericUtility.UtilityClassObject;

public class RealListenersImpClass implements ITestListener,ISuiteListener{
 public  ExtentReports report;
 public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report generation");
//		test.log(Status.INFO, "Report generation");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark= new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("VTIGER_CRM");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows10");
		report.setSystemInfo("Browser", "Chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
//		test.log(Status.INFO, "Report backup");
		report.flush();
		}

	@Override
	public void onTestStart(ITestResult result) {
		String method= result.getMethod().getMethodName();
		test= report.createTest(method);
		//UtilityClassObject.setTest(test);
		System.out.println("===STRT "+method+"===");
//		test.log(Status.INFO, "===START "+method+"===");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String method= result.getMethod().getMethodName();
		System.out.println("===END "+method+"===");
		test.log(Status.INFO, "===END "+method+"===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String method= result.getMethod().getMethodName();
		test= report.createTest(method);
		
		TakesScreenshot tks= (TakesScreenshot) BaseClass.sDriver;
		 String src = tks.getScreenshotAs(OutputType.BASE64);
		 
		 String sysTime = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(src, method+"_"+sysTime);
		test.log(Status.INFO, "===FAILED "+method+"===");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String method= result.getMethod().getMethodName();
		System.out.println("===SKIP "+method+"===");
//		test.log(Status.INFO, "===SKIP "+method+"===");
	}
	

}
