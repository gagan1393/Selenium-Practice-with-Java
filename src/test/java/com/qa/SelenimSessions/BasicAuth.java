package com.qa.SelenimSessions;


import io.github.bonigarcia.wdm.WebDriverManager;


import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.network.Network;
import org.openqa.selenium.devtools.v96.network.model.Headers;
import org.testng.Assert;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BasicAuth {

    private static final String UserName = "admin";
    private static final String Password = "admin";
    public static WebDriver driver = null;


    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //other way for pass Basic Auth
        //driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");


        getBasicAuthHandle(driver);
        driver.get("https://the-internet.herokuapp.com/basic_auth");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Thread.sleep(3000);
        String ExpectedResult = driver.findElement(By.xpath("//div[@class='example']")).getText();
        System.out.println(ExpectedResult);
        Assert.assertTrue(ExpectedResult.contains("Congratulations! You must have the proper credentials."));
        driver.quit();

    }

    public static void getBasicAuthHandle(WebDriver driver){

        DevTools devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        //send auth header
        Map<String, Object> header = new HashMap<>();
        String basecAuth = "Basic " + new String(new Base64().encode(String.format("%s:%s", UserName, Password).
                getBytes(StandardCharsets.UTF_8)));
        header.put("Authorization", basecAuth);
        devTools.send(Network.setExtraHTTPHeaders(new Headers(header)));

    }


}
