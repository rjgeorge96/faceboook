package com.stepdefinition;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import com.runner.FBRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FBStepDefinition extends FBRunner {

	public static ChromeDriver driver = FBRunner.driver;

	@Given("user Opens Facebook Page and Checks If Facebook Is Present In Title and URL")
	public void user_opens_facebook_page_and_checks_if_facebook_is_present_in_title_and_url() {
		driver.get("https://www.facebook.com/");
		String currentURL = driver.getCurrentUrl();
		String title = driver.getTitle();
		String page = "facebook";
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

	@When("user Logins With Invalid UserName and Password")
	public void user_logins_with_invalid_user_name_and_password() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("asdfgf;lkjhj");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("asdfgf;lkjhj");
		driver.findElement(By.xpath("//button[@name='login']")).click();
	}

	@Then("user Takes ScreenShot Of Error {string}")
	public void user_takes_screen_shot_of_error(String fileName) throws IOException, InterruptedException {
		Thread.sleep(3000);
		TakesScreenshot srcShot = (TakesScreenshot) driver;
		File src = srcShot.getScreenshotAs(OutputType.FILE);
		File des = new File(".//Screenshots//"+fileName+".png");
		FileHandler.copy(src, des);
	}

	@When("user Gets A Step Back and Clicks Create New Acount")
	public void user_gets_a_step_back_and_clicks_create_new_acount() throws InterruptedException {
		Thread.sleep(2000);
		driver.navigate().back();
		driver.findElement(By.xpath("//a[contains(@data-testid,\"open\")]")).click();
		Thread.sleep(3000);
	}

	@When("user Enters Invalid Details and Submits")
	public void user_enters_invalid_details_and_submits() {
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

}