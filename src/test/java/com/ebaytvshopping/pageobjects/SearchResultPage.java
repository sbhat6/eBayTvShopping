package com.ebaytvshopping.pageobjects;

import java.util.List;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import com.ebaytvshopping.testcases.TC_PurchaseTv;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

//This is a Page-Object class for 'Search Result' page
public class SearchResultPage extends TC_PurchaseTv { // **Java OOP concept 'Constructors' is used here**
	public SearchResultPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/textview_item_title") // Element locator for getting all item names from the
																	// -search result.
	@CacheLookup
	List<AndroidElement> resultItems;

	public void selectItem() { // Method to select one item title from the list of item titles based on a keyword. //
		int resultNumber = resultItems.size();
			for (int i = 0; i < resultNumber; i++) { // **Iteration strategy 'for loop' is used here to traverse through
														// -list of Android elements captured is used here**
				String itemName = resultItems.get(i).getText();
				if (itemName.contains("QLED")) {
					resultItems.get(i).click();
					break;
				}
			}
		}
}
