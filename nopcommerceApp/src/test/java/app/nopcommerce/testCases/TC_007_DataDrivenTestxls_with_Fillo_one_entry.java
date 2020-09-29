package app.nopcommerce.testCases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import app.nopcommerce.pageObjects.LoginPage;
import app.nopcommerce.testBase.BaseClass;



@Test
public class TC_007_DataDrivenTestxls_with_Fillo_one_entry extends BaseClass {
	
		
	ArrayList<String> row= new ArrayList<String>();
	ArrayList<String> loginValues=new ArrayList<String>();
	ArrayList<String> data= new ArrayList<String>();
	
	public ArrayList<String> getData() throws com.codoid.products.exception.FilloException{
		
		
		
		Fillo f = new Fillo();
		
		Connection connection = f.getConnection("TestData/LoginData.xlsx");
		
		String strQuery = "Select * from Sheet1";
		
		Recordset recordset=connection.executeQuery(strQuery); // Execute Query and store result in Recordset
    
		while(recordset.next()){  // Condition till record set has values
        
			row=recordset.getFieldNames(); // get column names
          
			for (int i=0;i<=row.size()-1;i++) {  // run for loop for desired column values
                loginValues.add(recordset.getField(row.get(i)));  // add row values to ArrayList
            }
        } 
		recordset.close();
		connection.close();
		return loginValues;
    }
	
	
	public void TC_007_DataDrivenLogin_loguot_with_Fillo_one_entry() throws Exception {
					
		logger.info("********* Starting TC_007_DataDrivenTestxls_with_Fillo_one_entry *************");
		
		logger.info("********* Providing login to application *************");
		
		driver.get(baseURL);
			
		LoginPage lp=new LoginPage(driver);
	
		logger.info("********* Providing login details to application *************");
		
		data = getData();
		lp.setUserName(data.get(0));
		lp.setPassword(data.get(1));
			
		lp.clickLogin();
		Thread.sleep(7000);
		
		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		
		
		
		if(exp_title.equals(act_title))
		{
			if(data.get(2).equals("Pass"))
			{
				logger.info("**************** loginTest is Passed ************* ");
				lp.clickLogout();
				Thread.sleep(3000);
				Assert.assertTrue(true);
			}
			else if(data.get(2).equals("Fail"))
			{
				logger.warn("**************** loginTest is Failed************* ");
				lp.clickLogout();
				Thread.sleep(3000);
				Assert.assertTrue(false);
			}
					
		}
		else if(!exp_title.equals(act_title))
		{
			if(data.get(2).equals("Pass"))
			{
				logger.warn("**************** loginTest is Failed************* ");
				Assert.assertTrue(false);
			}
			else if(data.get(2).equals("Fail"))
			{
				logger.info("**************** loginTest is Passed ************* ");
				Assert.assertTrue(true);
			}

		}
		
		logger.info("********* Finished  TC_007_DataDrivenTestxls_with_Fillo_one_entry *************");
			
		
	}
		

			
	
}