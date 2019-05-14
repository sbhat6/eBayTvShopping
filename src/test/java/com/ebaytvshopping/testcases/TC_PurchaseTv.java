package com.ebaytvshopping.testcases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import com.ebaytvshopping.pageobjects.CartPage;
import com.ebaytvshopping.pageobjects.CheckoutPage;
import com.ebaytvshopping.pageobjects.HomePage;
import com.ebaytvshopping.pageobjects.ItemPage;
import com.ebaytvshopping.pageobjects.LoginPage;
import com.ebaytvshopping.pageobjects.MenuPage;
import com.ebaytvshopping.pageobjects.SearchResultPage;

public class TC_PurchaseTv extends BaseSetup {
	private HomePage hp;
	private MenuPage mp;
	private LoginPage lp;
	private SearchResultPage sp;
	private ItemPage ip;
	private CartPage cp;
	private CheckoutPage chp;
	public static Logger logs;

	@BeforeClass
	public void initObjects() {
		hp = new HomePage(driver);
		mp = new MenuPage(driver);
		lp = new LoginPage(driver);
		sp = new SearchResultPage(driver);
		ip = new ItemPage(driver);
		cp = new CartPage(driver);
		chp = new CheckoutPage(driver);
		logs = Logger.getLogger(TC_PurchaseTv.class);
		PropertyConfigurator.configure(log4jpropath);
	}

	@Test(priority = 1)
	public void launchApp() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		hp.clickMenu();
	}

	@Test(priority = 2, dependsOnMethods = { "launchApp" })
	public void goToSignIn() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		mp.clickSigninText();
	}

	@Test(priority = 3, dependsOnMethods = { "goToSignIn" })
	public void signInToApp() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			lp.enterUsername(username);
			lp.enterPassword(password);
			lp.clickSigninButton();
			logs.info("Successfully logged in");
			lp.clickDenyButton();
		} catch (NoSuchElementException e) {
			logs.error("One of the elements of this test is not found or timed out");
		} catch (Exception e) {
			throw (e);
		}
	}

	@SuppressWarnings("deprecation")
	@Test(priority = 4, dependsOnMethods = { "signInToApp" })
	public void searchAndSelectItem() throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			hp.tapSearchBox();
			hp.enterSearchKeyword(keyword);
			((AndroidDriver<AndroidElement>) driver).pressKeyCode(66);
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"QLED\"));");
			sp.selectItem();
			logs.info("Required item is selected");
		} catch (NoSuchElementException e) {
			logs.error("One of the elements of searchAndSelectItem test is not found or timed out");
		} catch (Exception e) {
			throw (e);
		}
	}

	@Test(priority = 5, dependsOnMethods = { "searchAndSelectItem" })
	public void cartActions() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String itemName = ip.getItemTitle();
		float itemPrice = ip.getItemPrice();
		float itemPostagePrice = ip.getPostagePrice();
		ip.addToCartAction();
		logs.info("Required item is added to cart");
		try {
			ip.viewCart();
			cp.getCartItemTitle(itemName);
			cp.getCartItemPrice(itemPrice);
			cp.getCartPostagePrice(itemPostagePrice);
			cp.checkTotalAmount(itemPrice, itemPostagePrice);
			cp.clickCheckoutButton();
			logs.info("Checking out");
		} catch (AssertionError e) {
			logs.error("There is mismatch between cart info and item description");
		} catch (NoSuchElementException e) {
			logs.error("One of the elements of cartActions test is not found or timed out");
		}
	}

	@Test(priority = 6, dependsOnMethods = { "cartActions" })
	public void doCheckOut() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"Total\"));");
		chp.selectPaymentMethod();
		logs.info("Code is not written to add valid credit card or PayPal account");
		logs.info("Code needs to be added when a valid credit card or PayPal details are available");
		chp.confirmPaymenrAndBuy();
		logs.info("Test scenario coverage is completed");
	}

	@AfterClass
	public void clearCart() throws InterruptedException {
		Thread.sleep(5000);
		driver.startActivity(new Activity(apkpackage, apkactivity));
		ip.viewCart();
		cp.clickRemoveButton();
	}
}
