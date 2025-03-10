package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;

import java.util.concurrent.TimeUnit;

 public class TC007_LogoutTest extends ProjectSpecificationMethods{
    WebDriver driver;
    
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://example.com"); // Replace with actual site URL
    }
    
    @Test
    public void testLogoutFunctionality() {
        // Assuming user is already logged in
        WebElement logoutButton = driver.findElement(By.id("logout")); // Change locator as needed
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");
        
        logoutButton.click();
        
        // Verify redirection to the homepage
        String expectedUrl = "https://example.com/home"; // Replace with actual homepage URL
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Logout redirection failed");
    }
    
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
   
