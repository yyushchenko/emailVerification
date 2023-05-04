package caseStudy.gmail.page;

import caseStudy.tools.GeneralFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GmailPage extends GeneralFunctions {

    private final WebDriver driver;

    //Selectors
    private final By acceptCookiesBtn = By.cssSelector("a.cookieBarButton.cookieBarConsentButton");
    private final By signInBtn = By.cssSelector("a.button.button--medium.button--mobile-before-hero-only");
    private final By username = By.cssSelector("#identifierId");
    private final By password = By.xpath("//input[@type='password']");
    private final By nextBtn = By.cssSelector("#identifierNext");
    private final By nextBtnPass = By.cssSelector("#passwordNext");
    private final By composeEmailBtn = By.xpath("//div[@class='aic']/div/div[@role='button']");
    private final By recepientInput = By.xpath("//div[@class='aH9']/input");
    private final By subjectInput = By.xpath("//input[@name='subjectbox']");
    private final By bodyInput = By.xpath("//div[contains(@g_editable, 'true')]");
    private final By sendBtn = By.xpath("//div[@class='dC']/div[contains(@aria-label, 'Send')]");
    private final By rowsSelector = By.cssSelector("tr.zA.zE");
    private final By childEmailSelector = By.xpath(".//td/div/span[1]/span");
    private final By childSubjectSelector = By.xpath(".//td/div/div/div/span/span");
    private final By childBodySelector = By.xpath(".//td/div/div/span");
    private final String bodyPrefix = " - \n";


    public GmailPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginToEmailBox(String email, String pass) {
        clickBtn(acceptCookiesBtn);
        clickBtn(signInBtn);
        inputData(username, email);
        clickBtn(nextBtn);
        inputData(password, pass);
        clickBtn(nextBtnPass);
    }

    public void verifyLogin() {

        boolean login = driver.getCurrentUrl().contains("signin");
        if (login) {
            System.out.println("Login is successful");
        } else {
            System.out.println("Login with provided credentials failed");
        }

    }

    public void createEmail(String email, String subject, String body) {

        driverWait(20);
        clickBtn(composeEmailBtn);
        inputData(recepientInput, email);
        inputData(subjectInput, subject);
        inputData(bodyInput, body);
        clickBtn(sendBtn);

    }

    public boolean verifyEmailReceived(String email, String subject, String body) {

        List<WebElement> rowsList = driver.findElements(rowsSelector);

        for (WebElement elem : rowsList) {
            if (checkSingleRow(elem, email, subject, body)) {
                System.out.println("Email with given sender and subject is found");
                return true;
            }
        }
        System.out.println("Email with given email and subject doesn't exist");
        return false;
    }

    public boolean checkSingleRow(WebElement row, String email, String subject, String body) {
        WebElement emailCheck = row.findElement(childEmailSelector);

        String nextEmail = emailCheck.getAttribute("email");

        System.out.println("Looking for email from sender " + nextEmail);
        if (!nextEmail.equals(email)) {
            return false;
        }

        WebElement subjectCheck = row.findElement(childSubjectSelector);

        String nextSubject = subjectCheck.getText();

        System.out.println("Looking for email with the subject " + nextSubject);
        if (!nextSubject.equals(subject)) {
            return false;
        }

        WebElement bodyCheck = row.findElement(childBodySelector);

        String nextBody = bodyCheck.getText();

        System.out.println("Looking for email with the body " + nextBody);
        if (!nextBody.equals(bodyPrefix + body)) {
            return false;
        }

        return true;
    }


}
