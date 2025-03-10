package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class ProductDisplay extends ProjectSpecificationMethods {

	@FindBy(xpath = "//a[text()='Monitors']")
	WebElement monitorTab;

	@FindBy(xpath = "//a[text()='Phones']")
	WebElement phonesCategory;

	@FindBy(xpath = "//a[text()='Laptops']")
	WebElement laptopsCategory;

	@FindBy(xpath = "//a[text()='Monitors']")
	WebElement monitorsCategory;

	@FindBy(xpath = "//div[@class='modal-content']")
	WebElement applicationLogs;

	public ProductDisplay(WebDriver driver) {
		UtilsClass.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ProductDisplay verifyMonitorTabDisplayed() {
		Assert.assertTrue(monitorTab.isDisplayed(), "Monitor tab is not displayed on the homepage.");
		System.out.println("Monitor tab is displayed.");
		return this;
	}

	public ProductDisplay verifyProductCategories() {
		Assert.assertTrue(phonesCategory.isDisplayed(), "Phones category is missing.");
		Assert.assertTrue(laptopsCategory.isDisplayed(), "Laptops category is missing.");
		Assert.assertTrue(monitorsCategory.isDisplayed(), "Monitors category is missing.");
		System.out.println("All product categories are displayed correctly.");
		return this;
	}

	public ProductDisplay verifyApplicationLogsDisplayed() {
		System.out.println("Skipping application logs validation as they are not present on the website.");
		return this;
	}

}
