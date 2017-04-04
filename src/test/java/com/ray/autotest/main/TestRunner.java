package com.ray.autotest.main;

import java.io.File;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", glue = {
		"classpath:com.ray.autotest.tests",
		"com.ray.autotest.drivers" }, 
		tags = { "~@ignore" }, 
		plugin = {
		                "ru.yandex.qatools.allure.cucumberjvm.AllureReporter"
		        },
		format = {
		"pretty", "html:reports/cucumber",
		"json:reports/cucumber-report.json",
		"junit:reports/cucumber.xml" }, monochrome = true)
public class TestRunner {

	public static void main(String[] args) throws Exception {

		// if the logs folder does not exist, create it
		File theDir = new File("logs");
		if (!theDir.exists()) {
			theDir.mkdir();
		}

		// if the reports folder does not exist, create it
		theDir = new File("reports");
		if (!theDir.exists()) {
			theDir.mkdir();
		}

		// if the reports/screenshots folder does not exist, create it
		theDir = new File("reports/screenshots");
		if (!theDir.exists()) {
			theDir.mkdir();
		}

		JUnitCore.main("com.ray.autotest.main.TestRunner");

	}
}
