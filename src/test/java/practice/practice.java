package practice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class practice {

	public static void main(String[] args) throws MalformedURLException {


		DesiredCapabilities dcap = new DesiredCapabilities();
		dcap.setCapability(MobileCapabilityType.DEVICE_NAME, "GalaxyS8");
		dcap.setCapability(MobileCapabilityType.UDID, "ce12160ca41e7e1705");
		dcap.setCapability(MobileCapabilityType.APP, "C:\\Users\\sb.ganapati\\Downloads\\eBay.apk");
		URL url = new URL ("http://127.0.01:4723/wd/hub");
		AndroidDriver driver = new AndroidDriver(url, dcap);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElementById("com.ebay.mobile:id/home").click();

	}

}
