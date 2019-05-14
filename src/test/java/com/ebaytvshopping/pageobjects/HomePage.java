package com.ebaytvshopping.pageobjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	public HomePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/home")
	@CacheLookup
	AndroidElement menuButton;

	public void clickMenu() {
		menuButton.click();
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/search_box")
	@CacheLookup
	AndroidElement tapSearch;

	public void tapSearchBox() {
		tapSearch.click();
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/search_src_text")
	@CacheLookup
	AndroidElement searchTxtBox;

	public void enterSearchKeyword(String keyword) {
		searchTxtBox.sendKeys(keyword);
	}
}