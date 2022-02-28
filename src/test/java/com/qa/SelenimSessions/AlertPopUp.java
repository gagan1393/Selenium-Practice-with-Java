package com.qa.SelenimSessions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class AlertPopUp {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeoptions = new ChromeOptions();
        chromeoptions.setExperimentalOption("excludeSwitches",
                Arrays.asList("disable-popup-blocking"));
        WebDriver driver = new ChromeDriver(chromeoptions);
        driver.get("https://www.naukri.com/");

    }
}
