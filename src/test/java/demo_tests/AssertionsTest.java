package demo_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsTest {

    private SoftAssert softAssert = new SoftAssert();
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
    public void testSingleCheckbox() {
        driver.findElement((By.linkText("Checkbox Demo"))).click();
        driver.findElement(By.id("isAgeSelected")).click();
        String actualMessage = driver.findElement(By.id("txtAge")).getText();
        String expectedMessage = "Checked";
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void testRadioButtons() {
        driver.findElement(By.linkText("Radio Buttons Demo")).click();
        driver.findElement(By.xpath("//input[@name='gender' and @value='Male']")).click();
        driver.findElement(By.xpath("//input[@value='15 - 50']")).click();
        driver.findElement(By.xpath("//button[text()='Get values']")).click();

        String actualGender = driver.findElement(By.cssSelector("span.genderbutton")).getText();
        String actualAgeGroup = driver.findElement(By.cssSelector("span.groupradiobutton")).getText();

        // When we use softAssert, execution will continue if 1 assertion fails (difference from Assert)
        softAssert.assertEquals(actualGender, "Male", "\n Gender message is not correct! \n");
        softAssert.assertTrue(actualAgeGroup.contains("15"), "\n Age group message is not correct! \n");

        // When we use softAssert we have to call assertAll() function at the end of the test
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
