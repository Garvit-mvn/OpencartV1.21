package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegTest extends BaseClass {
	
	
	
	@Test(groups = {"Regression", "Master"})
	public void verify_account_registration() {
		
	logger.info("*******Starting TC001_AccountRegTest*******");
	try {	
	HomePage hp = new HomePage(driver);
	hp.clickMyaccount();
	logger.info("Clicked on My Account link");
	
	hp.clickRegister();
	logger.info("Clicked on My Register link");
	
	AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
	
	logger.info("Providing customer details");
	regpage.setFirstName(randomString().toUpperCase());
	regpage.setLastName(randomString().toUpperCase());
	regpage.setEmail(randomString() + "@gmail.com");
	regpage.setTelephone(randomNum());
	
	String Password = randomAlphaNum();
	regpage.setPassword(Password);
	regpage.setConfirmPassword(Password);
	
	regpage.chkPolicy();
	regpage.clickContinue();
	
	logger.info("Validating Expected msg");
	String ConfMsg = regpage.getConfirmationMsg();
	if(ConfMsg.equals("Your Account Has Been Created!")) {
		Assert.assertTrue(true);
	}
	else {
		logger.error("Test Failed");
		logger.debug("Debug Logs");
		Assert.assertTrue(false);
	}
//	Assert.assertEquals(ConfMsg, "Your Account Has Been Created!");
	
	}catch(Exception e) {
		logger.error("Test Failed");
		logger.debug("Debug Logs"); //generate debug logs
		Assert.fail();

	}
	logger.info("*********Finished*********");

	
	}
	
	

}
