package com.ebaytvshopping.pageobjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MenuPage {
	public MenuPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/logo")
	@CacheLookup
	AndroidElement signinButton;

	public void clickSigninText() {
		signinButton.click();
	}
}
