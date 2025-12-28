package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilClickable;
import static helpers.WaitHelper.waitUntilVisible;

public class SqlExMainPage extends BasePage {

    @FindBy(css = "input[type='text'][name='login']")
    private WebElement loginInput;

    @FindBy(css = "input[type='password'][name='psw']")
    private WebElement passwordInput;

    @FindBy(css = "input[name='subm1']")
    private WebElement loginButton;

    public SqlExMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Логин на SQL-EX: login={login}")
    public SqlExMainPage login(String login, String password) {
        waitUntilVisible(webDriver, loginInput);
        loginInput.clear();
        loginInput.sendKeys(login);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        waitUntilClickable(webDriver, loginButton);
        loginButton.click();
        return this;
    }
}