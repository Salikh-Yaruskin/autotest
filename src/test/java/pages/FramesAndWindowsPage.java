package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

import static helpers.WaitHelper.waitUntilVisible;

public class FramesAndWindowsPage extends BasePage {

    @FindBy(css = "#example-1-tab-1 iframe.demo-frame")
    private WebElement iframe;

    @FindBy(css = "a[href='#']")
    private WebElement openWindowLink;

    private String currentWindow;

    public FramesAndWindowsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переключиться в iframe")
    public FramesAndWindowsPage switchToFrame() {
        webDriver.switchTo().defaultContent();
        waitUntilVisible(webDriver, iframe);
        webDriver.switchTo().frame(iframe);
        return this;
    }

    @Step("Нажать ссылку открытия нового окна")
    public DefaultWindowPage clickOpenWindow() {
        currentWindow = webDriver.getWindowHandle();
        waitUntilVisible(webDriver, openWindowLink);
        openWindowLink.click();
        switchToNewWindow();
        return new DefaultWindowPage(webDriver);
    }

    @Step("Получение количества открытых окон")
    public int getOpenedWindowsCount() {
        return webDriver.getWindowHandles().size();
    }

    private void switchToNewWindow() {
        for (String handle : webDriver.getWindowHandles()) {
            if (!handle.equals(currentWindow)) {
                webDriver.switchTo().window(handle);
                break;
            }
        }
    }
}
