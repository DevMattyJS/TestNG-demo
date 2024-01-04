package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By bootstrapProgressBarPageLink = By.linkText("Bootstrap Progress bar");

    public BootstrapProgressBarPage clickBootstrapProgressBarLink() {
        click(bootstrapProgressBarPageLink);
        return new BootstrapProgressBarPage();
    }
}
