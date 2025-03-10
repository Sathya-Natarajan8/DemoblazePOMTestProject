package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;

import java.time.Duration;

public class PurchasePage extends  ProjectSpecificationMethods{
    WebDriver driver;

    @FindBy(xpath = "//input[@id='name']/preceding-sibling::label")
    WebElement nameField;

    @FindBy(xpath= "//label[@for='card']")
    WebElement cardField;

    @FindBy(xpath="//label[@for='country']")
    WebElement countryField;

    @FindBy(xpath = "//button[text()='Purchase']")
    WebElement purchaseButton;

    @FindBy(xpath = "//h2[contains(text(),'Thank you for your purchase!')]")
    WebElement confirmationMessage;

    public PurchasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterPurchaseDetails(String name, String card, String country) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ensure fields are visible and clickable before entering text
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).click();
        nameField.clear();
        nameField.sendKeys(name);

        wait.until(ExpectedConditions.elementToBeClickable(cardField)).click();
        cardField.clear();
        cardField.sendKeys(card);

        wait.until(ExpectedConditions.elementToBeClickable(countryField)).click();
        countryField.clear();
        countryField.sendKeys(country);
    }

    public void confirmPurchase() {
        purchaseButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ✅ Wait for confirmation message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")));

        // ✅ Click the "OK" button to close the modal
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));
        okButton.click();

        // ✅ Wait until modal disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("orderModal"))); // Ensure modal is fully closed
    }


    public boolean isConfirmationDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(confirmationMessage)).isDisplayed();
    }
}