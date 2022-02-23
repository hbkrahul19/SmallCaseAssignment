package com.scripts;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pageObjects.Locator;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Scenario2 {
	
	
	WebDriver driver;
	@BeforeClass
	public  void setup() throws Exception{
		System.out.println("Launching chrome browser");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
	}
	
	@Test
	public void flipkartProduct() throws InterruptedException {
		Scenario1 fp = new Scenario1();
		fp.scenarioScript1();
	}
	@Test
	public  void scenarioScript2() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
	    Locator.amazonSearchField(driver).sendKeys("Apple iPhone 13 Pro Max (256GB) - Gold");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='nav-search-submit-button']"))).click();
		
	    String parent = driver.getWindowHandle();
	    Locator.amazonFirstProduct(driver).click();
	    
	    Set<String> allTabs = driver.getWindowHandles();
	    
	    for(String child:allTabs) {
	    	   if(!parent.equalsIgnoreCase(child)) {
	    		   driver.switchTo().window(child);
	    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
	    		   System.out.println(driver.findElement(By.xpath("//span[@id='productTitle']")).getText());
	    		   
	    		   JavascriptExecutor jse = (JavascriptExecutor) driver;
	    		   jse.executeScript("window.scrollBy(0,300)");
	    		 //  jse.executeScript("arguments[0].scrollIntoView();", Locator.amazonAddToCartButton(driver));	   
	    		   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='add-to-cart-button']"))).click();
	    		   Locator.skipButton(driver).click();
	    		   Locator.amazonGoToCart(driver).click();
	    		  System.out.println(Locator.amazonProductPrice(driver).getText());
	    		   
	    	   }}

	}
	

}
