package app.nopcommerce.testCases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;


import app.nopcommerce.pageObjects.LoginPage;
import app.nopcommerce.testBase.BaseClass;


public class TC_008_DataDrivenTest_Login_with_Fillo_Data_Provider extends BaseClass 
{
	
	
	@Test(dataProvider="LoginData")
	public void TC_008_dataDrivenTest_Login_with_Fillo_Data_Provider(String user,String pwd,String exp) throws InterruptedException
	{
		
		
		driver.get(baseURL);
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickLogin();
		Thread.sleep(5000);
		
		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		
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
		logger.info("********* Finished  TC_008_DataDrivenTest_Login_with_Fillo_Data_Provider *************");
	}
	
	
		
	@DataProvider(name="LoginData")	
	public String [][] getData() throws IOException, FilloException
	{
		logger.info("********* starting TC_008_DataDrivenTest_Login_with_Fillo_Data_Provider *************");
		
		Fillo f = new Fillo();
		
		Connection connection = f.getConnection("TestData/LoginData.xlsx");
		
		String strQuery = "Select * from Sheet1";
		
		Recordset recordset=connection.executeQuery(strQuery); // Execute Query and store result in Recordset
    
		String logindata [][] = new String[recordset.getCount()][3];
		
		int i =0;
		
		while(recordset.next())
		{  // Condition till record set has values
			
			System.out.println(i);
			logger.info("********* Providing login details to application *************");
			
			logindata[i][0] = recordset.getField("username");
			logindata[i][1] = recordset.getField("password");
			logindata[i][2] = recordset.getField("exp");
			
			i++;
			
		}	
			
		return logindata;		
	}

			
}

		
		
			
		
	
	
	
	
	


