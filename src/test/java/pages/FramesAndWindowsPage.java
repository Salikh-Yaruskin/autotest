package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

import static helpers.WaitHelper.waitUntilVisible;

public class FramesAndWindowsPage extends BasePage {

    @FindBy(xpath = "//iframe[contains(@class,'demo-frame')]")
    private WebElement iframe;

    @FindBy(xpath = "//a[@target='_blank']")
    private WebElement openWindowLink;

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
    public FramesAndWindowsPage clickOpenWindow() {
        waitUntilVisible(webDriver, openWindowLink);
        openWindowLink.click();
        return this;
    }
}
