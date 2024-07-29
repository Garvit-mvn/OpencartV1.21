package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

@FindBy(xpath="//input[@id='input-firstname']") WebElement txtfirstName;
@FindBy(xpath="//input[@id='input-lastname']") WebElement txtlastName;
@FindBy(xpath="//input[@id='input-email']") WebElement txtemail;
@FindBy(xpath="//input[@id='input-telephone']") WebElement txttelephone;
@FindBy(xpath="//input[@id='input-password']") WebElement txtpassword;
@FindBy(xpath="//input[@id='input-confirm']") WebElement txtpasswordConfirm;
@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
@FindBy(xpath="//input[@name='agree']") WebElement chkPolicy;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;


public void setFirstName(String firstname) {
	txtfirstName.sendKeys(firstname);
}

public void setLastName(String lastname) {
	txtlastName.sendKeys(lastname);
}

public void setEmail(String email) {
	txtemail.sendKeys(email);
}

public void setTelephone(String num) {
	txttelephone.sendKeys(num);
}

public void setPassword(String pwd) {
	txtpassword.sendKeys(pwd);
}

public void setConfirmPassword(String ConfimPwd) {
	txtpasswordConfirm.sendKeys(ConfimPwd);
}

public void chkPolicy() {
	chkPolicy.click();
}

public void clickContinue() {
	btnContinue.click();
}

public String getConfirmationMsg() {
	try {
	return msgConfirmation.getText();
	} catch(Exception e) {
		return(e.getMessage());
	}
}



}
