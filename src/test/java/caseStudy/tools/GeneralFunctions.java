package caseStudy.tools;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;

public class GeneralFunctions {

    private static WebDriver driver;
    final int maxTime = 20;

    public static WebDriver setDriver() {

        Properties appProperties = urlAndCredsProvider.loadProperties();

        System.out.println("Initializing a driver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-popup-blocking");
        options.addArguments("start-maximized");
        System.setProperty("webdriver.chrome.driver", appProperties.getProperty("driverLocation"));

        driver = new ChromeDriver(options);

        return driver;
    }

    public void clickBtn(By selector) {
        System.out.println("Clicking button by selector " + selector.toString());
        WebElement button = getElement(selector, maxTime);
        button.click();
    }

    public WebElement getElement(By selector, int maxTime) {
        return new WebDriverWait(driver, Duration.ofSeconds(maxTime)).until(ExpectedConditions.elementToBeClickable(selector));
    }

    public void inputData(By selector, String input) {
        System.out.println("Inserting string " + input + " in the location " + selector.toString());
        WebElement field = getElement(selector, 5);
        field.sendKeys(input);
    }

    public String getCurrentDateTime() {

        Calendar calendar = Calendar.getInstance();
        String pattern = "yyyy-MM-dd'T'h:m:ss.SZ";
        DateFormat dateFormat = new SimpleDateFormat(pattern);

        return dateFormat.format(calendar.getTime());
    }

    public String generateRandomString() {
        boolean useLetters = true;
        boolean useNumbers = false;

        return RandomStringUtils.random(12, useLetters, useNumbers);
    }

    public void driverWait(int milliseconds) {
        System.out.println("Driver waits for " + milliseconds + " ms");
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {}
    }

    public static void generateTestReport(String file){
        File report = new File(file);
        try{
            PrintStream printStream = new PrintStream(report);
            System.setOut(printStream);
        }catch (IOException e) {
            System.out.println("Failed to write to file");
        }


    }
}

