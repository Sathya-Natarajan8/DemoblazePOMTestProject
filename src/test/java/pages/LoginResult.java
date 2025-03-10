package pages;

import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class LoginResult extends ProjectSpecificationMethods {

    @FindBy(xpath = "//a[@id='nameofuser']")
    WebElement nameOfUser;

    public LoginResult(WebDriver driver) {
        UtilsClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Validate successful login by checking if username is displayed
    public LoginResult validateSuccessfulLogin(String expectedUsername) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(nameOfUser));

        String actualText = nameOfUser.getText();
        Assert.assertTrue(actualText.contains(expectedUsername), "Login failed! Expected username not found.");
        System.out.println("Login successful! User: " + actualText);

        return this;
    }

    // Handle login failure alerts (Invalid username/password)
    public LoginResult handleLoginAlerts() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());

            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Message: " + alertText);

            Assert.assertTrue(alertText.contains("Wrong password.") || alertText.contains("User does not exist."),
                    "Unexpected alert message!");

            alert.accept();
            System.out.println("Alert dismissed successfully.");
        } catch (NoAlertPresentException e) {
            System.out.println("No login alert appeared. Login likely successful.");
        }

        return this;
    }

    public LoginResult verifyLogin(String expectedMessage, String testType) {
        if (testType.equalsIgnoreCase("Positive")) {
            validateSuccessfulLogin(expectedMessage);
        } else {
            handleLoginAlerts();
        }
        return this;
    }
}
