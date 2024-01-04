package demo_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class TakeFailedScreenshotTest {

    private WebDriver driver;
    private final String BASE_URL = "https://www.lambdatest.com/selenium-playground/";

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openBaseUrl() {
        driver.get(BASE_URL);
    }

    @Test
    public void testSimpleFormDemo() {
        driver.findElement(By.linkText("Simple Form Demo")).click();
        driver.findElement(By.xpath("//input[@id='user-message']"))
                .sendKeys("Test Automation is Awesome!!!");
        driver.findElement(By.id("showInput")).click();

        String actualMessage = driver.findElement(By.id("message")).getText();

        Assert.assertEquals(actualMessage, "Test Automation is Awesome!!!");
    }

    @AfterMethod
    public void takeScreenshotForFailures(ITestResult testResult) {
        // Take screenshot if test fails
        if (ITestResult.FAILURE == testResult.getStatus()) {

            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir")
                    + "/src/test/resources/screenshots/" +
                    testResult.getName() + ".png");

            try {
                FileHandler.copy(source, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
