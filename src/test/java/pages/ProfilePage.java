package pages;

import helpers.PropertyProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.WaitHelper.waitUntilUrlContains;
import static helpers.WaitHelper.waitUntilVisible;

public class ProfilePage extends BasePage {

    @FindBy(xpath = "//div[@class='form-group ng-scope']//input[@name='name']")
    private WebElement nameField;

    @FindBy(xpath = "//div[@class='form-group ng-scope']//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//pre[contains(@class,'ng-binding')]")
    private WebElement preField;

    @FindBy(xpath = "//a[@ui-sref='form.interests']")
    private WebElement nextSectionButton;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Заполнить поле Name")
    public ProfilePage inputName() {
        waitUntilVisible(webDriver, nameField);
        nameField.sendKeys(PropertyProvider.getInstance().getProperty("property.name"));
        return this;
    }

    @Step("Заполнить поле Email")
    public ProfilePage inputEmail() {
        waitUntilVisible(webDriver, emailField);
        emailField.sendKeys(PropertyProvider.getInstance().getProperty("property.email"));
        return this;
    }

    @Step("Получить введенные значение из первью-поля")
    public String getPreviewField() {
        waitUntilVisible(webDriver, preField);
        return preField.getText().trim();
    }

    @Step("Перейти к шагу Interests")
    public InterestsPage clickNextSection() {
        waitUntilVisible(webDriver, nextSectionButton);
        nextSectionButton.click();
        waitUntilUrlContains(webDriver, PropertyProvider.getInstance().getProperty("web.url.interests"));
        return new InterestsPage(webDriver);
    }
}
