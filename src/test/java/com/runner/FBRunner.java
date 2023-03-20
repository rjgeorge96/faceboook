package com.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src\\test\\java\\com\\feature\\facebook.feature",
glue = "com.stepdefinition",monochrome = true)

public class FBRunner {

	public static ChromeDriver driver= null;
	
	@BeforeClass
	public static void openChrome_Fb() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options );
	}
	
	@AfterClass
	public static void tearDown() {
//		driver.quit();
	}
	
	
}
