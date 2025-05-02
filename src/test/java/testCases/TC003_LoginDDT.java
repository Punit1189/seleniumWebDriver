package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginData" ,dataProviderClass = DataProviders.class, groups = "DataDriven")
	
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		
		logger.info(" **** Starting TC003_LoginDDT *** ");
		//Home Page
		try
		{
			
		
		HomePage hp = new HomePage(driver);
		hp.clickLnkMyAccount();
		hp.clicLnkLogin();
		
		//LoginPage
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPass(pwd);
		lp.setLoginBtn();
		//Thread.sleep(3000);
		
		//MyAccountPage
		
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageExists();
		//Thread.sleep(2000);
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage == true)
			{
				map.clickLogOut();
				Assert.assertTrue(true);
				
			}
			
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage == true)
			{
				map.clickLogOut();
				Assert.assertTrue(false);
				
			}
			
			else
			{
				Assert.assertTrue(true);
			}
			
		}	
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info(" **** Finishing TC003_LoginDDT *** ");
		
   }

}
