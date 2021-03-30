package com.learnautomation.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.beust.jcommander.Parameter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUp()
	{
	 excel = new ExcelDataProvider();
	 config =new ConfigDataProvider();
	 
	 ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/FreeCRM"+Helper.getCurrentDateTime()+"+.html"));
	 report = new ExtentReports();
	 report.attachReporter(extent);
	}
	
	@Parameters({"browser","urlToBeTested"})
	@BeforeClass
	public void setup(String browser, String url)
	{
		//driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingUrl());
		
		driver = BrowserFactory.startApplication(driver,browser, url);
	}
	
	@AfterClass
	public void teardown()
	{
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreesnshot(driver)).build());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreesnshot(driver)).build());
		}
		else if (result.getStatus()==ITestResult.SKIP)
		{
			logger.skip("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreesnshot(driver)).build());
		}
		
		report.flush();
		
	}
}
