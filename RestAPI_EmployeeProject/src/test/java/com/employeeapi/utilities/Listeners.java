package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter
{		
	  ExtentHtmlReporter htmlReporter;
	  ExtentReports extent;
	  ExtentTest test;
		
	  @Override
	  public void onStart(ITestContext testContext) {
		  htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/customReports/customReports.html");
		  htmlReporter.config().setDocumentTitle("Automation report");
		  htmlReporter.config().setReportName("API Automation report");
		  htmlReporter.config().setTheme(Theme.DARK);		
		  
		  extent= new ExtentReports();
		  extent.attachReporter(htmlReporter);
		  extent.setSystemInfo("user", "Raghu");
		  extent.setSystemInfo("Hostname", "localhost");
		  extent.setSystemInfo("Environment", "QA");
		 
	  }
	  
	@Override
	  public void onTestSuccess(ITestResult tr) {
	  test = extent.createTest(tr.getName());
	  test.log(Status.PASS, "Testcase "+tr.getName()+" is passed");
	  }

	  @Override
	  public void onTestFailure(ITestResult tr) {
		  test = extent.createTest(tr.getName());
		  test.log(Status.FAIL, "Testcase "+tr.getName()+" is failed");
	  }

	  @Override
	  public void onTestSkipped(ITestResult tr) {
		  test = extent.createTest(tr.getName());
		  test.log(Status.SKIP, "Testcase "+tr.getName()+" is Skipped");	
	  }
	 
	  @Override
	  public void onFinish(ITestContext testContext) {
		  extent.flush();
	  }

	

}
