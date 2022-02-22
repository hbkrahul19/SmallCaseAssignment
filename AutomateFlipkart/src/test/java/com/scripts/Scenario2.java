package com.scripts;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		
		
	//	Locator.amazonSearchField(driver).sendKeys(Scenario1.productName);
		Locator.amazonSearchField(driver).sendKeys("Apple iPhone 13 128GB Midnight");
		
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
		
		
		return driver;
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		
		Scenario1.scenarioScript1();
		scenarioScript2();
		
		System.out.println("Test complete");
	}

}
