package com.prestashop.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NegativeTestScenario {
	WebDriver dr ;
@BeforeClass

public void setup() {
	WebDriverManager.chromedriver().setup();
	dr = new ChromeDriver();
	dr.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
}
@Test
public void wrongCredentialsTest() {
	dr.get(" http://automationpractice.com");
	dr.findElement(By.xpath("//a[@class='login']")).click();
	dr.findElement(By.xpath("//input[@id='email']")).sendKeys("nargiza@gmail.com");
	dr.findElement(By.xpath("//input[@id='passwd']")).sendKeys("azigran");
	dr.findElement(By.xpath("//button[@id='SubmitLogin']/span")).click();

	WebElement authenticate = dr.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
	
	System.out.println(authenticate.getText());
	Assert.assertTrue(authenticate.isDisplayed());
}
	@Test
	public void invalidEmailTest() {
		dr.get(" http://automationpractice.com");
		dr.findElement(By.xpath("//a[@class='login']")).click();
		dr.findElement(By.xpath("//input[@id='email']")).sendKeys("gmail.nargiza");
		dr.findElement(By.xpath("//input[@id='passwd']")).sendKeys("azigran");
		dr.findElement(By.xpath("//button[@id='SubmitLogin']/span")).click();
		
		WebElement invalidFormat = dr.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		System.out.println(invalidFormat.getText());
		Assert.assertTrue(invalidFormat.isDisplayed());
	}
	@Test
	public void blankEmailTest(){
		dr.get(" http://automationpractice.com");
		dr.findElement(By.xpath("//a[@class='login']")).click();
		dr.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		dr.findElement(By.xpath("//input[@id='passwd']")).sendKeys("azigran");
		dr.findElement(By.xpath("//button[@id='SubmitLogin']/span")).click();
		
		WebElement emailRequired = dr.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
		System.out.println(emailRequired.getText());
		Assert.assertTrue(emailRequired.isDisplayed());
	
	}
	
	@Test
	public void blankPasswordTest() {
		dr.get(" http://automationpractice.com");
		dr.findElement(By.xpath("//a[@class='login']")).click();
		dr.findElement(By.xpath("//input[@id='email']")).sendKeys("nargiza@gmail.com");
		dr.findElement(By.xpath("//input[@id='passwd']")).sendKeys("");
		dr.findElement(By.xpath("//button[@id='SubmitLogin']/span")).click();
		
		WebElement passwdRequired = dr.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
	 System.out.println(passwdRequired.getText());
	 Assert.assertTrue(passwdRequired.isDisplayed());
	}
	@AfterClass
	public void done() {
		dr.quit();
	}

}
