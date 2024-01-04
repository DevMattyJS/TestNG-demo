package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BootstrapProgressBarPage;

public class ProgressBarTest extends BaseTest {

    private BootstrapProgressBarPage progressBarPage;
    private SoftAssert softAssert = new SoftAssert();

    @Test
    public void testProgressBarPercentage() {

        progressBarPage = homePage.clickBootstrapProgressBarLink();
        progressBarPage.clickStartDownloadButton();

        String actualMessage = progressBarPage.getCompletedMessage();
        String actualPercentage = progressBarPage.getProgressBarPercentage();
        String expectedMessage = "Download completed!";
        String expectedPercentage = "100%";

        softAssert.assertEquals(actualMessage, expectedMessage, "\n The message is not correct! \n");
        softAssert.assertEquals(actualPercentage, expectedPercentage, "\n Percentage is not 100%! \n");
        softAssert.assertAll();
    }
}
