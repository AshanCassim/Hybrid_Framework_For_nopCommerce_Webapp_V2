package app.nopcommerce.testBase;

import java.io.File;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import app.nopcommerce.Utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass {
	
	
	public WebDriver driver;
	
	public Logger logger = LogManager.getLogger(this.getClass());    // Log4j2
	
	ReadConfig configObj = new ReadConfig();
	
	public String baseURL = configObj.getAppURL();
	
	public String useremail = configObj.getuseremail();
	public String pwd = configObj.getpassword();
	
	
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws IOException, InterruptedException
	{
		if(br.equals("chrome"))
		{
			logger.info("********* starting TC on chrome*************");
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
				
		}
		else if(br.equals("firefox"))
		{
			logger.info("********* starting TC on firefox*************");
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			logger.info("********* starting TC on ie*************");
			//WebDriverManager.iedriver().setup();//Not working
			System.setProperty("webdriver.ie.driver","./Drivers/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			Thread.sleep(3000);
		}
		else if(br.equals("edge"))
		{
			logger.info("********* starting TC on edge*************");
			WebDriverManager.edgedriver().setup(); //Not working
			//System.setProperty("webdriver.edge.driver","./Drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\Screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	
	public String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
}

