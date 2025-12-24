package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilClickable;
import static helpers.WaitHelper.waitUntilVisible;

public class HomePage extends BasePage {

    @FindBy(xpath = "//p[contains(., \"You're logged in!!\")]")
    private WebElement loggedInMessage;

    @FindBy(xpath = "//a[contains(.,'Logout') or contains(@href, '#/login')]")
    private WebElement logoutLink;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить, что Home открыт")
    public boolean isOpened() {
        waitUntilVisible(webDriver, loggedInMessage);
        return loggedInMessage.isDisplayed();
    }

    @Step("Получить текст сообщения после логина")
    public String getLoggedInMessage() {
        waitUntilVisible(webDriver, loggedInMessage);
        return loggedInMessage.getText().trim();
    }

    @Step("Нажать Logout")
    public LoginPage logout() {
        waitUntilClickable(webDriver, logoutLink);
        logoutLink.click();
        return new LoginPage(webDriver);
    }
}

