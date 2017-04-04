package com.ray.autotest.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.ray.autotest.drivers.AirHtmlUnitDriver;
import com.ray.autotest.listeners.LoggingWebDriverEventListener;
import com.ray.autotest.main.AutoTestProperties;

public class Utilities {

    private static WebDriverEventListener loggingListener = new LoggingWebDriverEventListener();

	// Takes a screenshot of the driver's current page, and returns the screenshot name
	// If the current run is not configured to take screenshots, a blank screenshot name is returned
	// If a screenshot is attempted and fails, an exception message is returned
	public static String takeScreenshot(WebDriver driver) {
		String screenshotName = "";
		if (AutoTestProperties.enableScreenshots) {
			try {
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				String now = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss").format(new Date());
				screenshotName = now + "_" + driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("oss_web") + 8).replaceAll("/", "_").replace(".html", "").replace(".htm", "") + ".png";
				String screenshotFolder = "logs/screenshots/";
				FileUtils.copyFile(scrFile, new File(screenshotFolder + screenshotName));
			} catch (IOException e) {
				screenshotName = "Exception: " + e.getMessage();
			}
		}
		return screenshotName;
	}

	public static WebDriver getWebDriver() {

		WebDriver driver = null;

		String webdriverType = AutoTestProperties.webdriverType;

		if ("ChromeDriver".equalsIgnoreCase(webdriverType)) {
			File file = new File(AutoTestProperties.chromeDriverLocation);
			System.setProperty("webdriver.chrome.driver",
					file.getAbsolutePath());
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-extensions");
			//adding in the attempt to resolve the Timed out receiving message from renderer
			options.addArguments("--dns-prefetch-disable");
			options.addArguments("--no-sandbox");
			driver = new ChromeDriver(options);
			//driver = new ChromeDriver();
			
		} else if ("FirefoxDriver".equalsIgnoreCase(webdriverType)) {
			driver = new FirefoxDriver();

		} else if ("InternetExplorerDriver".equalsIgnoreCase(webdriverType)) {
			File file = new File(AutoTestProperties.internetExplorerDriverLocation);
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			// ieCapabilities.setCapability(CapabilityType.VERSION, "9");
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			driver = new InternetExplorerDriver(ieCapabilities);

		} else if ("HtmlUnitDriver".equalsIgnoreCase(webdriverType)) {
			// Headless browser
			// Emulate a Firefox's JavaScript
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			
			String headlessBrowserUserAgent = AutoTestProperties.headlessBrowserUserAgent;
			capabilities.setBrowserName(headlessBrowserUserAgent);

			capabilities.setVersion("24.0");
			driver = new AirHtmlUnitDriver(capabilities);
		}

        if (AutoTestProperties.eventListenersEnabled) {  
		    EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
            eventDriver.register(loggingListener);
            driver = eventDriver;
        }

		return driver;
	}


}
