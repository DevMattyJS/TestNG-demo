package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class ParameterizedTest {

    private WebDriver driver;
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // Parameters names and values are set up in testng_parameters.xml file
    @BeforeMethod
    @Parameters({"URL"})
    public void openBaseUrl(String url) {
        driver.get(url);
    }

    @Test
    @Parameters({"Task", "TestResult"})
    public void testFileDownload(String task, String testResult) {
        driver.findElement(By.linkText("File Download")).click();
        driver.findElement(By.id("textbox")).sendKeys(task + " Execution: " + testResult);
        driver.findElement(By.id("create")).click();
        driver.findElement(By.id("link-to-download")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
