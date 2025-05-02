package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	
	
	
	@Test(groups = {"Regression", "Master"})
	public void verify_Account_Registration() throws InterruptedException
	{
		logger.info(" Staring of TC001_AccountRegistrationTest ");
		
		try
		{
		HomePage hp = new HomePage(driver);
		logger.info(" Clicking on My Account");
		hp.clickLnkMyAccount();
        Thread.sleep(3000);
        hp.clickLnkRegister();
		
        
        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
        logger.info(" Entering Details on register page ");
        
        regpage.setFirstName(randomeString());
        regpage.setLastName(randomeString());
        regpage.setEmail(randomeString() + "@gmail.com");
        regpage.setTelePhone(randomeNumber());  
        
        String password = randomeAlNumber();
        regpage.setPassword(password);
        regpage.setConfirmPassword(password);
        regpage.setPrivacyPolicy();
        regpage.clickContinuey();
        
        String confrms = regpage.getConfirmationMessage();
        if(confrms.equals("Your Account Has Been Created!"))
        {
        	logger.info(" Verify the message ");
        }
        else
        {
        	logger.error("This is Error");
			logger.debug("Debug log");
			Assert.assertTrue(false);
        	
        }
        //Assert.assertEquals(confrms, "Your Account Has Been Created!!");
        
		}
		catch(Exception e)
		{
			
			Assert.fail();
			
		}
		logger.info(" Finishing of TC001_AccountRegistrationTest ");
	}
	

}
