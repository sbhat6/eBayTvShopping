package com.ebaytvshopping.pageobjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//This is a Page-Object class for app 'Menu' page
public class MenuPage {
	public MenuPage(AndroidDriver<AndroidElement> driver) {		//**Java OOP concept 'Constructors' is used here**
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/logo") // Element locator for menu sign-in option
	@CacheLookup
	AndroidElement signinButton;

	public void clickSigninText() { // Method to click sign-in option
		signinButton.click();
	}
}
