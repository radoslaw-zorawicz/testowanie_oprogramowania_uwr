package com.testowanie.selenium.example;

import junit.framework.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumTest {
 

    /**
     * Przykładowa metoda testująca.
     */
    
    @Test
    public void exampleTest() {
        WebDriver driver = new HtmlUnitDriver();

        // Wchodzimy na strone google
        driver.get("http://www.google.com");

        // Znajdujemy element na stronie po jego id
        WebElement element = driver.findElement(By.name("q"));
        
        // Wpisujemy dane do znalezionego elementu
        element.sendKeys("Hello!");

        // Submitujemy formularz, Selenium samo znajdzie formularz do jakiego
        // należy nasz element
        element.submit();

        String title = driver.getTitle();
        // Tytuł strony
        System.out.println("Tytuł: " + title);

        driver.quit();
        
        Assert.assertNotNull(title);
    }
    
    /**
     * Miejsce na rozwiązania zadań.
     */
    

    @Test
    public void zad1() {
    	WebDriver driver = new HtmlUnitDriver();
    	driver.get("http://www.yahoo.com");
    	WebElement element = driver.findElement(By.name("p"));
    	element.sendKeys("wikipedia");
    	element.submit();
    	String title = driver.getTitle();
        System.out.println("Tytuł: " + title);
        Assert.assertNotNull(title);

        driver.quit();
    }
    
    @Test
    public void zad2()  {
    	WebDriver driver = new FirefoxDriver();
    	driver.get("http://www.google.com/webhp?complete=1&hl=en");
    	WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Leonardo");
        element.submit();
        try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			System.out.println("Thread.sleep() error"); 
		}
        WebElement table= driver.findElement(By.className("sbsb_b"));
        Assert.assertTrue(table.isDisplayed());
    	
    }
    
    public WebDriver inputLogin(String login, String password) {
    	WebDriver driver = new FirefoxDriver();
    	String currentDirPath = System.getProperty("user.dir");
    	driver.get("file:///" + currentDirPath + "/web/login.html");
    	WebElement loginInputElement = driver.findElement(By.name("username"));
    	loginInputElement.sendKeys(login);
    	WebElement passwordInputElement = driver.findElement(By.name("password"));
    	passwordInputElement.sendKeys(password);
    	WebElement buttonElement = driver.findElement(By.name("Zaloguj"));
    	buttonElement.click();
    	return driver;
    }
    
    @Test
    public void zad3() {
    	WebDriver driver =  inputLogin("user", "password");
    	WebElement successElement = driver.findElement(By.className("success"));
    	Assert.assertNotNull(successElement);
    }
    
    @Test
    public void zad4IncorrectUsername() {
    	WebDriver driver =  inputLogin("foo", "password");
    	WebElement failElemet = driver.findElement(By.className("fail"));
    	Assert.assertNotNull(failElemet);
    }
    
    @Test
    public void zad4IncorrectPassword() {
    	WebDriver driver =  inputLogin("user", "bar");
    	WebElement failElemet = driver.findElement(By.className("fail"));
    	Assert.assertNotNull(failElemet);
    }
    
    @Test
    public void zad4IncorrectUsernameAndPassword() {
    	WebDriver driver =  inputLogin("foo", "bar");
    	WebElement failElemet = driver.findElement(By.className("fail"));
    	Assert.assertNotNull(failElemet);
    }
}
