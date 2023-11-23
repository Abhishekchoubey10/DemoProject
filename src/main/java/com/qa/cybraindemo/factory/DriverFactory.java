package com.qa.cybraindemo.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.aspectj.util.FileUtil;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.io.Files;
import com.qa.cybraindemo.utils.Errors;



public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsmanager;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	public WebDriver insit_driver(Properties prop) {
		String browserName = "chrome";
		System.out.println("Browser name is :" + browserName);
		optionsmanager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		if (browserName.equalsIgnoreCase("chrome")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver("chrome");
			} else {
				//WebDriverManager.chromedriver().setup();
				tdriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));

			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver("firefox");
			} else {
				//WebDriverManager.firefoxdriver().setup();
				tdriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
			}
		} else if (browserName.equalsIgnoreCase("edge")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteWebDriver("edge");
			} else {
				//WebDriverManager.edgedriver().setup();
				tdriver.set(new EdgeDriver(optionsmanager.getEdgeOptions()));
			}
		} else {
			System.out.println(Errors.BROWSER_NOT_FOUND_ERROR_MSG + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	/**
	 * This method is use to run script on remote docker machine
	 * 
	 * @param browserName
	 */
	private void init_remoteWebDriver(String browserName) {
		System.out.println("Running Test case on remote grid" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			try {
				tdriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			try {
				tdriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("edge")) {
			try {
				tdriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getEdgeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is use to initialize the properties on the basis of given Actor
	 *
	 * 
	 * @param actName
	 * 
	 * @return
	 */
	public Properties insit_prop() {
		prop = new Properties();
		FileInputStream ip = null;

		try {
			ip = new FileInputStream("./src/test/resources/config/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();

		}

		return prop;
	}

	/**
	 * This will return the thread local copy of WebDriver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tdriver.get();
	}

	/**
	 * Take Screenshot
	 * 
	 * @return
	 */
	public static String getScreenshot() {
	File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";

	File Destination = new File(path);
	try {
			FileUtil.copyFile(srcFile, Destination);//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}