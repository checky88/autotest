package com.ray.autotest.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ray.autotest.main.AutoTestProperties;
import com.ray.autotest.pages.HomePage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;





public class HomePageTestSteps extends TestSteps {

	final static Logger logger = Logger.getLogger(HomePageTestSteps.class);

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	@Override
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@Before
	public void beforeScenarios() {
		super.setUp();
	}

	@After
	public void afterScenarios() throws Throwable {
		super.tearDown();
	}

	
	@Given("^the user is on the home page$")
	public void the_user_is_on_the_home_page() throws Throwable {
		super.gotoHomePage();
	}

	@When("^the user clicks contact$")
	public void the_user_clicks_contact() throws Throwable {
		v1ContactPage = v1HomePage.goToContactPage();
	}

	@Then("^the user is brought to the contact page$")
	public void the_user_is_brought_to_the_contact_page() throws Throwable {
	    v1ContactPage.getPageUrl().equals("https://www.version1.com/Contact");
	}

	
    



	




}