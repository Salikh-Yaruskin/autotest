package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilVisible;

public class DefaultWindowPage extends BasePage {

    @FindBy(css = "a[href='#']")
    private WebElement newBrowserTabLink;

    private String currentWindow;

    public DefaultWindowPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать ссылку New Browser Tab")
    public FramesAndWindowsPage clickNewBrowserTab() {
        currentWindow = webDriver.getWindowHandle();
        waitUntilVisible(webDriver, newBrowserTabLink);
        newBrowserTabLink.click();

        for (String handle : webDriver.getWindowHandles()) {
            if (!handle.equals(currentWindow)) {
                webDriver.switchTo().window(handle);
                break;
            }
        }

        return new FramesAndWindowsPage(webDriver);
    }
}
