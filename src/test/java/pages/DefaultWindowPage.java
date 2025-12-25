package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilVisible;

public class DefaultWindowPage extends BasePage {

    @FindBy(xpath = "//a[text()='New Browser Tab']")
    private WebElement newBrowserTabLink;

    public DefaultWindowPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать ссылку New Browser Tab")
    public void clickNewBrowserTab() {
        waitUntilVisible(webDriver, newBrowserTabLink);
        newBrowserTabLink.click();
    }
}
