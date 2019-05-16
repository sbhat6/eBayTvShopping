package com.ebaytvshopping.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadInputData { // This class is responsible for reading the values of the variables defined at
								// '/Config/InputVariables.properties' file.
	public Properties prop;

	public ReadInputData() { // Java OOP concept 'Constructors' is used here**
		File fl = new File("./Config/InputVariables.properties");
		try {
			FileInputStream fs = new FileInputStream(fl); // **Java Properties & FileInputStream classes are used here**
			prop = new Properties();
			prop.load(fs);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}

	public String getServerIp() { // All the methods here are used to read the file contents
		String serverIp = prop.getProperty("serverip");
		return serverIp;
	}

	public String getServerPort() {
		String serverPort = prop.getProperty("serverport");
		return serverPort;
	}

	public String getApkPath() {
		String apkPath = prop.getProperty("apkpath");
		return apkPath;
	}

	public String getUserName() {
		String userName = prop.getProperty("username");
		return userName;
	}

	public String getPassword() {
		String passWord = prop.getProperty("password");
		return passWord;
	}

	public String getKeyword() {
		String searchKeyword = prop.getProperty("searchkeyword");
		return searchKeyword;
	}

	public String getPlatformName() {
		String platformName = prop.getProperty("platformname");
		return platformName;
	}

	public String getDeviceName() {
		String deviceName = prop.getProperty("devicename");
		return deviceName;
	}

	public String getAppPackageName() {
		String packageName = prop.getProperty("apkpackage");
		return packageName;
	}

	public String getAppActivityName() {
		String activityName = prop.getProperty("apkactivity");
		return activityName;
	}

	public String getDeviceId() {
		String deviceId = prop.getProperty("deviceudid");
		return deviceId;
	}

	public String getTvType() {
		String tvType = prop.getProperty("tvtype");
		return tvType;
	}

	public String getLog4jPath() {
		String propath = prop.getProperty("log4jpropath");
		return propath;
	}
}
