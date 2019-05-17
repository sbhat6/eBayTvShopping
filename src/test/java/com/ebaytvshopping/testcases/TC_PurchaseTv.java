package com.ebaytvshopping.testcases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ScreenOrientation;
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
					// required objects. Also, to handle screen orientation**
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // **Appium Sync technique 'Implicit Wait' is
																			// -used in all the test methods below**
		hp.clickMenu();
	}

	@Test(priority = 2, dependsOnMethods = { "launchApp" }) // Second test method, which depends on its former, to click
															// -on sign-in option.
	public void goToSignIn() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		mp.clickSigninText();
	}

	@Test(priority = 3, dependsOnMethods = { "goToSignIn" }) // Third test method, which depends on its former, to login
																// -to the app using supplied variables.
	public void signInToApp() throws Exception {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try { // ** Java error handing technique 'Try-Catch' technique is used here**
			lp.enterUsername(username);
			lp.enterPassword(password);
			lp.clickSigninButton();
			logs.info("Successfully logged in");
			lp.clickDenyButton();
		} catch (NoSuchElementException e) {
			logs.error("One of the elements of this test is not found or timed out"); // ** Logging tool Log4J is used
																						// -in most of the test methods
																						// -below to print user-friendly
																						// -information in the logs.**
		} catch (Exception e) {
			throw (e);
		}
	}

	@SuppressWarnings("deprecation")
	@Test(priority = 4, dependsOnMethods = { "signInToApp" }) // Fourth test method, which depends on its former, to
																// -select a desired TV from the list of search result
																// -items.
	public void searchAndSelectItem() throws Exception {
		try { // ** Java error handing technique 'Try-Catch' technique is used here**
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			hp.tapSearchBox();
			hp.enterSearchKeyword(keyword);
			((AndroidDriver<AndroidElement>) driver).pressKeyCode(66);
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"QLED\"));"); // **Appium UI action
																									// -'scrolling' is
																									// -done here.**
			sp.selectItem();
			logs.info("Required item is selected");
		} catch (NoSuchElementException e) {
			logs.error("One of the elements of searchAndSelectItem test is not found or timed out");
		} catch (Exception e) {
			throw (e);
		}
	}

	@Test(priority = 5, dependsOnMethods = { "searchAndSelectItem" }) // Fifth test method, which depends on its former,
																		// -to perform actions with cart.
	public void cartActions() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String itemName = ip.getItemTitle();
		float itemPrice = ip.getItemPrice();
		float itemPostagePrice = ip.getPostagePrice();
		ip.addToCartAction();
		logs.info("Required item is added to cart");
		try { // ** Java error handing technique 'Try-Catch' technique is used here**
			ip.viewCart();
			cp.getCartItemTitle(itemName); // Variables returned from Item Page class are passed as arguments for the
											// -Cart Page methods.
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

	@Test(priority = 6, dependsOnMethods = { "cartActions" }) // Sixth test method, which depends on its former, to
																// -perform actions on check-out page.
	public void doCheckOut() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"Total\"));"); // **Appium UI action
																								// -'scrolling' is done
																								// -here.**
		chp.selectPaymentMethod();
		logs.info("Code is not written to add valid credit card or PayPal account");
		logs.info("Code needs to be added when a valid credit card or PayPal details are available");
		chp.confirmPaymenrAndBuy();
		logs.info("Test scenario coverage is completed");
	}

	@AfterClass // TestNG's @AfterClass annotated method is used to clear the cart after the
				// -completion of all the tests so that cart remains empty at the time of next
				// run.
	public void clearCart() throws InterruptedException {
		Thread.sleep(5000);
		driver.startActivity(new Activity(apkpackage, apkactivity));
		ip.viewCart();
		cp.clickRemoveButton();
	}
}
