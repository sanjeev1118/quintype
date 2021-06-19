package com.quintype.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.quintype.utils.WaitUtil;

public class ExplorePage {
	
	private final WebDriver driver;
	private String actualVal;
	
	public ExplorePage(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	@FindBy(how = How.XPATH, using = "//a[@href='/pappuyadavjapl']/../following-sibling::div[1]")
	private WebElement personFollowBtn;
	
	public String followPerson() {
		WaitUtil.waitForElementToBeClickable(driver, personFollowBtn);
		personFollowBtn.click();
		WaitUtil.shortwait();
		actualVal = personFollowBtn.getText();
		return actualVal;
	}

}
