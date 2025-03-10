package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC002_LoginTest extends ProjectSpecificationMethods {

    @BeforeTest
    public void setup() {
        sheetname = "LoginPageTest";
        testName = "LoginTest";
        testDescription = "Testing the login functionality with valid and invalid credentials";
        testAuthor = "Sathya";
        testCategory = "Smoke Testing";
    }

    @Test(dataProvider = "excelRead")
    public void loginTest(String UserName, String Password, String ExpectedMessage, String TestType) {
        HomePage home = new HomePage(driver);
        home.clickLogin()
            .enterUsername(UserName)
            .enterPassword(Password)
            .clickLogin()
            .verifyLogin(ExpectedMessage, TestType);
    }
}

