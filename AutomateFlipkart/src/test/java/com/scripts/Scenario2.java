package com.scripts;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pageObjects.Locator;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Scenario2 {
	
	Scenario1 fp = new Scenario1();
	WebDriver driver;
	public String APrice;
	
	
	@BeforeClass
	public  void setup() throws Exception{
		System.out.println("Launching chrome browser");
		WebDriverManager.chromedriver().setup();
	}
	//FLipkart scenario execution
	@Test
	public void flipkartProduct() throws InterruptedException {
		fp.scenarioScript1();
	}
	
	//Amazon scenario execution
	@Test
	public  void scenarioScript2() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		
	    Locator.amazonSearchField(driver).sendKeys("Apple iPhone 13 Pro Max (256GB)");
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='nav-search-submit-button']"))).click();
		
	    String parent = driver.getWindowHandle();
	    Locator.amazonFirstProduct(driver).click();
	    
	    Set<String> allTabs = driver.getWindowHandles();
	    
	    //Switch to new tab
	    for(String child:allTabs) {
	    	   if(!parent.equalsIgnoreCase(child)) {
	    		   driver.switchTo().window(child);
	    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
	    		  // System.out.println(driver.findElement(By.xpath("//span[@id='productTitle']")).getText());
	    		   Reporter.log("Product Name: "+Locator.amazonProductName(driver).getText());
	    		 
	    		   APrice = Locator.amazonPrice(driver).getText();
	    		   Reporter.log(APrice.replaceAll(",", "").replace(".00", ""));
	    		   
	    		   //Click on Add to Cart button
	    		   JavascriptExecutor jse = (JavascriptExecutor) driver;
	    		   jse.executeScript("window.scrollBy(0,300)");	   
	    		   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='add-to-cart-button']"))).click();
	    		  
	    		   //If skip button display
	    		   if(Locator.skipButton(driver).isDisplayed()) {
	    	  wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-labelledby='attachSiNoCoverage-announce']"))).click();
	    		   
	    	  wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//a[normalize-space()='Go to Cart']"))).click();
	    	  Reporter.log("Product Price on Amazon: "+Locator.amazonProductPrice(driver).getText().replace("₹", "Rs "));
	    		  
	    		  //If only cart button display
	    		   }else if(Locator.amazonCartButton(driver).isDisplayed()) {
	    	   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"attach-sidesheet-view-cart-button\"]/span/input"))).click();
	    	   Reporter.log("Product Price on Amazon: "+Locator.amazonProductPrice(driver).getText().replace("₹", "Rs "));
	    		   }
	    		   
	    		   //When Go cart shows
	    		   else {
	    			   wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//a[normalize-space()='Go to Cart']"))).click();
	    			   Reporter.log("Product Price on Amazon: "+Locator.amazonProductPrice(driver).getText().replace("₹", "Rs "));
	    		   }
	    	   }}}
	
	   @Test
		public void priceComparison() {
	    	
	    	
	    	String FPrice = fp.FProductPrice.replace("₹", "").replace(".00", "").replaceAll(",", "");
	    	Reporter.log(FPrice);
	    	
	    /*	//Price is same
	    	if(Long.parseLong(FPrice)==Long.parseLong(APrice)) {
	    		Reporter.log("Product price is same on Flipkart & Amazon: "+APrice);
	    	}else
	    		
	    		//Fipkart product price is more than Amazon
	    		if(Long.parseLong(FPrice)>Long.parseLong(APrice)) {
	    		Reporter.log("Product price on Amazon lesser than Flipkart: "+"Amazon= "+APrice+" & "+"Flipkart= "+FPrice);
	    	}else
	    		
	    		//Amazon product price is more than Flipkart
	    		if(Long.parseLong(FPrice)<Long.parseLong(APrice)) {
	    		Reporter.log("Product price on Flipkart lesser than Amazon: "+"Amazon= "+APrice+" & "+"Flipkart= "+FPrice);
	    	} */
		}

		@AfterClass
		public void tearDown() {
			if(driver!=null) {
				driver.quit();
			}

	}
	

}
