package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilClickable;
import static helpers.WaitHelper.waitUntilVisible;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[contains(@id,'_input_username_0') or contains(@name,'_input_username_0')]")
    private WebElement description;

    @FindBy(xpath = "//button[contains(@class,'btn')]")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'alert') and contains(.,'Username or password is incorrect')]")
    private WebElement authErrorAlert;

    @FindBy(xpath = "//input[@id='username']/following-sibling::div[contains(@class,'help-block')]")
    private WebElement usernameValidation;

    @FindBy(xpath = "//input[@id='password']/following-sibling::div[contains(@class,'help-block')]")
    private WebElement passwordValidation;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Ввести Username: {value}")
    public LoginPage setUsername(String value) {
        waitUntilVisible(webDriver, username);
        username.clear();
        if (value != null) {
            username.sendKeys(value);
        }
        return this;
    }

    @Step("Ввести Password: {value}")
    public LoginPage setPassword(String value) {
        waitUntilVisible(webDriver, password);
        password.clear();
        if (value != null) {
            password.sendKeys(value);
        }
        return this;
    }

    @Step("Ввести username description: {value}")
    public LoginPage setDescription(String value) {
        waitUntilVisible(webDriver, description);
        description.clear();
        if (value != null) {
            description.sendKeys(value);
        }
        return this;
    }

    @Step("Нажать Login")
    public HomePage clickLogin() {
        waitUntilClickable(webDriver, loginButton);
        loginButton.click();
        return new HomePage(webDriver);
    }

    @Step("Получить сообщение об ошибке")
    public String getErrorMessage() {
        waitUntilVisible(webDriver, authErrorAlert);
        return authErrorAlert.getText().trim();
    }
}
