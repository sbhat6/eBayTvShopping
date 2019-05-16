package com.ebaytvshopping.pageobjects;

import java.util.List;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//This is a Page-Object class for 'Cart' page.
public class CartPage {
	public CartPage(AndroidDriver<AndroidElement> driver) {		//**Java OOP concept 'Constructors' is used here**
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/title") // Element locator for item title displayed in the cart.
	@CacheLookup
	List<AndroidElement> cartItemTitle;

	public void getCartItemTitle(String itemName) { // Method to get the text of the item name, compare it with the same
													// -displayed on Item Page and assert.
		String cartItemName = cartItemTitle.get(1).getText();
		Assert.assertEquals(itemName, cartItemName,
				"Asserion failed: Item name displayed in cart is not matching item description");
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/item_price") // Element locator for item price displayed in the cart
	@CacheLookup
	AndroidElement cartItemPrices;

	public void getCartItemPrice(float itemPrice) { // Method to get the text of the item price, extract numbers part
													// -and convert into float data type, compare with the same displayed
													// -on Item page and assert.
		String t = cartItemPrices.getText();
		String u = t.substring(4);
		String v = u.replace(",", "");
		float w = Float.parseFloat(v);
		Assert.assertEquals(itemPrice, w,
				"Asserion failed: Item price displayed in cart is not matching item description");
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/item_est_shipping_cost") // Element locator for item shipping cost displayed
																		// in the cart.
	@CacheLookup
	AndroidElement cartItemPostagePrices;

	public void getCartPostagePrice(float itemPostagePrice) { // Method to get the text of the item postage price,
																// -extract numbers part and convert into float data
																// -type, compare with the same displayed on Item page
																// -and assert.
		String x = cartItemPostagePrices.getText();
		String y = x.substring(6);
		float z = Float.parseFloat(y);
		Assert.assertEquals(itemPostagePrice, z,
				"Asserion failed: Item shipping cost displayed in cart is not matching item description");
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/shopping_cart_subtotal_amount") // Element locator for item total price
																			// -displayed in the cart.
	@CacheLookup
	AndroidElement totalAmount;

	public void checkTotalAmount(float itemPrice, float itemPostagePrice) { // Method to get the text of the item total
																			// -price, extract numbers part and convert
																			// -into float data type, compare with the
																			// -sum of item price & item postage price
																			// -displayed on Item page and assert.
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

	@AndroidFindBy(id = "com.ebay.mobile:id/shopping_cart_checkout") // Element locator for checkout button displayed in
																		// -the cart.
	@CacheLookup
	AndroidElement checkoutButton;

	public void clickCheckoutButton() { // Method to click on checkout button.
		checkoutButton.click();
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/remove_from_cart_button") // Element locator for remove item button
																		// -displayed in the cart.
	@CacheLookup
	AndroidElement removeButton;

	public void clickRemoveButton() { // Method to click on remove item button only if it exists and then close the
										// -cart page.
		if (removeButton.isDisplayed()) {
			removeButton.click();
			removeAgainButton.click();
			closeCartButton.click();
		} else
			closeCartButton.click();
	}

	@AndroidFindBy(id = "android:id/button1") // Element locator for remove item confirmation displayed on the pop-up.
	@CacheLookup
	AndroidElement removeAgainButton; // This element is used in clickRemoveButton method above.
	@AndroidFindBy(id = "com.ebay.mobile:id/toolbar_close_button")
	@CacheLookup
	AndroidElement closeCartButton; // This element is used in clickRemoveButton method above.
}
