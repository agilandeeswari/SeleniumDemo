package util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import pages.BasePage;

public class ExtentRepotsgen extends BasePage implements ITestListener,ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		report = new ExtentReports("./report/"+suite.getName()+"_"+System.currentTimeMillis()+"_Report.html");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger = report.startTest(result.getMethod().getMethodName());
		logger.log(LogStatus.INFO, result.getMethod().getMethodName()+"Started");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.log(LogStatus.PASS, "Test Executed sucessfully");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		logger.log(LogStatus.FAIL, result.getThrowable());
		String filename="./screenshot/"+result.getMethod().getMethodName()+"_"+result.getEndMillis()+".png";
		TakesScreenshot screen = (TakesScreenshot) driver;
		File srcFile=screen.getScreenshotAs(OutputType.FILE);
		File desFile=new File(filename);
		try
		{
			FileUtils.copyFile(srcFile, desFile);
		}
		catch(Exception e)
		{
			System.out.print("e.getMessage"+e.getMessage());
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.log(LogStatus.SKIP, "Test Case Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
