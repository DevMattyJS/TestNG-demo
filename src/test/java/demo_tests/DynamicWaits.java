package demo_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class DynamicWaits {

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
    public void testExplicitWait() {
        driver.findElement(By.linkText("Dynamic Data Loading")).click();
        driver.findElement(By.id("save")).click();
        By image = By.xpath("//div[@id='loading']/img");

        // WebDriverWait will wait until the condition is met,
        //      but the time we define in constructor at most
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(image));

        boolean isImageDisplayed = driver.findElement(image).isDisplayed();
        Assert.assertTrue(isImageDisplayed, "Image Is Not Displayed!");
    }

    @Test
    public void testFluentWait() {
        driver.findElement(By.linkText("JQuery Download Progress bars")).click();
        driver.findElement(By.id("downloadButton")).click();

        // Waiting 30 seconds (max) for ar element to be present on the page
        //      checking for its presence once every 100 milliseconds
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                WebElement progressLabel = driver.findElement(By.xpath("//div[@id='dialog']/div[@class='progress-label']"));
                String progressLabelText = progressLabel.getText();

                if (progressLabelText.equals("Complete!")) {
                    System.out.println("Download is Complete!");
                    return progressLabel;
                }

                System.out.println(progressLabelText);
                return null;
            }
        });
    }

    @Test
    public void testImplicitWait() {
        // This is a global dynamic wait statement (it will wait for every element to load)
        //      => it should be declared in setUp() method, but it's not a good practice
        //              to mix implicit and explicit waits in one class
        //                  => it can cause unexpected behavior
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/dynamic_loading");

        driver.findElement(By.partialLinkText("Example 2")).click();
        driver.findElement(By.xpath("//div[@id='start']/button")).click();

        String actualMessage = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();

        Assert.assertEquals(actualMessage, "Hello World!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
