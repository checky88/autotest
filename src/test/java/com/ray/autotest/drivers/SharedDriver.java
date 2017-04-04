package com.ray.autotest.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.ray.autotest.main.AutoTestProperties;
import com.ray.autotest.utils.Utilities;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

/**
 * @author Ray Kelly
 * 
 * 
 */
public class SharedDriver extends EventFiringWebDriver {
	private static final WebDriver REAL_DRIVER;
	private static final Thread CLOSE_THREAD = new Thread() {
		@Override
		public void run() {
			REAL_DRIVER.close();
		}
	};

	static {
		REAL_DRIVER = Utilities.getWebDriver();

		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	}

	public SharedDriver() {
		super(REAL_DRIVER);
	}

	@Override
	public void close() {
		if (Thread.currentThread() != CLOSE_THREAD) {
			throw new UnsupportedOperationException(
					"You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
		}
		super.close();
	}

	@Before
	public void deleteAllCookies() {
		manage().deleteAllCookies();
	}

	@After
	protected void tearDown() throws Throwable {
		logout_of_OSS();
		
	}

	protected void logout_of_OSS() throws Throwable {
		if (REAL_DRIVER != null) {
			REAL_DRIVER.get(AutoTestProperties.UrlPrefix
					+ "/j_spring_security_logout");
			// REAL_DRIVER.manage().deleteAllCookies();
		}
	}

}