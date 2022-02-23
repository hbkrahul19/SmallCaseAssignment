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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.pageObjects.Locator;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario1 {
	
	WebDriver driver;
//	static String productName;	
	
	@BeforeClass
	public  void setup() throws Exception{
		WebDriverManager.chromedriver().setup();
	//	driver = new ChromeDriver();
	}
	
	
	@Test
	public void scenarioScript1() throws InterruptedException{
		
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		

		Locator.closePopup(driver).click();
		Locator.searchField(driver).sendKeys("Apple iPhone 13 Pro Max (256GB) - Gold");
		Locator.searchButton(driver).click();
		
	//	 productName = Locator.productName(driver).getText();
		
		String parent = driver.getWindowHandle();
		Locator.firstProduct(driver).click();
		
		Set<String> allTabs = driver.getWindowHandles();
		
		
       for(String child:allTabs) {
    	   if(!parent.equalsIgnoreCase(child)) {
    		   driver.switchTo().window(child);
    		   Locator.pinCode(driver).sendKeys("800020");
    	       Locator.pinCheck(driver).click();
    	      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
    		 
    	     //  System.out.println(Locator.productPrice(driver).getText());
    	      Reporter.log(Locator.productPrice(driver).getText());
    	    
    	      
    	       WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    	       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='ADD TO CART']"))).click();
    	       Thread.sleep(1000);
    	       Reporter.log("Product Price: "+Locator.cartPrice(driver).getText());
    			JavascriptExecutor jse = (JavascriptExecutor) driver;
    			   jse.executeScript("window.scrollBy(0,500)");
    	   }
       }
	}
	@Test
	public void increaseProductPrice() throws InterruptedException{
		Locator.increaseQuantity(driver).click();
		Reporter.log("Final Product Price (Quantity= 2)"+Locator.totalPrice(driver).getText());
	}
	
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
}
	}
