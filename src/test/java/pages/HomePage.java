package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class HomePage extends ProjectSpecificationMethods {

	@FindBy(xpath = "//a[@id='signin2']")
	WebElement signUpLink;

	@FindBy(xpath = "//a[contains(text(),'Log in')]")
	WebElement loginLink;

	@FindBy(xpath = "//a[contains(text(),'Cart')]")
	WebElement cartLink;

	public HomePage(WebDriver driver) {

		UtilsClass.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SignUpPage clickSignUp() {

		signUpLink.click();
		return new SignUpPage(driver);
	}

	public LoginPage clickLogin() {
		loginLink.click();
		return new LoginPage(driver);
	}

	public ProductPage selectProduct(String productName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement product = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@class='hrefch'][contains(text(), '" + productName + "')]")));
		product.click();
		return new ProductPage(driver);
	}

	public CartPage navigateToCart() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // ✅ Ensure the purchase form is closed before clicking "Cart"
	    try {
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("orderModal"))); // Modify ID if needed
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("name"))); // Ensure input field is not blocking
	    } catch (Exception e) {
	        System.out.println("No modal or input field blocking cart.");
	    }

	    WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(cartLink));
	    cartButton.click();

	    return new CartPage(driver);
	}


	public void addItemToCart(String productName) {
		selectProduct(productName);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addToCartButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']")));
		addToCartButton.click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		driver.navigate().back();
	}
    public LogoutPage clickLogout() {
        return new LogoutPage(driver);
    }
}

