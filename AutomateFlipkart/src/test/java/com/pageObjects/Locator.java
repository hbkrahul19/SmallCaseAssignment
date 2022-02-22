package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Locator {

	
	public static WebElement closePopup(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > div._2Sn47c > div > div > button"));
	}
	
	public static WebElement searchField(WebDriver driver) {
		return driver.findElement(By.cssSelector("#container > div > div._1kfTjk > div._1rH5Jn > div._2Xfa2_ > div._1cmsER > form > div > div > input"));
	}
	
	public static WebElement searchButton(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/button"));
	}

	public static WebElement firstProduct(WebDriver driver) {
	//	return driver.findElement(By.xpath("(//div[normalize-space()='Vu Premium 108 cm (43 inch) Ultra HD (4K) LED Smart Android TV'])[1]"));
		return driver.findElement(By.xpath("(//div[@class='fMghEO'])[1]"));
	}
	public static WebElement pinCode(WebDriver driver) {
	
		//	return driver.findElement(By.cssSelector("#pincodeInputId"));
	return driver.findElement(By.xpath("//input[@id='pincodeInputId']"));
	}
	
	public static WebElement pinCheck(WebDriver driver) {
		
		return driver.findElement(By.xpath("(//span[@class='_2P_LDn'])[1]"));
	}
	
	
	public static WebElement productPrice(WebDriver driver) {
		
		return driver.findElement(By.xpath("//div[@class='_30jeq3 _16Jk6d']"));
	}
	
	public static WebElement cartPrice(WebDriver driver) {
		
		return driver.findElement(By.cssSelector("div[class='Ob17DV _3X7Jj1'] span"));
	}
	
	
	public static WebElement productName(WebDriver driver) {
		return driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)"));
	}
	
	
	public static WebElement amazonSearchField(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
	}
	
	public static WebElement amazonSearchButton(WebDriver driver) {
		return driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
	}
	
	public static WebElement test(WebDriver driver) {
		return driver.findElement(By.cssSelector("._2KpZ6l._2U9uOA._3v1-ww"));
	}
	
	
}
