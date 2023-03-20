package com.testng.facebook;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseclass.BaseClass;


public class FacebookTestNG extends BaseClass {

	static String page = "Facebook";
	static String currentURL;
	static String title;
	public static WebDriver driver = null;

	@BeforeClass
	@Parameters ("browser")
	public static void openChrome_Fb(String browser) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		driver = launchBrowser(browser);
		driver.get("https://www.facebook.com/");
	}

	@Test(dependsOnMethods = "loggingIn")
	public static void screenShot() throws InterruptedException, IOException {
		Thread.sleep(3000);
		TakesScreenshot srcShot = (TakesScreenshot) driver;
		File src = srcShot.getScreenshotAs(OutputType.FILE);
		File des = new File(".//Screenshots//FBLoginErrorTestNG.png");
		FileHandler.copy(src, des);
	}

	@Test
	public static void titleUrlCheck() {
		currentURL = driver.getCurrentUrl();
		title = driver.getTitle();
		System.out.println("\nCurrent URL: " + currentURL + "\nTitle: " + title);

		if (title.toLowerCase().contains(page.toLowerCase())) {
			System.out.println("\n" + page + " is present in " + title);
		} else {
			System.err.println("\n" + page + " is not present in " + title);
		}

		if (currentURL.toLowerCase().contains(page.toLowerCase())) {
			System.out.println("\n" + page + " is present in " + currentURL);
		} else {
			System.err.println("\n" + page + " is not present in " + currentURL + "\n");
		}
	}

	@Test(dependsOnMethods = "titleUrlCheck")
	public static void loggingIn() throws InterruptedException, IOException {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("asdfgf;lkjhj");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("asdfgf;lkjhj");
		driver.findElement(By.xpath("//button[@name='login']")).click();
	}

	@Test(priority = 1, dependsOnMethods = "loggingIn")
	public static void createAcc() throws InterruptedException {

		Thread.sleep(2000);
		driver.navigate().back();
		driver.findElement(By.xpath("//a[contains(@data-testid,\"open\")]")).click();
		Thread.sleep(3000);

		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("asdfgf");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("asdfgf");
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("asdfgf@';lkjhj");
		driver.findElement(By.xpath("//input[@name='reg_passwd__']")).sendKeys("asdfgf;lkjhj");
		Select date = new Select(driver.findElement(By.xpath("id('day')")));
		date.selectByVisibleText("1");
		Select month = new Select(driver.findElement(By.xpath("id('month')")));
		month.selectByVisibleText("Jan");
		Select year = new Select(driver.findElement(By.xpath("id('year')")));
		year.selectByVisibleText("1996");
		driver.findElement(By.xpath("//input[@value='2']")).click();
		driver.findElement(By.name("websubmit")).click();
	}

	@Test(priority = 1, dependsOnMethods = "createAcc")
	public static void screenShot_CreateNewAccErr() throws InterruptedException, IOException {
		Thread.sleep(3000);
		TakesScreenshot srcShot = (TakesScreenshot) driver;
		File src = srcShot.getScreenshotAs(OutputType.FILE);
		File des = new File(".//Screenshots//FBCreateNewAccErrorTestNG.png");
		FileHandler.copy(src, des);
	}

}
