package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class LoginPage extends ProjectSpecificationMethods {

    @FindBy(xpath = "//input[@id='loginusername']")
    WebElement userName;

    @FindBy(xpath = "//input[@id='loginpassword']")
    WebElement password;

    @FindBy(xpath = "//div[@class='modal-footer']//button[contains(text(),'Log in')]")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        UtilsClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage verifyLoginButtonVisible() {
        Assert.assertTrue(loginButton.isDisplayed(), " 'Log in' button is NOT visible on the homepage!");
        System.out.println(" 'Log in' button is visible.");
        return this;
    }

    public LoginPage verifyLoginButtonClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        System.out.println("'Log in' button is clickable.");
        return this;
    }

    public LoginPage enterUsername(String username) {
        userName.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        password.sendKeys(pass);
        return this;
    }

    public LoginResult clickLogin() {
        loginButton.click();
        return new LoginResult(driver);
    }
}

