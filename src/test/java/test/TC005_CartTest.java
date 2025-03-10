package test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.CartPage;
import utils.CartDataProvider;
import utils.UtilsClass;

public class TC005_CartTest extends ProjectSpecificationMethods{

    HomePage home;
    CartPage cart;

	@BeforeClass
		
		public void setup() {
	        UtilsClass utils = new UtilsClass();
	        utils.browserLaunch("Chrome", "https://www.demoblaze.com");

	        home = new HomePage(UtilsClass.driver);
	        cart = new CartPage(UtilsClass.driver);

		sheetname = "CartTestData";
		testName = "Cart Functionality";
		testDescription = "Verify adding & deleting items from the cart";
		testAuthor = "Sathya";
		testCategory = "Regression Testing";
	}

    @Test(priority = 1, dataProvider = "CartData", dataProviderClass = CartDataProvider.class)
    public void testAddItemToCart(String productName, int expectedPrice) {
        // Add item to cart
        home.addItemToCart(productName);
        home.navigateToCart();

        // Verify item is added
        Assert.assertTrue(cart.isItemPresent(productName), "Item is NOT present in the cart!");

        // Validate total price
        int actualTotal = cart.getTotalPrice();
        Assert.assertEquals(actualTotal, expectedPrice, "Total price mismatch!");
    }

    @Test(priority = 2, dataProvider = "CartData", dataProviderClass = CartDataProvider.class, dependsOnMethods = "testAddItemToCart")
    public void testDeleteItemFromCart(String productName, int expectedPrice) {
        home.navigateToCart();

        int initialCount = cart.getItemCount();
        cart.deleteItem(productName);

        // Verify item is removed
        Assert.assertFalse(cart.isItemPresent(productName), "Item was NOT removed from the cart!");

        // Validate cart count update
        Assert.assertEquals(cart.getItemCount(), initialCount - 1, "Cart count did not update!");

        // Validate total price updates correctly
        int expectedTotal = cart.getTotalPrice() - expectedPrice;
        cart.validateTotalPrice(expectedTotal);
    }
}

