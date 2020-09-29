package app.nopcommerce.Utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{ 
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	
	public void onStart(ITestContext testContext)
	{		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		
		htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/"+repName);//specify location of the report
				
		htmlReporter.config().setDocumentTitle("nopCommerce Automation Report"); // Tile of report
		htmlReporter.config().setReportName("nopCommerce  Functional Testing"); // name of the report
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user","Ashan");
			
	}
	
	public void onTestSuccess(ITestResult result)
	{
		parentTest=extent.createTest(result.getName()); // create new entry in the report
		parentTest.log(Status.PASS, "Test Case PASSED " + result.getName());
	}
	
	public void onTestFailure(ITestResult result)
	{
		parentTest=extent.createTest(result.getName()); // create new entry in the report
		
		parentTest.log(Status.FAIL, "TEST CASE FAILED " + result.getName()); // to add name in extent report
		parentTest.log(Status.FAIL, "TEST CASE FAILED  " + result.getThrowable()); // to add error/exception in extent report

		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+result.getName()+".png";
		try {
			parentTest.addScreenCaptureFromPath(screenshotPath);// adding screen shot	
		} catch (IOException e) {
				e.printStackTrace();
		} 
	}
	
	public void onTestSkipped(ITestResult result)
	{
		parentTest=extent.createTest(result.getName()); // create new entry in the report
		parentTest.log(Status.SKIP, "Test Case SKIPPED  " + result.getName());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	
	
}
