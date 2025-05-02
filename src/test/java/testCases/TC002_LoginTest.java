package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	
	@Test(groups = {"Sanity", "Master"})
	public void verify_loginTest()
	{
		logger.info(" ** Starting Login Test ** ");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickLnkMyAccount();
		hp.clicLnkLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPass(p.getProperty("password"));
		lp.setLoginBtn();
		
		//MyAccount Page
		
		MyAccountPage myp = new MyAccountPage(driver);
		boolean targetPage = myp.isMyAccountPageExists();
		Assert.assertTrue(targetPage);
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info(" ** Finish Login Test ** ");

		
	}

}
