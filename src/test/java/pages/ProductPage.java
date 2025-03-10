package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class ProductPage extends ProjectSpecificationMethods {

    @FindBy(xpath = "//a[@class='hrefch' and text()='Samsung galaxy s6']")
    WebElement productTitle;

    @FindBy(xpath = "//p[@id='article']")
    WebElement productDescription;

    @FindBy(xpath = "//h5[contains(text(), '$360')")
    WebElement productPrice;

    @FindBy(xpath = "//a[contains(@onclick, 'addToCart')]")
    WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        UtilsClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductPage verifyProductDetails(String expectedTitle, String expectedDescription, String expectedPrice) {
        Assert.assertTrue(productTitle.isDisplayed(), "Product title is not displayed!");
        Assert.assertEquals(productTitle.getText(), expectedTitle, "Product title does not match!");

        Assert.assertTrue(productDescription.isDisplayed(), "Product description is not displayed!");
        Assert.assertTrue(productDescription.getText().contains(expectedDescription), "Product description does not match!");

        Assert.assertTrue(productPrice.isDisplayed(), "Product price is not displayed!");
        Assert.assertTrue(productPrice.getText().contains(expectedPrice), "Product price does not match!");

        System.out.println("Product details are verified successfully.");
        return this;
    }
    
    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@onclick, 'addToCart')]")));
        addToCartButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert Message: " + alert.getText()); // Optional: Print alert text
        alert.accept(); // Accept the alert
    }
}
