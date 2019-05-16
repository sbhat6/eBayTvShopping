package com.ebaytvshopping.pageobjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//This is a Page-Object class for app 'Home' page
public class HomePage {
	public HomePage(AndroidDriver<AndroidElement> driver) {		//**Java OOP concept 'Constructors' is used here**
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/home") // Element locator for menu button
	@CacheLookup
	AndroidElement menuButton;

	public void clickMenu() { // Method to click menu button
		menuButton.click();
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/search_box") // Element locator for search box
	@CacheLookup
	AndroidElement tapSearch;

	public void tapSearchBox() { // Method to click search box
		tapSearch.click();
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/search_src_text") // Element locator for search text box
	@CacheLookup
	AndroidElement searchTxtBox;

	public void enterSearchKeyword(String keyword) { // Method to enter search keyword
		searchTxtBox.sendKeys(keyword);
	}
}