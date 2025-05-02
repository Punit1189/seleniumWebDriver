package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups = {"Sanity","Regression", "Master"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException, InterruptedException
	{
		// Loading file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No Matching Os");
				return;
			}
			
			//browser
			
			switch(br.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("microsoftEdge");break;
			case "firefox": capabilities.setBrowserName("firefox");break;
			default: System.out.println(" Invalid Browser Name "); return;		
			
			
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
					
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(br.toLowerCase())
		{
		case "chrome": driver = new ChromeDriver();break;
		case "edge": driver = new EdgeDriver();break;
		case "firefox": driver = new FirefoxDriver();break;
		default: System.out.println(" Invalid Browser Name "); return;
		
		}
		}
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appurl1"));
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
	
	@AfterClass(groups = {"Sanity","Regression", "Master"})
	public void tearDown()
	{
		driver.close();
	}
	
	public String randomeString()
    {
     	String genratedString = RandomStringUtils.randomAlphabetic(5);
     	return genratedString;
     	
     	
    }
	 
	public String randomeNumber()
    {
     	String genratedString = RandomStringUtils.randomNumeric(10);
     	return genratedString;     	
     	
    }
	 
	public String randomeAlNumber()
    {
     	String genratedString = RandomStringUtils.randomAlphanumeric(10);
     	return genratedString;     	
     	
    }
	
	

	public String captureScreen(String tname) 
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot  takesScreenshot = (TakesScreenshot) driver;
		
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
