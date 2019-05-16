package com.ebaytvshopping.pageobjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//This is a Page-Object class for 'Log-in' page
public class LoginPage {
	public LoginPage(AndroidDriver<AndroidElement> driver) {		//**Java OOP concept 'Constructors' is used here**
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Email or username']") // Element locator for user-name text box.
	@CacheLookup
	AndroidElement unameTxtBox;

	public void enterUsername(String uname) { // Method to enter user-name.
		unameTxtBox.sendKeys(uname);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Password']") // Element locator for password text box.
	@CacheLookup
	AndroidElement pwordTxtBox;

	public void enterPassword(String pword) { // Method to enter password
		pwordTxtBox.sendKeys(pword);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/button_sign_in") // Element locator for sign-in button.
	@CacheLookup
	AndroidElement buttonSignin;

	public void clickSigninButton() { // Method to click sign-in button.
		buttonSignin.click();
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/button_google_deny") // Element locator to skip a recommendation.
	@CacheLookup
	AndroidElement denyButton;

	public void clickDenyButton() { // Method to click deny button.
		denyButton.click();
	}
}
