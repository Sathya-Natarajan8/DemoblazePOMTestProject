package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.ProductDisplay;

public class TC003_ProductCategoryTest extends ProjectSpecificationMethods {

    @BeforeTest
    public void setup() {
        sheetname = "ProductCategoryTest";
        testName = "ProductCategoryDisplayTest";
        testDescription = "Validating product categories and logs display";
        testAuthor = "Sathya";
        testCategory = "Smoke Testing";
    }

    @Test
    public void productCategoryDisplayTest() {
    	ProductDisplay home = new ProductDisplay (driver);
            home.verifyMonitorTabDisplayed()
            .verifyProductCategories()
            .verifyApplicationLogsDisplayed();
    }
}
