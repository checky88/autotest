package com.ray.autotest.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


public class ContactPage extends PageLoadableComponent{
	
	final static Logger logger = Logger.getLogger(HomePage.class);

	public static final String PAGE_URL_SUFFIX = "Contact";
	
	public ContactPage(WebDriver driver) {
		super(driver);
		
	}


	@Override
	protected String getUrlSuffix() {
			return PAGE_URL_SUFFIX;
	}



}
