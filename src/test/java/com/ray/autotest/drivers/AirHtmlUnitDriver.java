package com.ray.autotest.drivers;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.WebClient;
import com.ray.autotest.main.AutoTestProperties;

public class AirHtmlUnitDriver extends HtmlUnitDriver {

	/*
	 * The only purpose of this class is to suppress javascript exceptions.
	 * 
	 * This is necessary because the headless browser will fail on javascript exceptions that the regular browsers forgive.
	 * This will undoubtedly result in false positives, so we will have to include rigourous test steps to ensure that the 
	 * screen output is correct.
	 */
	public AirHtmlUnitDriver(DesiredCapabilities capabilities) {
		super(capabilities);
		super.setJavascriptEnabled(true);
	}

	@Override
	protected WebClient modifyWebClient(WebClient client) {
		WebClient modifiedClient = super.modifyWebClient(client);

		modifiedClient.getOptions().setThrowExceptionOnScriptError(AutoTestProperties.throwExceptionOnHeadlessBrowserJavascriptError);
		return modifiedClient;
	}
}
