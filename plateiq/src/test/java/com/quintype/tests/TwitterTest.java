package com.quintype.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.quintype.base.Base;
import com.quintype.pages.ExplorePage;
import com.quintype.pages.HomePage;
import com.quintype.pages.LoginPage;

public class TwitterTest extends Base{
	private HomePage homePage;
	private ExplorePage explorePage;
	
	@BeforeClass
	public void setup() {
		homePage = PageFactory.initElements(driver, HomePage.class);
		explorePage = PageFactory.initElements(driver, ExplorePage.class);
	}
	
	@BeforeMethod
	public void loginToTwitter() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login(username, password);
		System.out.println(username + "logged into Twitter...");
	}
	
	@Test
	public void followTweeterHandleTest() {
		String expectedVal = "Following";
		System.out.println("Following @pappuyadavjapl");
		homePage.search("@pappuyadavjapl");
		String actualVal = explorePage.followPerson();
		Assert.assertEquals(actualVal, expectedVal);
		
	}

}
