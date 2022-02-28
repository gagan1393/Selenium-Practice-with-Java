package com.qa.SelenimSessions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class FindBrokenLinks {

    static WebDriver driver;

    public static void main(String[] args) {

        ChromeOptions chromeoption = new ChromeOptions();
        chromeoption.addArguments("headless");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeoption);
        driver.get("https://www.amazon.in/");

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("The Total links present on the page are :" + links.size());

        List<String> urllist = new ArrayList<String>();

        for (WebElement e : links){
            String url = e.getAttribute("href");
            urllist.add(url);
        }
        long startTime = System.currentTimeMillis();
        urllist.parallelStream().forEach(e -> getBrokenLinks(e));
        long endTime = System.currentTimeMillis();

        System.out.println("The Total Time Taken is :" + (endTime-startTime));
        driver.quit();
    }

    public static void getBrokenLinks(String linkurl){

        try {
           URL url = new URL(linkurl);
           HttpURLConnection httpurlconnection = (HttpURLConnection) url.openConnection();
           httpurlconnection.setConnectTimeout(5000);
           httpurlconnection.connect();

           if (httpurlconnection.getResponseCode() >= 400){
               System.out.println(linkurl + "--->" + httpurlconnection.getResponseMessage() + " :is a broken Link");
           }else {
               System.out.println(linkurl + "--->" + httpurlconnection.getResponseMessage());
           }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
