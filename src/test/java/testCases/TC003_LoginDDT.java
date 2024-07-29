package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")
	public void verify_LoginDDT(String email, String pwd, String exp) {
		
		logger.info("*********Starting TC003_LoginDDT********");
		try {
		//Homepage
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccount Page
		MyAccount macc = new MyAccount(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		

		/*Data is valid  - login success - test pass  - logout
		Data is valid -- login failed - test fail
		
		Data is invalid - login success - test fail  - logout
		Data is invalid -- login failed - test pass
		*/
		
		if(exp.equalsIgnoreCase("Valid")) {
			if(targetPage == true) {
				macc.clickLogout();
				Assert.assertTrue(true);			
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		else {
			if(targetPage == true) {
				macc.clickLogout();
				Assert.assertTrue(false);
				
			}
			else {
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("*********Finished TC003_LoginDDT********");
	}
}
