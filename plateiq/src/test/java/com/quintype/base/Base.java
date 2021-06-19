package com.quintype.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.quintype.pages.LoginPage;
import com.quintype.utils.ExcelReader;

public class Base {

	public WebDriver driver;
	public Properties CONFIG;
	protected ExcelReader excelreader;
	protected String username;
	protected String password;

	@BeforeTest
	public void init() throws IOException {
		excelreader = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\TestCases.xlsx");
		CONFIG = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
		CONFIG.load(fis);
		System.out.println(System.getProperty("user.dir"));
		
	}

	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser) {
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if(browser.equalsIgnoreCase("chrome"))
		{
//			System.setProperty("webdriver.chrome.driver", "./plateiq/src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		username = CONFIG.getProperty("username");
		password = CONFIG.getProperty("password");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		driver.get(CONFIG.getProperty("baseurl"));
		System.out.println("Webpage Title - " + driver.getTitle());
		
	}


	
/*	@AfterClass()
	public void teardown() {
		driver.quit();
	}*/

}
