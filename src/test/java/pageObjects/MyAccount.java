package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage{

	public MyAccount(WebDriver driver) {
		super(driver);
			}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgHeading; // My Account Page Heading
	
	public boolean isMyAccountPageExists() {
		try {
		return msgHeading.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnkLogout; // added in step 6
	
	public void clickLogout() {
		lnkLogout.click();
	}


}
