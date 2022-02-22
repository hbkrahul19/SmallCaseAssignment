package com.scripts;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.pageObjects.Locator;

import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class Scenario1 {
	
	static String productName;	
	@Test
	public static WebDriver scenarioScript1() throws InterruptedException{
		
		WebDriver driver = new ChromeDriver();
		String windowHandle = driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		

		Locator.closePopup(driver).click();
		Locator.searchField(driver).sendKeys("Apple iPhone 13 128GB Midnight");
		Locator.searchButton(driver).click();
		
		 productName = Locator.productName(driver).getText();
		
		String parent = driver.getWindowHandle();
		Locator.firstProduct(driver).click();
		
		Set<String> allTabs = driver.getWindowHandles();
		
		//System.out.println(allTabs.size());
		
       for(String child:allTabs) {
    	   if(!parent.equalsIgnoreCase(child)) {
    		   driver.switchTo().window(child);
    		   Locator.pinCode(driver).sendKeys("800020");
    	       Locator.pinCheck(driver).click();
    	      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
    		 
    	       System.out.println(Locator.productPrice(driver).getText());
    	   
    	    
    	      
    	       WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    	       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='ADD TO CART']"))).click();
    	       Thread.sleep(1000);
    	       System.out.println(Locator.cartPrice(driver).getText());
    	   }
       }
       
       
		
	
	
	

//	public static void main(String[] args) {

	//	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		try {
			scenarioScript1();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	//	System.out.println("Hello");
//	}
		return driver;
	}
}
