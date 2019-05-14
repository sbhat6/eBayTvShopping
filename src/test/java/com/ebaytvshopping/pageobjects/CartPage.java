package com.ebaytvshopping.pageobjects;

import java.util.List;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage {
	public CartPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/title")
	@CacheLookup
	List<AndroidElement> cartItemTitle;

	public void getCartItemTitle(String itemName) {
		String cartItemName = cartItemTitle.get(1).getText();
		Assert.assertEquals(itemName, cartItemName,
				"Asserion failed: Item name displayed in cart is not matching item description");
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/item_price")
	@CacheLookup
	AndroidElement cartItemPrices;

	public void getCartItemPrice(float itemPrice) {
		String t = cartItemPrices.getText();
		String u = t.substring(4);
		String v = u.replace(",", "");
		float w = Float.parseFloat(v);
		Assert.assertEquals(itemPrice, w,
				"Asserion failed: Item price displayed in cart is not matching item description");
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/item_est_shipping_cost")
	@CacheLookup
	AndroidElement cartItemPostagePrices;

	public void getCartPostagePrice(float itemPostagePrice) {
		String x = cartItemPostagePrices.getText();
		String y = x.substring(6);
		float z = Float.parseFloat(y);
		Assert.assertEquals(itemPostagePrice, z,
				"Asserion failed: Item shipping cost displayed in cart is not matching item description");
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/shopping_cart_subtotal_amount")
	@CacheLookup
	AndroidElement totalAmount;

	public void checkTotalAmount(float itemPrice, float itemPostagePrice) {
		String p = totalAmount.getText();
		String q = p.substring(4);
		String r = q.replaceAll(",", "");
		float s = Float.parseFloat(r);
		if (s == itemPrice + itemPostagePrice) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false,
					"Asserion failed: Item total price displayed in cart is not the sum of item price and shipping cost");
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/shopping_cart_checkout")
	@CacheLookup
	AndroidElement checkoutButton;

	public void clickCheckoutButton() {
		checkoutButton.click();
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/remove_from_cart_button")
	@CacheLookup
	AndroidElement removeButton;

	public void clickRemoveButton() {
		if (removeButton.isDisplayed()) {
			removeButton.click();
			removeAgainButton.click();
			closeCartButton.click();
		} else
			closeCartButton.click();
	}

	@AndroidFindBy(id = "android:id/button1")
	@CacheLookup
	AndroidElement removeAgainButton;
	@AndroidFindBy(id = "com.ebay.mobile:id/toolbar_close_button")
	@CacheLookup
	AndroidElement closeCartButton;
}
