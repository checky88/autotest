package com.ray.autotest.listeners;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.ray.autotest.utils.Utilities;

public class LoggingWebDriverEventListener implements WebDriverEventListener {

	final static Logger logger = Logger
			.getLogger(LoggingWebDriverEventListener.class);

	public void beforeNavigateTo(String url, WebDriver driver) {
		logger.info("Attempting to navigating to: " + url);
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		logger.info("Successfully navigated to: " + url);
	}

	public void beforeNavigateBack(WebDriver driver) {
		//

	}

	public void afterNavigateBack(WebDriver driver) {
		//

	}

	public void beforeNavigateForward(WebDriver driver) {
		//

	}

	public void afterNavigateForward(WebDriver driver) {
		//

	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		//

	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		//

	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		logger.info("Attempting to click " + element.getText());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		logger.info("Successfully clicked" + element.getText());
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		//

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		//

	}

	public void beforeScript(String script, WebDriver driver) {
		//

	}

	public void afterScript(String script, WebDriver driver) {
		//

	}

	public void onException(Throwable throwable, WebDriver driver) {
		logger.info("Exception thrown: " + throwable.getMessage());
		if(throwable.getMessage().contains("stale element reference: element is not attached to the page document")){
			logger.info("Not taking screenshot as Exception is stale element ");
		}
		else{
		String screenshotName = Utilities.takeScreenshot(driver);
		
		if (screenshotName.startsWith("Exception:")) {
			logger.info("Could not take screenshot where exception was thrown ["
					+ driver.getCurrentUrl() + "] :: " + screenshotName);
		} else if (!"".equals(screenshotName)) {
			logger.info("Screenshot where exception was thrown: "
					+ screenshotName);
		}
		}
	}

}
