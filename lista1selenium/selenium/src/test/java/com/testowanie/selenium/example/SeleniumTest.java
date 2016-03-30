package com.testowanie.selenium.example;

import junit.framework.Assert;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


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

    private static final String STORE_URL = "http://aliexpress.com/";
    private static final String STORE_SEARCH_ID = "search-key";
    private static final String STORE_SEARCH_PHRASE = "usb type-c cable";
    private static final String STORE_SEARCH_LISTS_RESULT_ID = "hs-list-items";
    private static final String STORE_SEARCH_FIRST_RESULT_SELECTOR = "ul > li:first-child .pic > a";
    private static final String STORE_ADD_TO_CART_BUTTON_ID = "add-to-cart";
    private static final String STORE_OPTIONS_SELECTOR = "#product-info-sku dd ul li:first-child a";
    private static final String STORE_AMOUNT_ID = "product-info-txt-quantity";
    private static final Integer STORE_AMOUNT_VALUE = 12;
    private static final String STORE_SHOW_CART =".ui-feedback-body a";
    private static final String STORE_AMOUNT_IN_CART_SELECTOR ="product-quantity-input\n";



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

    private void sleepFor(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            System.out.println("Thread.sleep() error");
        }
    }

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
        sleepFor(4000);
    }

    @Test
    public void zad2() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com/webhp?complete=1&hl=en");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Leonardo");
        element.submit();
        sleepFor(4000);
        WebElement table = driver.findElement(By.className("sbsb_b"));
        Assert.assertTrue(table.isDisplayed());

    }

    @Test
    public void zad3() {
        WebDriver driver = inputLogin("user", "password");
        WebElement successElement = driver.findElement(By.className("success"));
        Assert.assertNotNull(successElement);
    }

    @Test
    public void zad4IncorrectUsername() {
        WebDriver driver = inputLogin("foo", "password");
        WebElement failElement = driver.findElement(By.className("fail"));
        Assert.assertNotNull(failElement);
    }

    @Test
    public void zad4IncorrectPassword() {
        WebDriver driver = inputLogin("user", "bar");
        WebElement failElement = driver.findElement(By.className("fail"));
        Assert.assertNotNull(failElement);
    }

    @Test
    public void zad4IncorrectUsernameAndPassword() {
        WebDriver driver = inputLogin("foo", "bar");
        WebElement failElement = driver.findElement(By.className("fail"));
        Assert.assertNotNull(failElement);
    }

    @Test
    public void zad5addToCardSearchedItems() {
        WebDriver driver = new FirefoxDriver();
        driver.get(STORE_URL);

        WebElement searchInput = driver.findElement(By.id(STORE_SEARCH_ID));

        searchInput.sendKeys(STORE_SEARCH_PHRASE);
        searchInput.submit();

        sleepFor(4000);
        WebElement searchResults = driver.findElement(By.id(STORE_SEARCH_LISTS_RESULT_ID));
        driver.get(searchResults.findElement(By.cssSelector(STORE_SEARCH_FIRST_RESULT_SELECTOR)).getAttribute("href"));
        sleepFor(4000);

        WebElement amountInput = driver.findElement(By.id(STORE_AMOUNT_ID));
        WebElement addToCartButton = driver.findElement(By.id(STORE_ADD_TO_CART_BUTTON_ID));

        if (driver.findElements(By.cssSelector(STORE_OPTIONS_SELECTOR)).size() > 0) {
            List<WebElement> AllFirstOptions = driver.findElements(By.cssSelector(STORE_OPTIONS_SELECTOR));
            for (WebElement element : AllFirstOptions) {
                element.click();
            }
        }
        amountInput.clear();
        for (int i=0; i < STORE_AMOUNT_VALUE-1; i++){
            amountInput.sendKeys(Keys.ARROW_UP);
        }
        addToCartButton.click();

        sleepFor(4000);
        driver.get(driver.findElement(By.cssSelector(STORE_SHOW_CART)).getAttribute("href"));
        sleepFor(4000);

        WebElement amountOfOrderedItems = driver.findElement(By.className(STORE_AMOUNT_IN_CART_SELECTOR));
        Assert.assertTrue(amountOfOrderedItems.getAttribute("value").equals(STORE_AMOUNT_VALUE.toString()));
    }
}
