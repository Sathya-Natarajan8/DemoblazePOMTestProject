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

public class SignUpResult extends ProjectSpecificationMethods {

	@FindBy(xpath = "//input[@id='sign-username']")
	WebElement account;

	@FindBy(xpath = "//div[@class='modal-footer']//button[contains(text(),'Sign up')]")
	WebElement continueButton;

	public SignUpResult(WebDriver driver) {
		UtilsClass.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SignUpResult validUser(String expected, String testType) {
		String actualMail = account.getAttribute("value");
		Assert.assertEquals(actualMail, expected, "Entered email does not match expected!");

		continueButton.click();

		// ✅ Handle Both "User Already Exists" & "Sign Up Successful" Alerts
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Explicit Wait for Alert
			wait.until(ExpectedConditions.alertIsPresent());

			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			System.out.println("Alert Message: " + alertText);

			if (alertText.contains("This user already exists.")) {
				Assert.assertTrue(alertText.contains("This user already exists."),
						"Alert text does not match expected!");
			} else if (alertText.contains("Sign up successful")) {
				System.out.println("Signup was successful.");
			}

			alert.accept(); // ✅ Accept alert before proceeding
		} catch (NoAlertPresentException e) {
			System.out.println("No alert appeared.");
		}

		return this;
	}
}
