package com.qa.SelenimSessions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalendarHandle {

    public static WebDriver driver = null;

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-handle-calendar-in-selenium.html");
        driver.findElement(By.id("datepicker")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ui-datepicker-calendar")));
        String monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
        System.out.println(monthYearValue);

        String month = monthYearValue.split(" ")[0].trim();
        String year = monthYearValue.split(" ")[1].trim();

        while (!(month.equals("June")) && year.equals("2024")){
            driver.findElement(By.xpath("//a[@title='Next']")).click();
            monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
            System.out.println(monthYearValue);

            month = monthYearValue.split(" ")[0].trim();
            year = monthYearValue.split(" ")[1].trim();

        }

        driver.findElement(By.xpath("//a[@text()='25']")).click();
        //getSelectDate("25", "December", "2022");


    }

    //Generic method
    public static String[] getMonthYear(String monthYearValue){
        return monthYearValue.split(" ");

    }

    public static void getSelectDate(String exDay, String exMonth, String exYear){

        String monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
        System.out.println(monthYearValue);

        while (!(getMonthYear(monthYearValue)[0].equals(exMonth) &&
                    getMonthYear(monthYearValue)[1].equals(exYear))){

            driver.findElement(By.xpath("//a[@title='Next']")).click();
            monthYearValue = driver.findElement(By.className("ui-datepicker-title")).getText();
        }
        driver.findElement(By.xpath("//a[@text()='"+exDay+"']")).click();
    }
}
