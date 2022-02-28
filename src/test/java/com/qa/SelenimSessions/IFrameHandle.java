package com.qa.SelenimSessions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class IFrameHandle {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://selectorshub.com/xpath-practice-page/");
        driver.manage().window().maximize();
        driver.switchTo().frame("pact");
        /*
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement input = (WebElement) (jse.executeScript("return document.querySelector(\"#snacktime\").shadowRoot.querySelector(\"#tea\")"));
        WebElement input2 = (WebElement) (jse.executeScript("return document.querySelector(\"#snacktime\").shadowRoot.querySelector(\"#app2\").shadowRoot.querySelector(\"#pizza\")"));

        String js = "arguments[0].setAttribute('value', 'Masala ginger Tea')";
        String js2 = "arguments[0].setAttribute('value', 'Double cheese Burger')";
        ((JavascriptExecutor)driver).executeScript(js, input);
        ((JavascriptExecutor)driver).executeScript(js2, input2);

         */
        getIFrameElement(driver,"return document.querySelector(\"#snacktime\").shadowRoot.querySelector(\"#tea\")",
                "arguments[0].setAttribute('value', 'Masala ginger Tea')");

        Thread.sleep(3000);
        getIFrameElement(driver,"return document.querySelector(\"#snacktime\").shadowRoot.querySelector(\"#app2\").shadowRoot.querySelector(\"#pizza\")",
                "arguments[0].setAttribute('value', 'Double cheese Burger')");

        Thread.sleep(3000);
        driver.quit();
    }

    public static void getIFrameElement(WebDriver driver, String inputvalue, String value){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        WebElement input = (WebElement) (jse.executeScript(inputvalue));
        ((JavascriptExecutor)driver).executeScript(value, input);
    }
}
