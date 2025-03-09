package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.ProjectSpecificationMethods;
import pages.HomePage;


public class TC001_SignUpTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetname = "SignUpPageTest";
        testName = "SignUpTest";
        testDescription = "Testing the signup functionality with positive and negative cases";
        testAuthor = "Sathya";
        testCategory = "Smoke Testing";
	}

	@Test(dataProvider = "excelRead")
	public void signUpTest(String UserName,String Password ,String TestName,String expected,String TestType) {
		HomePage obj = new HomePage(driver);
		obj.clickSignUp()

		.Username(UserName)
		.password(Password)
		.clickSignUp()
		.validUser(expected ,TestType);
	}
}
