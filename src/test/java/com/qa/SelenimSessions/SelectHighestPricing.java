package com.qa.SelenimSessions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class SelectHighestPricing {
    static WebDriver driver = null;

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        List<WebElement> listPrice = driver.findElements(By.className("inventory_item_price"));

        double largest = 0;

        for (WebElement e: listPrice){
            double price = Double.parseDouble(e.getText().replace("$", ""));
            if(largest<price){
                largest=price;
            }
            System.out.println(largest);
            String xpath_min = "//div[normalize-space()='$"+largest+"']/following-sibling::button[text()='Add to cart']";
            driver.findElement(By.xpath(xpath_min)).click();

            driver.quit();
        }

    }
}
