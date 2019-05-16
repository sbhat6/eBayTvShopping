package com.ebaytvshopping.testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.ebaytvshopping.utilities.ReadInputData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseSetup {
	ReadInputData rd = new ReadInputData(); // **Variable input data is supplied from configuration properties file kept
											// -at './Config/InputVariables.properties' which is read by ReadInputData
											// -class. Object 'rd' is created to call the constructor as well as the
											// methods
											// -present in ReadInputData class**
	public String serverip = rd.getServerIp();
	public String serverport = rd.getServerPort();
	public String platformname = rd.getPlatformName();
	public String devicename = rd.getDeviceName();
	public String deviceid = rd.getDeviceId();
	public String apkpath = rd.getApkPath();
	public String apkpackage = rd.getAppPackageName();
	public String apkactivity = rd.getAppActivityName();
	public String username = rd.getUserName();
	public String password = rd.getPassword();
	public String keyword = rd.getKeyword();
	public String tvType = rd.getTvType();
	public String log4jpropath = rd.getLog4jPath();
	AndroidDriver<AndroidElement> driver; // ** Object 'driver' is instantiated from Appum's AndroidDriver class**

	@BeforeTest // **TestNG's @BeforeTest annotation is used to perform Desired Capabilities
				// Definition and AndroidDriver Initialization actions prior to execution of
				// actual tests**
	public void basesetup() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformname);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
		cap.setCapability(MobileCapabilityType.UDID, deviceid);
		cap.setCapability(MobileCapabilityType.APP, apkpath); // Application under test is taken from './Apps/eBay.apk'
																// path.
		cap.setCapability("appPackage", apkpackage);
		cap.setCapability("appActivity", apkactivity);
		cap.setCapability("noReset", false);
		String serverUrl = "http://" + serverip + ":" + serverport + "/wd/hub";
		try { // ** Java error handing technique 'Try-Catch' technique is used here**
			driver = new AndroidDriver<AndroidElement>(new URL(serverUrl), cap); // Android driver is initiated here by
																					// -passing Appium server address and
																					// -Desired Capabilities information.
		} catch (NullPointerException ex) {
			throw new RuntimeException("Appium driver could not be initialised for this device");
		}
		
	}

	@AfterTest // **TestNG's @AfterTest annotation is used to perform driver termination after
				// the execution of all the tests.**
	public void endTest() {
		driver.quit();
	}
}