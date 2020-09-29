package app.nopcommerce.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	
	Properties configPropObj;
	
	public ReadConfig() 
	{
		// Load config.properties file
		
		File file = new File("./Configuration/config.properties");
		
		try {
			configPropObj = new Properties();
			
			FileInputStream configfile = new FileInputStream(file);
			configPropObj.load(configfile);
			// end of loading gconfig.properties file
			
		} catch (IOException e) {
			
			System.out.println("Exception is: " + e.getMessage());
		}
		
	}
	
	public String getAppURL() 
	{
		String url = configPropObj.getProperty("baseURL");
		return url;
		
	}
	
	public String getuseremail() 
	{
		String email = configPropObj.getProperty("useremail");
		return email;
		
	}
	
	public String getpassword() 
	{
		String passwrd = configPropObj.getProperty("password");
		return passwrd;
		
	}
}
	
	


