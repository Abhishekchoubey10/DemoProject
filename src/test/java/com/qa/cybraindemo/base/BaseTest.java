package com.qa.cybraindemo.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.cybraindemo.factory.DriverFactory;
import com.qa.cybraindemo.pages.ContactUsPage;

public class BaseTest {
	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public ContactUsPage contactusPage;

	public SoftAssert softAssert;

	@Parameters({ "browser", "browserversion"})
	@BeforeTest
	public void setup(String browser, String browserVersion) {
		df = new DriverFactory();
		prop = df.insit_prop();

		if (browser != null) {
			prop.setProperty("browser", browser);
			prop.setProperty("browserversion", browserVersion);
		}

		driver = df.insit_driver(prop);
		contactusPage = new ContactUsPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
