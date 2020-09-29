package app.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import app.nopcommerce.pageObjects.AddcustomerPage;
import app.nopcommerce.pageObjects.LoginPage;
import app.nopcommerce.testBase.BaseClass;


public class TC_003_AddCustomerTest extends BaseClass{

	@Test
	public void TC_003_addNewCustomer() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_AddCustomerTest_003 *************");
		
		driver.get(baseURL);
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(useremail);
		lp.setPassword(pwd);
		lp.clickLogin();
		Thread.sleep(3000);
		
		logger.info("*********Adding new customer *************");
		
		AddcustomerPage addcust=new AddcustomerPage(driver);
		
		addcust.clickOnCustomersMenu();
		addcust.clickOnCustomersMenuItem();
		addcust.clickOnAddnew();
		Thread.sleep(2000);

		logger.info("***************  Providing customer details  *********** ");

		String email=randomestring()+"@gmail.com";
		addcust.setEmail(email);
		addcust.setPassword("test123");
		addcust.setFirstName("Ashan");
		addcust.setLastName("Shan");
		addcust.setGender("Male");
		addcust.setDob("8/08/1994"); // Format: MM/DD/YYY
		addcust.setCompanyName("LTRL");
		addcust.setCustomerRoles("Vendors");
		Thread.sleep(3000);
		addcust.setManagerOfVendor("Vendor 2");
		addcust.setAdminContent("This is for testing.........");
		addcust.clickOnSave();
		Thread.sleep(3000);

		// validation
				if (addcust.verifyConfirmationMsg()) {
					logger.info("***************  Customer added succesfully *********** ");
					Assert.assertTrue(true);

				} else {
					logger.error("*************** Customer Not added succesfully *********** ");
					captureScreen(driver,"addNewCustomer");
					Assert.assertTrue(false);
				}
				logger.info("***************   TC_AddCustomerTest_003 Finished  *********** ");
	}
	
}
