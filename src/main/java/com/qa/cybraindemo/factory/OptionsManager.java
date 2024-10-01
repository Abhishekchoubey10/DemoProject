package com.qa.cybraindemo.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			co.addArguments("--incognito");

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setPlatformName("linux");
			//co.setCapability("enableVNC", true); // this part will add to visualize the test in browser
			co.setBrowserVersion(prop.getProperty("browserversion"));

			co.setCapability("selenoid:options", new HashMap<String, Object>() {{
			    /* How to add test badge */
			    put("name", "Test badge...");

			    /* How to set session timeout */
			    put("sessionTimeout", "15m");

			    /* How to set timezone */
			    put("env", new ArrayList<String>() {{
			        add("TZ=UTC");
			    }});

			    /* How to add "trash" button */
			    put("labels", new HashMap<String, Object>() {{
			        put("manual", "true");
			    }});

			    /* How to enable video recording */
			    put("enableVNC", true);
			}});
		}

		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		fo.addArguments("--remote-allow-origins=*");
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			fo.addArguments("--incognito");

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setPlatformName("linux");
			//fo.setCapability("enableVNC", true); // this part will add to visualize the test in browser
			fo.setBrowserVersion(prop.getProperty("browserversion"));
			fo.setCapability("selenoid:options", new HashMap<String, Object>() {{
			    /* How to add test badge */
			    put("name", "Test badge...");

			    /* How to set session timeout */
			    put("sessionTimeout", "15m");

			    /* How to set timezone */
			    put("env", new ArrayList<String>() {{
			        add("TZ=UTC");
			    }});

			    /* How to add "trash" button */
			    put("labels", new HashMap<String, Object>() {{
			        put("manual", "true");
			    }});

			    /* How to enable video recording */
			    put("enableVNC", true);
			}});
		}

		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		eo.addArguments("--remote-allow-origins=*");
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			eo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			eo.addArguments("--incognito");

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setPlatformName("linux");
		}

		return eo;
	}
}
