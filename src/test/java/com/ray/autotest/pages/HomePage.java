package com.ray.autotest.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends PageLoadableComponent {

	final static Logger logger = Logger.getLogger(HomePage.class);

	public static final String PAGE_URL_SUFFIX = "Home";


	private WebElement btn_contact;


	public HomePage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected String getUrlSuffix() {
		return PAGE_URL_SUFFIX;
	}

	private WebElement getContactButton() {
		btn_contact = waitForClickable(By.xpath("//*[@id='menuElem']/li[9]/a"));
		return btn_contact;
		
	}



	
	public ContactPage goToContactPage() 
	{
		try{
		getContactButton().click();
		}
		catch  (Exception e) {
			//ignore
		}
		
		
		return (ContactPage) new ContactPage(getWebDriver())
				.get();
	}



}
