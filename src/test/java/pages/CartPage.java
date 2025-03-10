package pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import base.ProjectSpecificationMethods;
import utils.UtilsClass;

public class CartPage extends ProjectSpecificationMethods {

    @FindBy(xpath = "//a[@class='hrefch' and text()='Samsung galaxy s6']")
	public
    WebElement cartItem;
    
    @FindBy(xpath = "//td[text()='Samsung galaxy s6']/following-sibling::td/a[text()='Delete']")
    WebElement deleteButton;
    
    @FindBy(id = "totalp")
    WebElement totalPrice;

    @FindBy(xpath = "//tr[@class='success']")
    List<WebElement> cartItems;
    
    @FindBy(xpath = "//button[text()='Place Order']")
    WebElement placeOrderButton;

    public CartPage(WebDriver driver) {
        UtilsClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CartPage verifyItemAdded(String expectedItem) {
        Assert.assertTrue(cartItem.isDisplayed(), "Item is not added to the cart!");
        Assert.assertTrue(cartItem.getText().contains(expectedItem), "Added item does not match!");
        System.out.println("Item successfully added to the cart.");
        return this;
    }

	public void deleteItem(String itemName) {
		WebElement itemDeleteButton = driver
				.findElement(By.xpath("//td[text()='" + itemName + "']/following-sibling::td/a[text()='Delete']"));
		itemDeleteButton.click();
		UtilsClass.waitForElementToDisappear(itemDeleteButton);
	}

	public boolean isItemPresent(String itemName) {
		try {
			driver.findElement(By.xpath("//td[text()='" + itemName + "']"));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public int getItemCount() {
		return cartItems.size();
	}

	public int getTotalPrice() {
		return Integer.parseInt(totalPrice.getText());
	}

	public int calculateExpectedTotal() {
		int total = 0;
		for (WebElement item : cartItems) {
			String priceText = item.findElement(By.xpath("//th[text()='Price']")).getText();
			total += Integer.parseInt(priceText);
		}
		return total;
	}

	public void validateTotalPrice(int expectedPrice) {
		int actualTotal = getTotalPrice();
		Assert.assertEquals(actualTotal, expectedPrice, "Total price mismatch!");
	}

	public boolean isPurchaseFormDisplayed() {
		return placeOrderButton.isDisplayed();
	}

	public PurchasePage clickPlaceOrder() {
		placeOrderButton.click();
		return new PurchasePage(driver);
	}
}

