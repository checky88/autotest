package com.ray.autotest.tests;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ray.autotest.drivers.SharedDriver;
import com.ray.autotest.main.AutoTestProperties;
import com.ray.autotest.pages.ContactPage;
import com.ray.autotest.pages.HomePage;

//import cucumber.runtime.PendingException;
import cucumber.api.java.en.*;
import junit.framework.Assert;


public abstract class TestSteps {

	final static Logger logger = Logger.getLogger(TestSteps.class);

	private static boolean firstRun = true;

	

	protected HomePage v1HomePage = null;
	protected ContactPage v1ContactPage = null;
	
	

	public void setUp() {
		if (firstRun) {
			AutoTestProperties.loadProperties();
			firstRun = false;
		}

		if (getDriver() == null || AutoTestProperties.useNewBrowserForEachTest) {
			setDriver(new SharedDriver());
		}

	}

	

	
	protected void gotoHomePage() throws Throwable {
		logger.info("Attempting to navigate to V1 Home page");
		v1HomePage = (HomePage) new HomePage(getDriver()).get();
		logger.info("Navigated to " + v1HomePage.getPageUrl());
	}


	protected void logout_of_OSS() throws Throwable {
		if (getDriver() != null) {
			getDriver().get(
					AutoTestProperties.UrlPrefix
							+ "/j_spring_security_logout");
			getDriver().manage().deleteAllCookies();
			logger.info("Logged out of the application");
		}
	}

	protected void tearDown() throws Throwable {
		//
	}

	protected void close_browser() {
		if (getDriver() != null) {
			getDriver().quit();
			setDriver(null);
		}
	}

	protected void reopen_browser() {
		this.setUp();
	}

	public abstract WebDriver getDriver();

	public abstract void setDriver(WebDriver driver);

}
