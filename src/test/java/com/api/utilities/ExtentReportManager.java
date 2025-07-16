package com.api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ExtentTest extentTest;
    private static String reportName;

 
    public void onStart(ITestContext testContext) {
            String currentDateTimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            reportName = "Test-Report" + currentDateTimeStamp + ".html";
            sparkReporter = new ExtentSparkReporter(".\\test-reports\\" + reportName);

            sparkReporter.config().setDocumentTitle("Api Automation Framework Reports");
            sparkReporter.config().setReportName("Api Testing - JsonPlaceHolder");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Application", "JsonPlaceHolder Api's");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            extent.setSystemInfo("Username", System.getProperty("user.name"));
    }
    
    
    public void onFinish(ITestContext testContext) {
        if (extent != null) {
            extent.flush();
        }
    }

    
    public void onTestSuccess(ITestResult result) {
        extentTest = extent.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.PASS, "Test passed..");
    }

    
    public void onTestFailure(ITestResult result) {
    		extentTest = extent.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.FAIL, "Test failed..");
        extentTest.log(Status.FAIL, result.getThrowable().getMessage());
    }

    
    public void onTestSkipped(ITestResult result) {
    		extentTest = extent.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.SKIP, "Test skipped..");
        extentTest.log(Status.SKIP, result.getThrowable().getMessage());
    }
    
}
