package com.ebaytvshopping.pageobjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import com.ebaytvshopping.testcases.TC_PurchaseTv;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//This is a Page-Object class for 'Checkout' page
public class CheckoutPage extends TC_PurchaseTv {
	public CheckoutPage(AndroidDriver<AndroidElement> driver) { // **Java OOP concept 'Constructors' is used here**
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/payment_main_text") // Element locator for selecting payment option
																// -displayed at the time of checkout.
	AndroidElement paymentMethod;

	public void selectPaymentMethod() { // Method to add a valid payment method.
		// Code needs to be added when a valid credit card or PayPal details are
		// -available.
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/purchase_link") // Element locator for confirm purchase button displayed on
															// -the checkout page.
	@CacheLookup
	AndroidElement paymentConfirm;

	public void confirmPaymenrAndBuy() {// Method to click on confirm button after adding payment method.
		// This button will get enable only after a valid payment method is added.
		if (paymentConfirm.isEnabled()) {
			paymentConfirm.click();
		} else
			System.out.println("\"CONFIRM AND PAY\" button is disabled because there is no payment method");
	}
}
