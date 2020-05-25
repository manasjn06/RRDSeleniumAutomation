package com.mirketa.testcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mirketa.factory.BrowserFactory;
import com.mirketa.factory.DataProviderFactory;
import com.mirketa.pages.SalesForceLoginPage;
import com.mirketa.utility.Helper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
/**
 * <p><b>Login into SalesForce Test</p></b>
 * <p>1.Open Browser and type url in address bar</p>
 * <p>2. Enter user name in username field.</p>
 * <p>3. Enter password in password field.</p>
 * <p>4. Click on login button</p>
 * <p>5. Verify title of browser window</p>
 * 
 *  * @author Manas Jena
 *
 */

public class SalesForceLoginTest {
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;

	@BeforeMethod
	public void setUp() {
		report=new ExtentReports("./Report/LoginPageReport.html");
		logger=report.startTest("Verify login into SalesForce");
		logger.log(LogStatus.INFO, "Open Browser and type url in address bar");
		driver = BrowserFactory.getBrowser("chrome");
		logger.log(LogStatus.INFO, "Salesforce login page is loading.");
		driver.get(DataProviderFactory.getConfig().getApplicationUrl());
		logger.log(LogStatus.INFO, "Application is up and running");
	}

	@Test
	public void TestLoginPage() throws InterruptedException {
		//SoftAssert softAssertion= new SoftAssert();
		SalesForceLoginPage salesForceLoginPage = PageFactory.initElements(driver, SalesForceLoginPage.class);
		String title1 = salesForceLoginPage.getApplicationTitle();
		System.out.println(title1);
		Assert.assertEquals(title1, "Login | Salesforce","Application title is not matching");
		//logger.log(LogStatus.PASS, "Application title is correct");
		logger.log(LogStatus.PASS, "SalesForce Login page loaded successfully");
		salesForceLoginPage.loginApplicaiton(DataProviderFactory.getExcel().getData(0, 1, 0), DataProviderFactory.getExcel().getData(0, 1, 1));
		logger.log(LogStatus.INFO, "Username and password entered and click on login button");
		Thread.sleep(15000);
		logger.log(LogStatus.PASS, "Login into Salesforce successfully.");
		//softAssertion.assertAll();
				
	}

	@AfterMethod
	public void teardown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String path=Helper.captureScreenshot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
		}
		BrowserFactory.closeBrowser(driver);
		report.endTest(logger);
		report.flush();
	}

}
