package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver ldriver)
	{
		this.driver = ldriver;
	}
	
	@FindBy(xpath="/html/body/div[1]/header/div/nav/div[2]/div/a") WebElement firstLoginButton;
	@FindBy(name="email") WebElement uname;
	@FindBy(name="password") WebElement pass;
	@FindBy(xpath="//*[@id=\"ui\"]/div/div/form/div/div[3]") WebElement loginButton;
	
	public void loginToCRM(String unameApplication, String passapplication)
	{
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
		}
		firstLoginButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		uname.sendKeys(unameApplication);
		pass.sendKeys(passapplication);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
		loginButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
	}
}
