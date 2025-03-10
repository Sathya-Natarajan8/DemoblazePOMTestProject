package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import base.ProjectSpecificationMethods;

public class LogoutPage extends ProjectSpecificationMethods {

    @FindBy(id = "logout2") // Adjust ID based on actual site
    WebElement logoutButton;

    public LogoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public LogoutPage verifyLogoutButtonVisible() {
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button not visible!");
        return this;
    }

    public LogoutPage clickLogout() {
        logoutButton.click();
        return this;
    }

    public LogoutPage verifyRedirectToHomePage() {
        String expectedUrl = "https://www.demoblaze.com";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Logout redirection failed!");
        return this;
    }
}

