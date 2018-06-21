package com.prestashop.tests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PositiveTestScenarios {
	WebDriver positive;
	Faker fakeEmail;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		positive = new ChromeDriver();
		positive.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
	}

	@Test
	public void loginTest() {
		positive.get("http://automationpractice.com");
		positive.findElement(By.xpath("//a[@class='login']")).click();
		Random rn = new Random();
		fakeEmail = new Faker();
		int ranEmail = rn.nextInt(10);
		String email = "" + fakeEmail.ancient().primordial().toLowerCase() + ranEmail + "@gmail.com";
		System.out.println("random email is " + email);
		positive.findElement(By.xpath("//input[@id='email_create']")).sendKeys(email);
		System.out.println(email);
		positive.findElement(By.xpath("//i[@class='icon-user left']")).click();

		String win1 = positive.getWindowHandle();
		for (String win2 : positive.getWindowHandles()) {
			positive.switchTo().window(win2);
		}

		String customerName = "" + fakeEmail.ancient().hero().toLowerCase();
		System.out.println("customer name is: " + customerName);
		positive.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(customerName);

		String customerLastname = "" + fakeEmail.ancient().god().toLowerCase();
		positive.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(customerLastname);

		String custPasswd = "" + fakeEmail.ancient().hashCode();
		System.out.println("password is : " + custPasswd);
		positive.findElement(By.xpath("//input[@id='passwd']")).sendKeys(custPasswd);

		// positive.findElement(By.xpath("//input[@id='firstname']")).sendKeys(customerName);
		// positive.findElement(By.xpath("//input[@id='lastname']")).sendKeys(customerLastname);

		String address = "" + fakeEmail.address().streetAddress();
		System.out.println(address);
		positive.findElement(By.xpath("//input[@id='address1']")).sendKeys(address);

		int indexDay = fakeEmail.number().numberBetween(1, 31);
		WebElement dayClick = positive.findElement(By.xpath("//select[@id='days']"));
		dayClick.click();
		Select day = new Select(positive.findElement(By.xpath("//select[@id='days']")));
		day.selectByValue("" + indexDay);

		int indexMonth = fakeEmail.number().numberBetween(1, 12);
		WebElement monthClick = positive.findElement(By.xpath("//select[@id='months']"));
		monthClick.click();
		Select month = new Select(positive.findElement(By.xpath("//select[@id='months']")));
		month.selectByValue("" + indexMonth);

		WebElement yearClick = positive.findElement(By.xpath("//select[@id='years']"));
		yearClick.click();
		Select year = new Select(positive.findElement(By.xpath("//select[@id='years']")));
		int yearIndex = fakeEmail.number().numberBetween(1900, 2018);
		year.selectByValue("" + yearIndex);

		String city = "" + fakeEmail.address().city();
		positive.findElement(By.cssSelector("input#city")).sendKeys(city);

		int stateIndex = fakeEmail.number().numberBetween(1, 54);
		// String state ="" +fakeEmail.address().state();
		WebElement stateClick = positive.findElement(By.cssSelector("div#uniform-id_state"));
		stateClick.click();
		Select state = new Select(positive.findElement(By.cssSelector("select#id_state")));
		state.selectByValue("" + stateIndex);
		// zip
		String zipIndex = "" + fakeEmail.number().numberBetween(10000, 70000);

		WebElement zipClick = positive.findElement(By.cssSelector("input#postcode"));
		zipClick.sendKeys(zipIndex);

		positive.findElement(By.cssSelector("input#phone_mobile")).sendKeys("773-995-4543");

		 String alias ="" + fakeEmail.address().buildingNumber();
		positive.findElement(By.cssSelector("input#alias")).sendKeys(alias);;
		
		positive.findElement(By.cssSelector("[name='submitAccount']")).click();
		
		positive.findElement(By.cssSelector(".logout")).click();
		
		//logging back
		
		
		positive.findElement(By.cssSelector("input#email")).sendKeys(email);
		positive.findElement(By.cssSelector("input#passwd")).sendKeys(custPasswd);
		
		positive.findElement(By.cssSelector("button#SubmitLogin")).click();
		
		String name = positive.findElement(By.cssSelector(".account>span")).getText();
		
		Boolean check =(customerName +" "+ customerLastname).equals(name);
		Assert.assertTrue(check, name);
		
	}
}
