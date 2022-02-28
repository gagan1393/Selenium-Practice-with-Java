package com.qa.SelenimSessions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HandleDropdown {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.orangehrm.com/orangehrm-30-day-trial/");

        By country = By.id("Form_submitForm_Country");
        selectDropDownValues(country, DropDown.INDEX.toString(), "10");
        Thread.sleep(3000);
        selectDropDownValues(country, DropDown.VALUE.toString(), "Cambodia");
        Thread.sleep(3000);
        selectDropDownValues(country, DropDown.VISIBLETEXT.toString(), "India");
        Thread.sleep(3000);
        driver.quit();
    }

    //Generic function for Handling dropdown

    public static WebElement getElement(By locator){
        return driver.findElement(locator);
    }

    public static void selectDropDownValues(By locator, String type, String value){

        Select select = new Select(getElement(locator));
        switch (type){
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "visibletext":
                select.selectByVisibleText(value);
                break;
            default:
                System.out.println("Please Pass the correct selection criteria...");
                break;
        }

    }
}
