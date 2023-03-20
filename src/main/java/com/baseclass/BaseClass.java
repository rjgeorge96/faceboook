package com.baseclass;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v107.webaudio.WebAudio;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver = null;
//	click	sendKeys	clear	isSelected	isEnabled	isDisplayed	getText	getAttribiute

	public static void clickElement(WebElement element) {
		element.click();
	}

	public static void sendText(WebElement element, String text) {
		element.sendKeys(text);
	}

	public static void eraseEntry(WebElement element) {
		element.clear();
	}

	public static boolean isElementPresent(WebElement element) {
		boolean displayed = element.isDisplayed();
		return displayed;
	}

	public static String textOfElement(WebElement element) {
		String text = element.getText();
		return text;
	}

	public static boolean isElementActive(WebElement element) {
		boolean active = element.isEnabled();
		return active;
	}

	public static boolean isElementSelected(WebElement element) {
		boolean selected = element.isSelected();
		return selected;
	}

	public static String getEnteredValue(WebElement element) {
		String attribute = element.getAttribute("value");
		return attribute;
	}

	public static String getPlaceholder(WebElement element) {
		String attribute = element.getAttribute("placeholder");
		return attribute;
	}

//	launchBrowser	get	getTitle	getCurrentUrl	back	forward	to	refresh	close	quit	

	public static ChromeOptions chromeOptions(List<String> opt) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments(opt);
		return options;
	}

	public static WebDriver launchBrowser(String browser) {
		switch (browser.toLowerCase()) {
		case "chrome":
		  	WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			System.err.println("Invalid Browser Name. Available browsers are:\nchrome,firefox,edge");
			break;
		}
		return driver;
	}

	public static void screenshot(String fileName) throws IOException {
		TakesScreenshot sc = (TakesScreenshot) driver;
		File source = sc.getScreenshotAs(OutputType.FILE);
		File destination = new File("./Facebook/Screenshots/"+fileName+".png");
		FileHandler.copy(source, destination);
	}
	

	public static void getUrl(String url) {
		driver.get(url);
	}

	public static String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public static String getTitle() {
		return driver.getTitle();
	}

	public static void back() {
		driver.navigate().back();
	}

	public static void forward() {
		driver.navigate().forward();
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	public static void close() {
		driver.close();
	}

	public static void quit() {
		driver.quit();
	}
}