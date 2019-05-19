package com.ebaytvshopping.testcases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
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

public class TC_PurchaseTv extends BaseSetup { // ** Java OOP concept 'Inheritance' is used here**
	// **Page object design pattern is used here. Objects of all the page classes
	// -are instantiated here**
	private HomePage hp;
	private MenuPage mp;
	private LoginPage lp;
	private SearchResultPage sp;
	private ItemPage ip;
	private CartPage cp;
	private CheckoutPage chp;
	public static Logger logs;

	@BeforeClass // TestNG's @BeforeClass annotated method is used here to initialize all the
					// -required objects. Also, to handle screen orientation**
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
		
		ScreenOrientation ori = driver.getOrientation();
		if (ori.value() == "LANDSCAPE") {
			driver.rotate(ScreenOrientation.PORTRAIT);
		}
	}

	@Test(priority = 1) // First test method to launch the app and click menu button.
	public void launchApp() {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // **Appium Sync technique 'Implicit Wait'
																				// is -used in all the test methods below**
			logs.info("Application launched");
			hp.clickMenu();
		} catch (Exception e) {
			e.getClass().getSimpleName();
			logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
			logs.fatal("Test Failed!! Remainig tests will be skipped.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(priority = 2, dependsOnMethods = { "launchApp" }) // Second test method, which depends on its former, to click
															// -on sign-in option.
	public void goToSignIn() {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			mp.clickSigninText();
		} catch (Exception e) {
			logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
			logs.fatal("Test Failed!! Remainig tests will be skipped.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(priority = 3, dependsOnMethods = { "goToSignIn" }) // Third test method, which depends on its former, to login
																// -to the app using supplied variables.
	public void signInToApp() throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			logs.info("Logging in");
			lp.enterUsername(username);
			lp.enterPassword(password);
			lp.clickSigninButton();
			logs.info("Successfully logged in");
			lp.clickDenyButton();
		} catch (Exception e) {
			logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
			logs.fatal("Test Failed!! Remainig tests will be skipped.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	@SuppressWarnings("deprecation")
	@Test(priority = 4, dependsOnMethods = { "signInToApp" }) // Fourth test method, which depends on its former, to
																// -select a desired TV from the list of search result items.
	public void searchAndSelectItem() throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			hp.tapSearchBox();
			logs.info("Searching for the item");
			hp.enterSearchKeyword(keyword);
			((AndroidDriver<AndroidElement>) driver).pressKeyCode(66);
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"QLED\"));"); // **Appium UI action 'scrolling' is done here**
			sp.selectItem();
			logs.info("Required item is selected");
		} catch (Exception e) {
			logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
			logs.fatal("Test Failed!! Remainig tests will be skipped.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(priority = 5, dependsOnMethods = { "searchAndSelectItem" }) // Fifth test method, which depends on its former,
																		// -to perform actions with cart.
	public void cartActions() throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			logs.info("Cheking item details");
			String itemName = ip.getItemTitle();
			float itemPrice = ip.getItemPrice();
			float itemPostagePrice = ip.getPostagePrice();
			ip.addToCartAction();
			logs.info("Required item is added to cart");
			ip.viewCart();
			logs.info("Checking cart details");
			cp.getCartItemTitle(itemName); // Variables returned from Item Page class are passed as arguments for the Cart Page methods.
			cp.getCartItemPrice(itemPrice);
			cp.getCartPostagePrice(itemPostagePrice);
			cp.checkTotalAmount(itemPrice, itemPostagePrice);
			cp.clickCheckoutButton();
			logs.info("Checking out");
		} catch (Exception e) {
			logs.error("One of the elements of this test could not be found or there is a mismatch between item details between decripton page and cart page. \"\n\" Please refer the error stacktrace below.");
			logs.fatal("Test Failed!! Remainig tests will be skipped.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test(priority = 6, dependsOnMethods = { "cartActions" }) // Sixth test method, which depends on its former, to
																// -perform actions on check-out page.
	public void doCheckOut() {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"Total\"));"); // **Appium UI action 'scrolling' is done here**
			chp.selectPaymentMethod();
			logs.info("Code is not written to add valid credit card or PayPal account");
			logs.info("Code needs to be added when a valid credit card or PayPal details are available");
			chp.confirmPaymenrAndBuy();
			logs.info("Test scenario coverage is completed");
		} catch (Exception e) {
			logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
			logs.fatal("Test Failed!! Remainig tests will be skipped.");
			e.printStackTrace();
			Assert.fail();
		}
	}

	@AfterClass // TestNG's @AfterClass annotated method is used to clear the cart after the
				// -completion of all the tests so that cart remains empty at the time of next run.
	public void clearCart() throws InterruptedException {
		try {
			Thread.sleep(5000);
			driver.startActivity(new Activity(apkpackage, apkactivity));
			ip.viewCart();
			cp.clickRemoveButton();
			logs.info("Post test actions: Cart cleared");
		} catch (Exception e) {
			logs.error("One of the elements of this test could not be found. Please refer the error stacktrace below.");
			logs.fatal("Test Failed!! Remainig tests will be skipped.");
			e.printStackTrace();
			Assert.fail();
		}
	}
}
