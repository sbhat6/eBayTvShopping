package com.ebaytvshopping.pageobjects;

import java.util.List;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchResultPage {
	public SearchResultPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/textview_item_title")
	@CacheLookup
	List<AndroidElement> resultItems;

	public void selectItem() {
		int resultNumber = resultItems.size();
		for (int i = 0; i < resultNumber; i++) {
			String itemName = resultItems.get(i).getText();
			if (itemName.contains("QLED")) {
				resultItems.get(i).click();
				break;
			}
		}
	}
}
