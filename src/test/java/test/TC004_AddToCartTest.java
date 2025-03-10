package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.HomePage;
import pages.ProductPage;
import pages.PurchasePage;
import utils.UtilsClass;
import pages.CartPage;

public class TC004_AddToCartTest extends ProjectSpecificationMethods {

    @BeforeTest
    public void setup() {
        sheetname = "AddToCartTest";
        testName = "AddToCartFunctionality";
        testDescription = "Testing product selection and adding to cart";
        testAuthor = "Sathya";
        testCategory = "Smoke Testing";
    }

    @Test
    public void addToCartTest() {
        HomePage home = new HomePage(driver);
        ProductPage product = new ProductPage(driver);
        CartPage cart = new CartPage(driver);

        home.selectProduct("Samsung galaxy s6");

        product.verifyProductDetails("Samsung galaxy s6", "The Samsung Galaxy S6 is powered by 1.5GHz octa-core Samsung Exynos 7420 processor and it comes with 3GB of RAM. The phone packs 32GB of internal storage cannot be expanded.", "$360");

        product.clickAddToCart();

        home.navigateToCart(); 
        cart.verifyItemAdded("Samsung galaxy s6");
    }
}
