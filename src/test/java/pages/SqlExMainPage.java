package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilClickable;
import static helpers.WaitHelper.waitUntilVisible;

public class SqlExMainPage extends BasePage {

    @FindBy(xpath = "//form[@name='frmlogin']//input[@name='login']")
    private WebElement loginInput;

    @FindBy(xpath = "//form[@name='frmlogin']//input[@name='psw']")
    private WebElement passwordInput;

    @FindBy(xpath = "//form[@name='frmlogin']//input[@name='subm1' and @type='submit']")
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