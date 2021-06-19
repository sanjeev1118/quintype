package com.quintype.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.quintype.utils.WaitUtil;

public class LoginPage {

	private final WebDriver driver;
	
	
	public LoginPage (WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.LINK_TEXT, using = "Log in")
	private WebElement loginLink;
	
	@FindBy(how = How.XPATH, using = "(//input[@name='session[username_or_email]'])[1]")
	private WebElement usernameTxtBox;

	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	private WebElement passwordTxtBox;
	
	@FindBy(how = How.XPATH, using = "(//span[text()='Log in'])[1]")
	private WebElement loginBtn;
	

	public void login(String username, String password) {
		WaitUtil.waitForElementToBeClickable(driver, loginLink);
		loginLink.click();
		enterUsername(username);
		enterPassword(password);
		loginBtn.click();
	}
	
	public void enterUsername(String username) {
		WaitUtil.waitForElementToBePresent(driver, usernameTxtBox);
		usernameTxtBox.clear();
		usernameTxtBox.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		WaitUtil.waitForElementToBePresent(driver, passwordTxtBox);
		passwordTxtBox.clear();
		passwordTxtBox.sendKeys(password);
	}

}

