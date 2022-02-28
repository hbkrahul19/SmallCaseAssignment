package com.scripts;

import java.util.Set;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.pageObjects.Locator;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario1 {
	
public  WebDriver driver;
public	String FProductPrice;
//	static String productName;	
	
	@BeforeClass
	public  void setup() throws Exception{
		WebDriverManager.chromedriver().setup();
	
	}
	
	
	@Test
	public void scenarioScript1() throws InterruptedException{
		
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		

		Locator.closePopup(driver).click();
		Locator.searchField(driver).sendKeys("Apple iPhone 13 Pro Max (256GB)");
		Locator.searchButton(driver).click();
		
		
		String parent = driver.getWindowHandle();
		Locator.firstProduct(driver).click();
		
		Set<String> allTabs = driver.getWindowHandles();
		
		
		//Switch to new tab
       for(String child:allTabs) {
    	   if(!parent.equalsIgnoreCase(child)) {
    		   driver.switchTo().window(child);
    		   
    		   Locator.pinCode(driver).sendKeys("800020");
    	       Locator.pinCheck(driver).click();
    	      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
    	      
    	       WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    	       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='ADD TO CART']"))).click();
    	       Thread.sleep(1000);
    	     FProductPrice = Locator.cartPrice(driver).getText();
    	       
    	       Reporter.log("Product Price on Flipkart: "+FProductPrice.replace("₹", "Rs "));
    			JavascriptExecutor jse = (JavascriptExecutor) driver;
    			   jse.executeScript("window.scrollBy(0,450)");
    			   
    			   //Increase quantity by1
    				try {
    					
    					wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'_3dY_ZR')]/child::button[2]"))).click();
    					
    					Thread.sleep(10000);
    					Reporter.log(("Final Product Price after addition of quantity: "+Locator.totalPrice(driver).getText().replace("₹", "Rs ")));
    				}
    				catch(NullPointerException e) {
    					Reporter.log("Quantity is not increasing or product is not in stock");
    				}
    				
    				driver.quit();		   
    	   }
       }
	}
	}
