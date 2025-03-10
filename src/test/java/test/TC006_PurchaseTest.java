package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.CartPage;
import pages.HomePage;
import pages.PurchasePage;
import utils.UtilsClass;

public class TC006_PurchaseTest extends ProjectSpecificationMethods {
    HomePage home;
    CartPage cart;
    PurchasePage purchase;

	@BeforeClass
	public void setup() {
		UtilsClass utils = new UtilsClass();
		utils.browserLaunch("Chrome", "https://www.demoblaze.com");
		home = new HomePage(UtilsClass.driver);
		cart = new CartPage(UtilsClass.driver);
		purchase = new PurchasePage(UtilsClass.driver);
		sheetname = "PurchaseTest";
		testName = "PurchaseFunctionality";
		testDescription = "Testing the purchase form appears after selecting the cart page";
		testAuthor = "Sathya";
		testCategory = "Smoke Testing";
	}

	@Test(priority = 1, dataProvider = "PurchaseTest")
    public void testAddProductToCart(String name, String cardNumber, String country, String productName) {
        home.addItemToCart(productName);
        home.navigateToCart();

        // Verify purchase form is displayed
        Assert.assertTrue(cart.isPurchaseFormDisplayed(), "Purchase form is NOT displayed!");

        // Proceed to purchase
        cart.clickPlaceOrder();
        purchase.enterPurchaseDetails(name, cardNumber, country);
        purchase.confirmPurchase();

        // Verify confirmation message
        Assert.assertTrue(purchase.isConfirmationDisplayed(), "Order confirmation message is NOT displayed!");
    }
}