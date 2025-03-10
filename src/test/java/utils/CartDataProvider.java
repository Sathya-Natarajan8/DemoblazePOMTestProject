package utils;

import org.testng.annotations.DataProvider;

public class CartDataProvider extends UtilsClass{

    @DataProvider(name = "CartData")
    public static Object[][] getCartData() {
        return new Object[][] {
            {"Samsung galaxy s6", 360},
            {"Nokia lumia 1520", 820}
        };
    }
}

