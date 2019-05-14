package com.ebaytvshopping.pageobjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ItemPage {
	public ItemPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_name']")
	@CacheLookup
	AndroidElement itemTitle;

	public String getItemTitle() {
		String itemName = itemTitle.getText();
		return itemName;
	}

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.ebay.mobile:id/textview_item_price\")")
	@CacheLookup
	AndroidElement itemValue;

	public float getItemPrice() {
		String a = itemValue.getText();
		String b = a.substring(4);
		String e = b.replaceAll(",", "");
		float itemPrice = Float.parseFloat(e);
		return itemPrice;
	}

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.ebay.mobile:id/shipping_textview\")")
	@CacheLookup
	AndroidElement itemPostageValue;

	public float getPostagePrice() {
		String c = itemPostageValue.getText();
		if (c.contains("Free")) {
			float itemPostagePrice = 0;
			return itemPostagePrice;
		} else {
			String d = c.substring(6, 10);
			float itemPostagePrice = Float.parseFloat(d);
			return itemPostagePrice;
		}
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/button_add_to_cart")
	@CacheLookup
	AndroidElement addToCartButton;
	@AndroidFindBy(id = "com.ebay.mobile:id/button_view_in_cart")
	@CacheLookup
	AndroidElement viewInCartButton;

	public void addToCartAction() {
		if (addToCartButton.isDisplayed()) {
			addToCartButton.click();
		} else if (viewInCartButton.isDisplayed()) {
			viewInCartButton.click();
		}
	}

	@AndroidFindBy(accessibility = "shopping cart")
	@CacheLookup
	AndroidElement cartButton;

	public void viewCart() {
		cartButton.click();
	}
}
