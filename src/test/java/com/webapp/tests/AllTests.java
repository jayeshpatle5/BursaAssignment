package com.webapp.tests;


import com.webapp.helpers.TestCaseClass;
import com.automation.core.config.ProjectConfig;
import com.automation.core.driver.DriverFactory;
import com.automation.core.testng.BaseTest;
import com.automation.core.utils.reporter.Report;
import com.google.inject.Guice;
import com.google.inject.Injector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllTests  extends BaseTest {

	@DataProvider (name = "data-provider-props")
	public String[][] dpMethodProps(){
		return new String[][]{{ProjectConfig.getPropertyValue("DisplayName"),
			ProjectConfig.getPropertyValue("EmailAddress"),
			ProjectConfig.getPropertyValue("Password")}};
	}



	@Test(groups = { "P1" }, description = "Equity sorting")
	public void MarketOverview() throws InterruptedException, Exception {
		TestCaseClass.equityPriceSorting(driver);
	}


	@Test (dataProvider = "data-provider-props")
	public void RegistrationModule_DP_props(String displayName, String email,String password ) throws InterruptedException, IOException {
		TestCaseClass.registrationValidation(driver,displayName,email,password);
	}


	@Test
	public void RegistrationModule_Inject_UserObject() throws InterruptedException, IOException 
	{
		TestConfiguration config = new TestConfiguration();
		Injector injector = Guice.createInjector(config);
		TestImpl test = injector.getInstance(TestImpl.class);
		TestCaseClass.registrationValidation(driver,test.name,test.email,test.pwd);
	}






}
