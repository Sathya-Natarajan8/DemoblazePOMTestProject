package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class SignUpPage extends ProjectSpecificationMethods {

    @FindBy(id = "sign-username")
    WebElement userName;

    @FindBy(id = "sign-password")
    WebElement password;

	@FindBy(xpath = "//div[@class='modal-footer']//button[contains(text(),'Sign up')]")
	WebElement signUpButton;

	public SignUpPage(WebDriver driver) {
		UtilsClass.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SignUpPage Username(String User) {

		userName.sendKeys(User);
		return this;
	}

	public SignUpPage password(String pass) {

		password.sendKeys(pass);
		return this;
	}

	public SignUpResult clickSignUp() {
		signUpButton.click();
		return new SignUpResult(driver);
	}

}
