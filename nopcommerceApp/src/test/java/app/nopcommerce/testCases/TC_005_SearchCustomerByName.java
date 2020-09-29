package app.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import app.nopcommerce.pageObjects.AddcustomerPage;
import app.nopcommerce.pageObjects.LoginPage;
import app.nopcommerce.pageObjects.SearchCustomerPage;
import app.nopcommerce.testBase.BaseClass;


public class TC_005_SearchCustomerByName extends BaseClass {

	@Test
	public void TC_005_searchCustomerbyName() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_SearchCustomerByName_005 *************");
		
		driver.get(baseURL);
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(useremail);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//Go to search page
		AddcustomerPage addcust=new AddcustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		
		//Name
		SearchCustomerPage searchcust=new SearchCustomerPage(driver);
		searchcust.setFirstName("Victoria");
		searchcust.setLastName("Terces");
		searchcust.clickSearch();
		Thread.sleep(3000);
		
		boolean status=searchcust.searchCustomerByName("VictoriaTerces");
		if(status == false)
		{
			logger.info("********* Search customer by name is failed*************");
			captureScreen(driver,"TC_005_searchCustomerbyName");
			driver.close();
			Assert.assertTrue(false);
		}
		else
		{
			logger.info("********* Search customer by name is passed *************");
			Assert.assertEquals(true, status);
		}
		
		
		logger.info("********* End of TC_SearchCustomerByName_005 *************");
	}
	
}
