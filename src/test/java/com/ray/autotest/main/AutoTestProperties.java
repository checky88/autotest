package com.ray.autotest.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AutoTestProperties {

	final static Logger logger = Logger.getLogger(AutoTestProperties.class);

	public static final String C_AUTOTEST_PROPERTIES_FILE = "autotest.properties";

	public static String UrlPrefix;

	public static String peaceJdbcUserid;
	public static String peaceJdbcPassword;
	public static String peaceJdbcUrl;

	public static String webdriverType;
	public static boolean throwExceptionOnHeadlessBrowserJavascriptError;
	public static String headlessBrowserUserAgent;

	public static String chromeDriverLocation;
	public static String internetExplorerDriverLocation;
	public static String htmlUnitDriverLocation;

	public static boolean useNewBrowserForEachTest;

	public static String overrideCurrentDomesticDN;

	public static boolean eventListenersEnabled = false;

	public static boolean enableScreenshots = true;

	public static boolean loadProperties() {

		try {

			logger.info("Loading properties file " + C_AUTOTEST_PROPERTIES_FILE);

			Properties props = new Properties();
			props.load(new FileInputStream(C_AUTOTEST_PROPERTIES_FILE));

			UrlPrefix = System.getProperty("UrlPrefix");
			if (UrlPrefix == null) {
				UrlPrefix = props.getProperty("UrlPrefix");
			}
			UrlPrefix = UrlPrefix
					+ (UrlPrefix.endsWith("/") ? "" : "/");


			webdriverType = System.getProperty("webdriverType");
			if (webdriverType == null) {
				webdriverType = props.getProperty("webdriverType");
			}

			throwExceptionOnHeadlessBrowserJavascriptError = "Y"
					.equalsIgnoreCase(props
							.getProperty("throwExceptionOnHeadlessBrowserJavascriptError"));

			headlessBrowserUserAgent = System
					.getProperty("headlessBrowserUserAgent");
			if (headlessBrowserUserAgent == null) {
				headlessBrowserUserAgent = props
						.getProperty("headlessBrowserUserAgent");
			}

			chromeDriverLocation = props.getProperty("chromeDriverLocation");
			internetExplorerDriverLocation = props
					.getProperty("internetExplorerDriverLocation");
			htmlUnitDriverLocation = props
					.getProperty("htmlUnitDriverLocation");

			useNewBrowserForEachTest = "Y".equalsIgnoreCase(props
					.getProperty("useNewBrowserForEachTest"));

			String enableEventListeners = System
					.getProperty("enableEventListeners");
			if (enableEventListeners == null) {
				eventListenersEnabled = "Y".equalsIgnoreCase(props
						.getProperty("enableEventListeners"));
			} else {
				eventListenersEnabled = "Y"
						.equalsIgnoreCase(enableEventListeners);
			}

			if ("HtmlUnitDriver".equals(webdriverType)) {
				enableScreenshots = false; // Headless browser provides no
											// screenshot
			} else {
				String enableScreenshotsProperty = System
						.getProperty("enableScreenshots");
				if (enableScreenshotsProperty == null) {
					enableScreenshots = "Y".equalsIgnoreCase(props
							.getProperty("enableScreenshots"));
				} else {
					enableScreenshots = "Y"
							.equalsIgnoreCase(enableScreenshotsProperty);
				}
			}


			logger.info("Properties :: UrlPrefix=" + UrlPrefix);
			logger.info("Properties :: webdriverType=" + webdriverType);

			if (webdriverType.equals("htmlUnitDriver")) {
				logger.info("Properties :: throwExceptionOnHeadlessBrowserJavascriptError="
						+ throwExceptionOnHeadlessBrowserJavascriptError);
				logger.info("Properties :: headlessBrowserUserAgent="
						+ headlessBrowserUserAgent);
				logger.info("Properties :: takeScreenshots="
						+ enableScreenshots + " (disabled for headless browser");
			} else {
				logger.info("Properties :: takeScreenshots="
						+ enableScreenshots);
			}

			logger.info("Properties :: eventListenersEnabled="
					+ eventListenersEnabled);

			if (webdriverType.equals("ChromeDriver")) {
				logger.info("Properties :: chromeDriverLocation="
						+ chromeDriverLocation);
			}
			if (webdriverType.equals("internetExplorerDriver")) {
				logger.info("Properties :: internetExplorerDriverLocation="
						+ internetExplorerDriverLocation);
			}
			logger.info("Properties :: useNewWebdriverForEachTest="
					+ useNewBrowserForEachTest);

		
		

			return true;

		} catch (IOException e) {
			logger.error("Cannot load properties file "
					+ AutoTestProperties.C_AUTOTEST_PROPERTIES_FILE, e);
			return false;
		}
	}

}
