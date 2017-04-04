package com.ray.autotest.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ray.autotest.main.AutoTestProperties;

public abstract class PageLoadableComponent extends
		LoadableComponent<PageLoadableComponent> {

	private final WebDriver driver;

	public PageLoadableComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		driver.get(getPageUrl());
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlContains(getPageUrl()));
	}

	@Override
	protected void isLoaded() throws Error {
		org.junit.Assert.assertTrue("Page not loaded, expected: "
				+ getUrlSuffix() + " got: " + driver.getCurrentUrl(), driver
				.getCurrentUrl().contains(getUrlSuffix()));
	}

	protected abstract String getUrlSuffix();

	public String getPageUrl() {
		return AutoTestProperties.UrlPrefix + getUrlSuffix();
	}

	protected WebDriver getWebDriver() {
		return driver;
	}

	protected WebElement waitForClickable(By findBy) {
		return waitForClickable(10, findBy);
	}

	protected WebElement waitForClickable(int seconds, By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		return wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}

	protected List<WebElement> waitForPresence(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(findBy));
	}

	protected WebElement waitForVisibility(By findBy) {
		return waitForVisibility(20, findBy);
	}

	protected WebElement waitForVisibility(int seconds, By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		return wait
				.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	protected WebElement waitForGraphVisibility(int seconds, By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		return wait
				.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	protected WebElement waitForGraphVisibility(By findBy) {
		return waitForGraphVisibility(120, findBy);
	}

	protected boolean isClickable(WebElement element) {
		return element.isEnabled() && !element.isSelected();
	}

	protected boolean elementPresent(By by) {
		return driver.findElements(by).size() > 0;
	}

	protected boolean elementPresent(WebElement parent, By by) {
		return parent.findElements(by).size() > 0;
	}

	protected void updateMonthYearDatepicker(int year, int month,
			By byDatePickerDiv, By byDatePicker) {
		// handle 2 digit years
		if (year < 100) {
			year += 2000;
		}

		waitForClickable(byDatePicker).click(); // Activate the date entry

		WebElement datePickerDiv = waitForVisibility(byDatePickerDiv);
		WebElement dp_date_year = datePickerDiv.findElement(By
				.className("ui-datepicker-year"));

		Select yearSel = new Select(dp_date_year);
		try {
			yearSel.selectByValue(String.valueOf(year));
		} catch (Exception E) {
			// System.out.println("Ignore exception");
		}

		WebElement datePickerDiv2 = waitForVisibility(byDatePickerDiv);
		WebElement dp_date_month = datePickerDiv2.findElement(By
				.className("ui-datepicker-month"));
		Select monthSel = new Select(dp_date_month);
		try {
			monthSel.selectByValue(String.valueOf(month - 1)); // value start @
																// 0 so we need
																// the -1
		} catch (Exception E) {
			// System.out.println("Ignore exception");
		}

		/*
		 * This always fails WebElement datePickerDiv3 =
		 * waitForVisibility(byDatePickerDiv); try { WebElement closeButton =
		 * datePickerDiv3.findElement(By.linkText("Done")); closeButton.click();
		 * } catch (Exception E) { //System.out.println("Ignore exception"); }
		 */

	}

	/**
	 * Use only if necessary since it uses xpath
	 * 
	 * @param elem
	 * @return
	 */
	protected WebElement getParentElement(WebElement elem) {
		WebElement parentElem = null;
		if (elem != null) {
			parentElem = elem.findElement(By.xpath(".."));
		}
		return parentElem;
	}



}
