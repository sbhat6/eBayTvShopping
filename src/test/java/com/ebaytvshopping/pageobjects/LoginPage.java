package com.ebaytvshopping.pageobjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {
	public LoginPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Email or username']")
	@CacheLookup
	AndroidElement unameTxtBox;

	public void enterUsername(String uname) {
		unameTxtBox.sendKeys(uname);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Password']")
	@CacheLookup
	AndroidElement pwordTxtBox;

	public void enterPassword(String pword) {
		pwordTxtBox.sendKeys(pword);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/button_sign_in")
	@CacheLookup
	AndroidElement buttonSignin;

	public void clickSigninButton() {
		buttonSignin.click();
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/button_google_deny")
	@CacheLookup
	AndroidElement denyButton;

	public void clickDenyButton() {
		denyButton.click();
	}
}
