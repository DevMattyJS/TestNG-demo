package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BootstrapProgressBarPage extends BasePage{

    private By startDownloadButton = By.id("dwnBtn");
    private By progressBarPercentage = By.cssSelector("p.counter");
    private By downloadCompleteMessage = By.xpath("//p[contains(@class, 'success')]");

    public void clickStartDownloadButton() {
        click(startDownloadButton);
    }

    public String getProgressBarPercentage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(progressBarPercentage));
        return getText(progressBarPercentage);
    }

    public String getCompletedMessage() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(downloadCompleteMessage));
        return getText(downloadCompleteMessage);
    }
}
