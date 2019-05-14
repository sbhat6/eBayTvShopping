package com.ebaytvshopping.pageobjects;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutPage {
	public CheckoutPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/payment_main_text")
	@CacheLookup
	AndroidElement paymentMethod;

	public void selectPaymentMethod() {
		// Code needs to be added when a valid credit card or PayPal details are available
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/purchase_link")
	@CacheLookup
	AndroidElement paymentConfirm;

	public void confirmPaymenrAndBuy() {
		// This button will get enable only after a valid payment method is added
		if (paymentConfirm.isEnabled()) {
			paymentConfirm.click();
		} else
			System.out.println("\"CONFIRM AND PAY\" button is disabled because there is no payment method");
	}
}
