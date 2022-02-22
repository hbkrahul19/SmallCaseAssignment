package com.scripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageObjects.Locator;

public class Scenario2 {

	
	public static WebDriver scenarioScript2() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		
		Locator.amazonSearchField(driver).sendKeys(Scenario1.productName);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='nav-search-submit-button']"))).click();
		
		
		
		return driver;
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		
		Scenario1.scenarioScript1();
		scenarioScript2();
		
		System.out.println("Test complete");
	}

}
