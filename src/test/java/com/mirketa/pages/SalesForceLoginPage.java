package com.mirketa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mirketa.factory.BrowserFactory;
import com.mirketa.factory.DataProviderFactory;

public class SalesForceLoginPage {
	WebDriver driver;

	public SalesForceLoginPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(xpath = "//input[@id='username']")
	WebElement userName;
	@FindBy(xpath = "//input[@id='password']")
	WebElement passWord;
	@FindBy(xpath = "//input[@id='Login']")
	WebElement loginButton;
	
	public String getApplicationTitle()
	{
		return driver.getTitle();
	}
	public void loginApplicaiton(String user,String password)
	{
		userName.sendKeys(user);
		passWord.sendKeys(password);
		loginButton.click();
	}
	


}
