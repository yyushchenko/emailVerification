package caseStudy;

import caseStudy.gmail.page.GmailPage;
import caseStudy.tools.GeneralFunctions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static caseStudy.tools.GeneralFunctions.*;

public class EmailTest {

    private WebDriver driver;

    @BeforeTest
    public void init() {
        driver = setDriver();
    }
    @Test
    @Parameters({"email", "password", "baseUrl", "reportFile"})
    public void emailTest(String email, String password, String baseUrl, String reportFile){

        generateTestReport(reportFile);
        System.out.println("Starting a new Email test");
        GeneralFunctions general = new GeneralFunctions();
        String subject = general.getCurrentDateTime();

        driver.get(baseUrl);
        GmailPage gmailPage = new GmailPage(driver);
        gmailPage.loginToEmailBox(email, password);
        gmailPage.verifyLogin();
        gmailPage.createEmail(email, subject);

        general.driverWait(2000);

        Assert.assertTrue(gmailPage.verifyEmailReceived(email, subject));
    }
    @AfterTest
    public void tearDown(){

        System.out.println("Closing browser and driver");
        driver.close();
        driver.quit();
    }
}
