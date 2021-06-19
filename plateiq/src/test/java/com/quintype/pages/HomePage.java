package com.quintype.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.quintype.utils.WaitUtil;

public class HomePage {
	private WebDriver driver;
	
	public HomePage(final WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.XPATH, using = "//input[@data-testid='SearchBox_Search_Input']")
	private WebElement searchBox;
	
	public void search(String searchParameter) {
		WaitUtil.waitForElementToBePresent(driver, searchBox);
		searchBox.clear();
		searchBox.sendKeys(searchParameter, Keys.ENTER);
	} 
	
}
