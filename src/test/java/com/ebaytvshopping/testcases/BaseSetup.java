package com.ebaytvshopping.testcases;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.ebaytvshopping.utilities.ReadInputData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseSetup {
	ReadInputData rd = new ReadInputData();
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
	AndroidDriver<AndroidElement> driver;

	@BeforeTest
	public void basesetup() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformname);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
		cap.setCapability(MobileCapabilityType.UDID, deviceid);
		cap.setCapability(MobileCapabilityType.APP, apkpath);
		cap.setCapability("appPackage", apkpackage);
		cap.setCapability("appActivity", apkactivity);
		cap.setCapability("noReset", false);
		String serverUrl = "http://" + serverip + ":" + serverport + "/wd/hub";
		try {
			driver = new AndroidDriver<AndroidElement>(new URL(serverUrl), cap);
		} catch (NullPointerException ex) {
			throw new RuntimeException("Appium driver could not be initialised for this device");
		}
	}

	@AfterTest
	public void endTest() {
		driver.quit();
	}
}