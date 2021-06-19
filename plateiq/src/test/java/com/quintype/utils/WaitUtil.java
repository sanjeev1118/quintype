package com.quintype.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

	public static final long SHORT_WAIT = 1000;
	public static final long LONG_WAIT = 4000;



	public static void shortwait() {
		WaitUtil.delay(SHORT_WAIT);
	}
	
	public static void longWait() {
		WaitUtil.delay(LONG_WAIT);
	}


	public static void delay(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebElement waitForElementToBePresent(WebDriver driver, WebElement element) {
		return WaitUtil.waitForElementToBePresent(driver, element, SHORT_WAIT);
	}

	public static WebElement waitForElementToBePresent(WebDriver driver, WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
		return WaitUtil.waitForElementToBeClickable(driver, element, SHORT_WAIT);
	}

	public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, long timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
}
