package tests;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstant = result.getInstance();

        if (!(testInstant instanceof BasicTest)) {
            return;
        }

        var driver = ((BasicTest) testInstant).webDriver;

        attachScreenshot(driver);
    }

    @Attachment(value = "Screenshot (failure)", type = "image/png")
    public byte[] attachScreenshot(WebDriver webDriver) {
        try {
            return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            return new byte[0];
        }
    }
}
