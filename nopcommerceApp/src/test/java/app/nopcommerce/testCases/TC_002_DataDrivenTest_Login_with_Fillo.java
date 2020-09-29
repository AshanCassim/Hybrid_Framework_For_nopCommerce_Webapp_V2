package app.nopcommerce.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import app.nopcommerce.pageObjects.LoginPage;
import app.nopcommerce.testBase.BaseClass;


public class TC_002_DataDrivenTest_Login_with_Fillo extends BaseClass {
	
	@Test
	public void TC_002_DataDrivenLogin_loguot_with_Fillo() throws Exception {
		
		logger.info("********* starting TC_002_DataDrivenTest_Login_with_Fillo *************");
		
		driver.get(baseURL);
		
		Fillo f = new Fillo();
		
		Connection connection = f.getConnection("TestData/LoginData.xlsx");
		
		String strQuery = "Select * from Sheet1";
		
		Recordset recordset=connection.executeQuery(strQuery); // Execute Query and store result in Recordset
    
		
		
		int i =1;
		
		while(recordset.next()){  // Condition till record set has values
			
			LoginPage lp=new LoginPage(driver);
	
			
			System.out.println(i);
			logger.info("********* Providing login details to application *************");
			
			
			
			lp.setUserName(recordset.getField("username"));
			lp.setPassword(recordset.getField("password"));
				
			lp.clickLogin();
			Thread.sleep(7000);
			
			String exp_title="Dashboard / nopCommerce administration";
			String act_title=driver.getTitle();
			
			String exp =recordset.getField("exp");
			
			if(exp_title.equals(act_title))
			{
				if(exp.equals("Pass"))
				{
					logger.info("**************** loginTest is Passed ************* ");
					lp.clickLogout();
					Thread.sleep(3000);
					Assert.assertTrue(true);
				}
				else if(exp.equals("Fail"))
				{
					logger.warn("**************** loginTest is Failed************* ");
					lp.clickLogout();
					Thread.sleep(3000);
					Assert.assertTrue(false);
				}
						
			}
			else if(!exp_title.equals(act_title))
			{
				if(exp.equals("Pass"))
				{
					logger.warn("**************** loginTest is Failed************* ");
					Assert.assertTrue(false);
				}
				else if(exp.equals("Fail"))
				{
					logger.info("**************** loginTest is Passed ************* ");
					Assert.assertTrue(true);
				}

			}
			i++;
		}
			
			logger.info("********* Finished  TC_002_DataDrivenTest_Login_with_Fillo *************");
			recordset.close();
			connection.close();	
			
	}
			
         
		
		
		
}

		
		
			
		
	
	
	
	
	


