package app.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import app.nopcommerce.pageObjects.LoginPage;
import app.nopcommerce.testBase.BaseClass;


public class TC_001_LoginTest extends BaseClass
	{
	
	@Test
	public void TC_001_Login_Test() throws IOException, InterruptedException
	{
		
		logger.info("********* starting TC_LoginTest_001 *************");
		driver.get(baseURL);
		
		
		LoginPage lp=new LoginPage(driver);
	
		logger.info("********* Providing login details to application *************");
		
		lp.setUserName(useremail);
		lp.setPassword(pwd);
		
		logger.info("*********Clicking on the login button*************");
		lp.clickLogin();
		Thread.sleep(3000);
		
			
		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		
		logger.info("********* Login validation starting..*************");
		
		if(exp_title.equals(act_title))
		{
			logger.info("*********Login Test passed*************");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("*********Login Test failed*************");
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
		}
		
	}
	
}
