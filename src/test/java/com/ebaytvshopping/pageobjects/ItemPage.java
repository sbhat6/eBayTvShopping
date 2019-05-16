package com.ebaytvshopping.pageobjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//This is a Page-Object class for selected 'Item' page
public class ItemPage {
	public ItemPage(AndroidDriver<AndroidElement> driver) {		//**Java OOP concept 'Constructors' is used here**
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.ebay.mobile:id/textview_item_name']")	// Element locator for item title displayed in the item description.
	@CacheLookup
	AndroidElement itemTitle;

	public String getItemTitle() {
		String itemName = itemTitle.getText();
		return itemName;
	}

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.ebay.mobile:id/textview_item_price\")")		// Element locator for item price displayed in the item description.
	@CacheLookup																								//**Location strategy 'by UIAutomatorViewer' is used here**
	AndroidElement itemValue;

	public float getItemPrice() {		// Method to get the text of the item name, extract numbers part and convert to float data type and return the value to main method.
		String a = itemValue.getText();
		String b = a.substring(4);
		String e = b.replaceAll(",", "");
		float itemPrice = Float.parseFloat(e);
		return itemPrice;
	}

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.ebay.mobile:id/shipping_textview\")")		// Element locator for item postage price displayed in the item description.
	@CacheLookup																								//**Location strategy 'by UIAutomatorViewer is used here**
	AndroidElement itemPostageValue;

	public float getPostagePrice() {		 //Method to get the text of the item price, extract numbers part and convert to float data type and return the value to main method.
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

	@AndroidFindBy(id = "com.ebay.mobile:id/button_add_to_cart")		// Element locator for add to cart button.
	@CacheLookup
	AndroidElement addToCartButton;
	@AndroidFindBy(id = "com.ebay.mobile:id/button_view_in_cart")		// Element locator for view in cart button.
	@CacheLookup
	AndroidElement viewInCartButton;

	public void addToCartAction() {		 //Method to get the text of the item postage price, extract numbers part and convert to float data type and return the value to main method.
		if (addToCartButton.isDisplayed()) {
			addToCartButton.click();
		} else if (viewInCartButton.isDisplayed()) {
			viewInCartButton.click();
		}
	}

	@AndroidFindBy(accessibility = "shopping cart")			// Element locator for open cart button
	@CacheLookup
	AndroidElement cartButton;

	public void viewCart() {
		cartButton.click();
	}
}
